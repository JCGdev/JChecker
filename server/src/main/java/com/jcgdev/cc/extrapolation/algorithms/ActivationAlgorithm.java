package com.jcgdev.cc.extrapolation.algorithms;

import com.jcgdev.cc.CreditCard;

public class ActivationAlgorithm implements CCExtrapolationAlgorithm {

	@Override
	public CreditCard execute(CreditCard[] ccs) {
		char[] cc = ccs[0].getNumber().toCharArray();
		
		
		for(int i=0; i<cc.length; i++) {
			if(i>5) {
				cc[i] = "x".charAt(0);
			}

		}
		
		CreditCard extrapoledCC = new CreditCard(new String(cc), null, null, null);
		
		return extrapoledCC;
	}
	
	

}
