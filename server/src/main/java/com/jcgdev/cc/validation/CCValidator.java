package com.jcgdev.cc.validation;

import com.jcgdev.cc.CreditCard;
import com.jcgdev.cc.validation.algorithms.CCValidationAlgorithm;

public class CCValidator {
	public static boolean validate(CreditCard cc, CCValidationAlgorithm algo) {
		return algo.execute(cc);
	}
}
