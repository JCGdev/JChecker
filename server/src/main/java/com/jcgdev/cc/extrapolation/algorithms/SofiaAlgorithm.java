package com.jcgdev.cc.extrapolation.algorithms;

import com.jcgdev.cc.CreditCard;

public class SofiaAlgorithm implements CCExtrapolationAlgorithm {

	@Override
	public CreditCard execute(CreditCard[] ccs) {
		
		String cc1 = ccs[0].getNumber(); 
		String cc2 = ccs[1].getNumber();
		assert cc1.length() == cc2.length() : "CCs must have same length - Sofia extrapolation";
		
		// Splitting bin from the full credit card number
		
		 String bin1 = cc1.substring(0, 5);
		 String bin2 = cc2.substring(0, 5);
		 assert	bin1 == bin2 : "CCs bins must coincide - Sofia Algorithm";
		
		 
		 char[] creditCard1 = cc1.toCharArray(); 
		 char[] creditCard2 = cc2.toCharArray();
		 
		 // sum 9th positions and 10th positions of both ccs
		 int sumOf9thPosOfcc1Andcc2 = Character.getNumericValue(creditCard1[9]) + 
				 					  Character.getNumericValue(creditCard2[9]);
		 
		 int sumOf10thPosOfcc1Andcc2 = Character.getNumericValue(creditCard1[10]) + 
				 					   Character.getNumericValue(creditCard2[10]);
		  
		 // (sum/2)*5
		 double nextOperationsOfcc1 = ((double)sumOf9thPosOfcc1Andcc2/2.0)*5.0;
		 double nextOperationsOfcc2 = ((double)sumOf10thPosOfcc1Andcc2/2.0)*5.0;
		 
		 // sum of the result of the last operations
		 int sumOfOperationsInBothCards = (int) (nextOperationsOfcc1 + nextOperationsOfcc2);
		 
		 String extrapolatedCard = String.format("%s%sxxxxxx", 
				 								 cc1.substring(0, 7), 
				 								 sumOfOperationsInBothCards);
		 
		return new CreditCard(extrapolatedCard, null, null, null);
	}

}
