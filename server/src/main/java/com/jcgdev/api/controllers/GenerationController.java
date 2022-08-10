package com.jcgdev.api.controllers;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jcgdev.cc.CreditCard;
import com.jcgdev.cc.generation.CCGenerator;
import com.jcgdev.cc.generation.algorithms.LuhnGenerationAlgorithm;


@RestController
public class GenerationController {
 
	@Autowired
	private Environment env;
	
	@GetMapping(value = "/cc/generate",
				produces = MediaType.APPLICATION_JSON_VALUE)
	public String ccgenerator(@RequestParam(name="quantity",required=true) int quantity,
							  @RequestParam(name="bin", required=false) String bin) {

		if(bin == null) {
			bin = "xxxxxxxxxxxxxxxx";
		}
		
		CreditCard[] ccs = CCGenerator.generate(bin, 
												quantity, 
												new LuhnGenerationAlgorithm());
	
		JSONArray generatedCCs = new JSONArray();
		for(CreditCard cc : ccs) {
			generatedCCs.put(cc.toString());
		}
		
		JSONObject response = new JSONObject();
		response.put("generated", generatedCCs);
		
		
		return response.toString();
		
		
	} 
	
}
