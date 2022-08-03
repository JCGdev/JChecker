package com.jcgdev;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.jcgdev.cc.CreditCard;
import com.jcgdev.cc.extrapolation.algorithms.SofiaAlgorithm;


@SpringBootApplication
public class Main {

	private static SpringApplication apiRest = new SpringApplication(Main.class);
	
	public static void main(String[] args) {
		apiRest.setDefaultProperties(Collections.singletonMap("server.port",
															  "8083"));
		apiRest.run(args);
		
//		String cc1 = "4915110176928790";
//		String cc2 = "4915110191768499";
//		
//		CreditCard creditCard1 = new CreditCard(cc1, null, null, null);
//		CreditCard creditCard2 = new CreditCard(cc2, null, null, null);
//	
//		CreditCard extrapolated = new SofiaAlgorithm().execute(new CreditCard[] {creditCard1, 
//																					creditCard2});
//		System.out.println(extrapolated.getNumber());
																																						
		
	}

	
	public SpringApplication getSpringApplication() {
		return apiRest;
	}
}
