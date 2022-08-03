package com.jcgdev.cc.extrapolation;

import com.jcgdev.cc.CreditCard;
import com.jcgdev.cc.extrapolation.algorithms.CCExtrapolationAlgorithm;

public class CCExtrapolator {
	
	public static CreditCard extrapolate(CreditCard[] ccs, CCExtrapolationAlgorithm algo) {
		return algo.execute(ccs);
	}

}
