package com.jcgdev.api.controllers;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jcgdev.Main;
import com.jcgdev.cc.CreditCard;
import com.jcgdev.cc.extrapolation.CCExtrapolator;
import com.jcgdev.cc.extrapolation.algorithms.ActivationAlgorithm;
import com.jcgdev.cc.extrapolation.algorithms.SimilarityAlgorithm;
import com.jcgdev.cc.extrapolation.algorithms.SofiaAlgorithm;
import com.jcgdev.utils.FileIO;

@RestController
public class ExtrapolationController {

	@Autowired
	private Environment env;
	
	@GetMapping(value ="/cc/extrapolate/doc",
				produces = MediaType.TEXT_HTML_VALUE)
    public String extrapolationDoc() {

		ClassPathResource resource = new ClassPathResource("ExtrapolationAPIDoc.html", 
														   this.getClass().getClassLoader());
		
		InputStream stream;
		try {
			stream = resource.getInputStream();
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}

		String fileContent = FileIO.read(stream, 500);
		
		return fileContent;
    }
	

	@GetMapping(value = "/cc/extrapolate/similarity",
				produces = MediaType.APPLICATION_JSON_VALUE)
    public String similarityExtrapolation(@RequestParam(name="cc1",required=true) String cc1,
    							 			  @RequestParam(name="cc2",required=true) String cc2) {

		CreditCard creditCard1 = new CreditCard(cc1, null, null, null);
		CreditCard creditCard2 = new CreditCard(cc2, null, null, null);
		
		CreditCard[] ccs = new CreditCard[] {creditCard1, creditCard2};
		
		CreditCard extrapolated = CCExtrapolator.extrapolate(ccs, new SimilarityAlgorithm());																		
        
		return String.format("{ \"cc\" : \"%s\" }", extrapolated.getNumber());
    }
	
	@GetMapping(value = "/cc/extrapolate/activation",
				produces = MediaType.APPLICATION_JSON_VALUE)
    public String activationExtrapolation(@RequestParam(name="cc",required=true) String cc) {

		CreditCard creditCard1 = new CreditCard(cc, null, null, null);
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
