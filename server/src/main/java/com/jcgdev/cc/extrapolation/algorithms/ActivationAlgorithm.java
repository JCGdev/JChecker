package com.jcgdev.cc.extrapolation.algorithms;

import com.jcgdev.cc.CreditCard;

public class ActivationAlgorithm implements CCExtrapolatorAlgorithm {

	@Override
	public CreditCard execute(CreditCard[] ccs) {
		char[] cc = ccs[0].getNumber().toCharArray();
		
		
		for(int i=cc.length-1; i>cc.length-8; i--) {
			cc[i] = "x".charAt(0);
		}
		
		CreditCard extrapoledCC = ccs[0];
		extrapoledCC.setNumber(new String(cc));
		
		return extrapoledCC;
	}
	
	

}
