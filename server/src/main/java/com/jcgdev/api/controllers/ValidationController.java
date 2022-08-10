package com.jcgdev.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jcgdev.cc.CreditCard;
import com.jcgdev.cc.validation.CCValidator;
import com.jcgdev.cc.validation.algorithms.LuhnValidationAlgorithm;


@RestController
public class ValidationController {

	@Autowired
	private Environment env;
	
	//TODO -> Make it a bit less shitty 
	@GetMapping(value ="/cc/validate")
    public String validation() {
        return "API entrypoints:\n "
        		+ "\n"
        		+ String.format("localhost:%s/cc/validate/luhn?cc1=X\n", env.getProperty("local.server.port"))
        		+ String.format("localhost:%s/cc/extrapolate/activation?cc1=X \n", env.getProperty("local.server.port"))
        		+ "";
    }
	
	
	@GetMapping(value = "/cc/validate/luhn",
				produces = MediaType.APPLICATION_JSON_VALUE)
    public String luhnValidation(@RequestParam(name="cc",required=true) String cc) {

		CreditCard creditCard1 = new CreditCard(cc, null, null, null);
		
		boolean status = CCValidator.validate(creditCard1, new LuhnValidationAlgorithm());
			 
        return String.format("{ \"isValid\" : %s }", Boolean.toString(status));
    } 
	
	
}
