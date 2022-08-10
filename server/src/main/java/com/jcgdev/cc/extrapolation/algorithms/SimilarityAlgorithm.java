package com.jcgdev.cc.extrapolation.algorithms;

import com.jcgdev.cc.CreditCard;

public class SimilarityAlgorithm implements CCExtrapolationAlgorithm{

	@Override
	public CreditCard execute(CreditCard[] ccs) {
		String cc1 = ccs[0].getNumber(); 
		String cc2 = ccs[1].getNumber();
		assert cc1.length() == cc2.length() : "CCs must have same length - Simility extrapolation";
		
		// Splitting bin from the full credit card number
		
		String bin1 = cc1.substring(0, 5);
		String bin2 = cc2.substring(0, 5);
		assert	bin1 == bin2 : "CCs bins must coincide -  ";

		
		String operable1 = cc1.substring(5, cc1.length());
		String operable2 = cc2.substring(5, cc2.length());
		
		
		char[] extrapolatedOperablePart = new char[operable1.length()];
		for(int i=0; i<operable1.length(); i++) {
			if(operable1.charAt(i) == operable2.charAt(i)) {
				extrapolatedOperablePart[i] = operable1.charAt(i);
			}else {
				extrapolatedOperablePart[i] = "x".charAt(0);
			}
		}
		
		String extrapolatedCC = bin1 + new String(extrapolatedOperablePart);
		
		return new CreditCard(extrapolatedCC, null, null, null);
		
	} 

}
 