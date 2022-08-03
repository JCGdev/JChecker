package com.jcgdev.cc.validation.algorithms;

import com.jcgdev.cc.CreditCard;

public interface CCValidationAlgorithm {
	public boolean execute(CreditCard cc);
}
