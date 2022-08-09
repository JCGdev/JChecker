package com.jcgdev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.jcgdev.cc.CreditCard;


@SpringBootApplication
public class Main {

	private static SpringApplication apiRest = new SpringApplication(Main.class);
	
	public static void main(String[] args) {
		apiRest.run(args);
	}
	
	
	public SpringApplication getSpringApplication() {
		return apiRest;
	}
}
