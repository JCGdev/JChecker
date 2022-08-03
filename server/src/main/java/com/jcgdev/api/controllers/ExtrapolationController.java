package com.jcgdev.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
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
	
	@GetMapping(value ="/cc/extrapolate")
    public String pruebaAPI() {
        return "API entrypoints:\n "
        		+ "\n"
        		+ String.format("localhost:%s/cc/extrapolate/similarity?cc1=X&cc2=X \n", env.getProperty("local.server.port"))
        		+ String.format("localhost:%s/cc/extrapolate/activation?cc1=X \n", env.getProperty("local.server.port"))
        		+ String.format("localhost:%s/cc/extrapolate/sofia?cc1=X&cc2=X \n", env.getProperty("local.server.port"))
        		+ "";
    }
	
	@GetMapping("/extrapolate/similarity")
    public CreditCard similarityExtrapolation(@RequestParam(name="cc1",required=true) String cc1,
    							 			  @RequestParam(name="cc2",required=true) String cc2) {

		CreditCard creditCard1 = new CreditCard(cc1, null, null, null);
		CreditCard creditCard2 = new CreditCard(cc2, null, null, null);
		
		CreditCard[] ccs = new CreditCard[] {creditCard1, creditCard2};
		
		CreditCard extrapolated = CCExtrapolator.extrapolate(ccs, new SimilityAlgorithm());																		
        return extrapolated;
    }
	
	@GetMapping("/extrapolate/activation")
    public CreditCard activationExtrapolation(@RequestParam(name="cc1",required=true) String cc1) {

		CreditCard creditCard1 = new CreditCard(cc1, null, null, null);
		CreditCard[] ccs = new CreditCard[] {creditCard1};
		
		CreditCard extrapolated = CCExtrapolator.extrapolate(ccs, new ActivationAlgorithm());			
        return extrapolated;
    }
	
	
	@GetMapping("/extrapolate/sofia")
    public CreditCard SofiaExtrapolation(@RequestParam(name="cc1",required=true) String cc1,
			 					 		 @RequestParam(name="cc2",required=true) String cc2) {
		
		CreditCard creditCard1 = new CreditCard(cc1, null, null, null);
		CreditCard creditCard2 = new CreditCard(cc2, null, null, null);
		
		CreditCard[] ccs = new CreditCard[] {creditCard1, creditCard2};
		
		CreditCard extrapolated = CCExtrapolator.extrapolate(ccs, new SofiaAlgorithm());																		
        return extrapolated;
		
    }
	
	
	
}
