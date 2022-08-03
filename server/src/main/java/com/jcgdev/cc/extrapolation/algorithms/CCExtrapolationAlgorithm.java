package com.jcgdev.cc.extrapolation.algorithms;

import com.jcgdev.cc.CreditCard;

public interface CCExtrapolationAlgorithm {
	public CreditCard execute(CreditCard[] ccs);
}