package com.jcgdev.cc.generation.algorithms;

import com.jcgdev.cc.CreditCard;

public interface CCGenerationAlgorithm {

	// necesitamos un prototipo de metodo/s que 
	// implementaran las clases que implementen esta interfaz
	public CreditCard[] execute(String bin, int genCount);
	

}
