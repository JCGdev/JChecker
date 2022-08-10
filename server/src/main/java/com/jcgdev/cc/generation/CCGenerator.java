package com.jcgdev.cc.generation;

import java.time.LocalDate;
import java.util.Date;

import com.jcgdev.cc.CreditCard;
import com.jcgdev.cc.generation.algorithms.CCGenerationAlgorithm;

public class CCGenerator {
	public static CreditCard[] generate(String bin, 
										int genCount,
										CCGenerationAlgorithm algo) {
		CreditCard[] ccs = algo.execute(bin, genCount);
		
		for(CreditCard cc : ccs) {
			cc.setCVV(Integer.toString(generateCVV()));
			cc.setExpireYear(Integer.toString(generateYear()));
			cc.setExpireMonth(Integer.toString(generateMonth()));
		}
		
		return ccs;
	}
	
	public static CreditCard[] generate(String bin, CCGenerationAlgorithm algo) {
		CreditCard[] ccs = algo.execute(bin, 30 );
		
		for(CreditCard cc : ccs) {
			cc.setCVV(Integer.toString(generateCVV()));
			cc.setExpireYear(Integer.toString(generateYear()));
			cc.setExpireMonth(Integer.toString(generateMonth()));
		}
		
		return ccs;
	}
	
	public static CreditCard[] generate(CCGenerationAlgorithm algo) {
		CreditCard[] ccs = algo.execute("xxxxxxxxxxxxxxxx", 30 );
		
		for(CreditCard cc : ccs) {
			cc.setCVV(Integer.toString(generateCVV()));
			cc.setExpireYear(Integer.toString(generateYear()));
			cc.setExpireMonth(Integer.toString(generateMonth()));
		}
		
		return ccs;
	}

	
	
	
	private static int generateCVV() {
		return (int) ((Math.random() * (999 - 100)) + 100);
	}
	
	private static int generateYear() {
		LocalDate date = LocalDate.now();
		int actualYear = date.getYear();

								  //
		int randomOffset = (int) ((Math.random() * (3 - 0)) + 0);
		int generatedYear = actualYear + randomOffset;
		
		return generatedYear;
	}
	
	private static int generateMonth() {
		return (int) ((Math.random() * (12 - 1)) + 1);
	}


}
