package com.jcgdev.cc.extrapolation.algorithms;

import com.jcgdev.cc.CreditCard;

public class SimilityAlgorithm implements CCExtrapolatorAlgorithm{

	@Override
	public CreditCard execute(CreditCard[] ccs) {
		String cc1 = ccs[0].getNumber(); 
		String cc2 = ccs[1].getNumber();
		assert cc1.length() == cc2.length() : "CCs must have same length - Simility extrapolation";
		
		// Splitting bin from the full credit card number
		
		String bin1 = cc1.substring(0, 5);
		String bin2 = cc2.substring(0, 5);
		assert	bin1 == bin2 : "CCs bins must coincide -  ";

		
		char[] extrapolatedCC = new char[cc1.length()];
		for(int i=0; i<cc1.length(); i++) {
			if(cc1.charAt(i) == cc2.charAt(i)) {
				extrapolatedCC[i] = cc1.charAt(i);
			}else {
				extrapolatedCC[i] = "x".charAt(0);
			}
		}
		
		return new CreditCard(new String(extrapolatedCC), null, null, null);
		
	} 

}
 