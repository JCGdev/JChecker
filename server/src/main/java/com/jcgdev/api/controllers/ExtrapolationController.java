package com.jcgdev.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jcgdev.cc.CreditCard;
import com.jcgdev.cc.extrapolation.CCExtrapolator;
import com.jcgdev.cc.extrapolation.algorithms.ActivationAlgorithm;
import com.jcgdev.cc.extrapolation.algorithms.SimilityAlgorithm;
import com.jcgdev.cc.extrapolation.algorithms.SofiaAlgorithm;

@RestController
public class ExtrapolationController {

	@Autowired
	private Environment env;
	
	//TODO -> Make it a bit less shitty 
	@GetMapping(value ="/cc/extrapolate",
				produces = MediaType.APPLICATION_XHTML_XML_VALUE)
    public String extrapolation() {
        return "<h1>API entrypoints:</h1> \n"
        		+ "\n"
        		+ String.format("<p>localhost:%s/cc/extrapolate/similarity?cc1=X&cc2=X </p> \n", env.getProperty("local.server.port"))
        		+ String.format("<p>localhost:%s/cc/extrapolate/activation?cc1=X </p> \n", env.getProperty("local.server.port"))
        		+ String.format("<p>localhost:%s/cc/extrapolate/sofia?cc1=X&cc2=X </p> \n", env.getProperty("local.server.port"))
        		+ "";
    }
	

	@GetMapping(value = "/cc/extrapolate/similarity",
				produces = MediaType.APPLICATION_JSON_VALUE)
    public String similarityExtrapolation(@RequestParam(name="cc1",required=true) String cc1,
    							 			  @RequestParam(name="cc2",required=true) String cc2) {

		CreditCard creditCard1 = new CreditCard(cc1, null, null, null);
		CreditCard creditCard2 = new CreditCard(cc2, null, null, null);
		
		CreditCard[] ccs = new CreditCard[] {creditCard1, creditCard2};
		
		CreditCard extrapolated = CCExtrapolator.extrapolate(ccs, new SimilityAlgorithm());																		
        
		return String.format("{ \"cc\" : \"%s\" }", extrapolated.getNumber());
    }
	
	@GetMapping(value = "/cc/extrapolate/activation",
				produces = MediaType.APPLICATION_JSON_VALUE)
    public String activationExtrapolation(@RequestParam(name="cc1",required=true) String cc1) {

		CreditCard creditCard1 = new CreditCard(cc1, null, null, null);
		CreditCard[] ccs = new CreditCard[] {creditCard1};
		
		CreditCard extrapolated = CCExtrapolator.extrapolate(ccs, new ActivationAlgorithm());			
        
		return String.format("{ \"cc\" : \"%s\" }", extrapolated.getNumber());
    }
	
	
	@GetMapping(value = "/cc/extrapolate/sofia",
				produces = MediaType.APPLICATION_JSON_VALUE)
    public String SofiaExtrapolation(@RequestParam(name="cc1",required=true) String cc1,
			 					 		 @RequestParam(name="cc2",required=true) String cc2) {
		
		CreditCard creditCard1 = new CreditCard(cc1, null, null, null);
		CreditCard creditCard2 = new CreditCard(cc2, null, null, null);
		
		CreditCard[] ccs = new CreditCard[] {creditCard1, creditCard2};
		
		CreditCard extrapolated = CCExtrapolator.extrapolate(ccs, new SofiaAlgorithm());																		
       
		return String.format("{ \"cc\" : \"%s\" }", extrapolated.getNumber());
		
    }
	
	
	
}
