package com.jcgdev.cc.generation;

import com.jcgdev.cc.CreditCard;
import com.jcgdev.cc.generation.algorithms.CCGenerationAlgorithm;

public class CCGenerator {
	public static CreditCard[] generate(String bin, 
										int genCount,
										CCGenerationAlgorithm algo) {
		return algo.execute(bin, genCount);
	}
	
	public static CreditCard[] generate(String bin, CCGenerationAlgorithm algo) {
				return algo.execute(bin, 30 );
	}
	
	public static CreditCard[] generate(CCGenerationAlgorithm algo) {
		return algo.execute("xxxxxxxxxxxxxxxx", 30 );
	}



}
