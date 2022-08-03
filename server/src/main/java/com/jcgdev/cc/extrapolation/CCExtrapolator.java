package com.jcgdev.cc.extrapolation;

import com.jcgdev.cc.CreditCard;
import com.jcgdev.cc.extrapolation.algorithms.CCExtrapolatorAlgorithm;

public class CCExtrapolator {
	
	public static CreditCard extrapolate(CreditCard[] ccs, CCExtrapolatorAlgorithm algo) {
		return algo.execute(ccs);
	}

}
