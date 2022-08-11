package com.jcgdev.api.controllers;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jcgdev.cc.CreditCard;
import com.jcgdev.cc.validation.CCValidator;
import com.jcgdev.cc.validation.algorithms.LuhnValidationAlgorithm;
import com.jcgdev.utils.FileIO;


@RestController
public class ValidationController {

	@Autowired
	private Environment env;
	
	
	@GetMapping(value ="/cc/validate/doc",
				produces = MediaType.TEXT_HTML_VALUE)
    public String validationDoc() {
		ClassPathResource resource = new ClassPathResource("ValidationAPIDoc.html", 
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
	
	
	@GetMapping(value = "/cc/validate/luhn",
				produces = MediaType.APPLICATION_JSON_VALUE)
    public String luhnValidation(@RequestParam(name="cc",required=true) String cc) {

		CreditCard creditCard1 = new CreditCard(cc, null, null, null);
		
		boolean status = CCValidator.validate(creditCard1, new LuhnValidationAlgorithm());
			 
        return String.format("{ \"isValid\" : %s }", Boolean.toString(status));
    } 
	
	
}
