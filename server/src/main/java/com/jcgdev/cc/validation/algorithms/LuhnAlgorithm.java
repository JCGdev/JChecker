package com.jcgdev.cc.validation.algorithms;

import com.jcgdev.cc.CreditCard;

public class LuhnAlgorithm implements CCValidationAlgorithm {

	@Override
	public boolean execute(CreditCard cc) {
		
		int[] ccNum = cc.getNumberAsIntArray();
		int validationDigit = ccNum[ccNum.length-1];
		
		// Multiply pair positions by 2 (except validation digit)
		int[] resultingNumber = new int[ccNum.length-1];
		
		boolean doubleEveryTwoDigits = true; 
		for(int i=resultingNumber.length-1; i>=0; i--) {
			if(doubleEveryTwoDigits){ 
				resultingNumber[i] = ccNum[i] * 2;
				doubleEveryTwoDigits = false;
				
			} else {
				resultingNumber[i] = ccNum[i];
				doubleEveryTwoDigits = true;
			}
		
		}
		
		// Sum of all indeces while separating two digit numbers and adding them
		int sum = 0;

		for(int i=0; i<resultingNumber.length; i++) {
			if(resultingNumber[i] > 10) { //Two digit number
				int firstDigit = (int) ((double)resultingNumber[i] / 10.0);
				int secondDigit = resultingNumber[i] % 10;
				
				sum+= (firstDigit + secondDigit);
				
			} else {
				sum+=resultingNumber[i];
			}
		}
	
		boolean validity = ((sum + validationDigit) % 10) == 0;
		
		return validity;
	}
	
}
