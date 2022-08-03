package com.jcgdev.cc.extrapolation.algorithms;

import com.jcgdev.cc.CreditCard;

public interface CCExtrapolatorAlgorithm {
	public CreditCard execute(CreditCard[] ccs);
}