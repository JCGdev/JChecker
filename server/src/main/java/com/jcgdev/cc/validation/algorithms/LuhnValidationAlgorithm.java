package com.jcgdev.cc.validation.algorithms;


import com.jcgdev.cc.CreditCard;

public class LuhnValidationAlgorithm implements CCValidationAlgorithm {

	@Override
	public boolean execute(CreditCard cc) {
		
		int[] ccNum = cc.getNumberAsIntArray();
		int checkDigit = ccNum[ccNum.length-1];
		
		int[] doubledCC = this.doubleValueEverySecondDigit(ccNum);
		int sum = this.susmAllDigits(doubledCC);
	
		boolean validity = (sum % 10) == 0;
		
		return validity;
	}
	
	
	
	// ------ dissected Luhn --------------------

		private int[] doubleValueEverySecondDigit(int[] operablePart) {
			// Doubling value every 2nd digit (Including validation digit)
			int[] resultingNumber = new int[operablePart.length];
			
			boolean doubleEveryTwoDigits = false; 
			for(int i=resultingNumber.length-1; i>=0; i--) {
				if(doubleEveryTwoDigits){ 
					resultingNumber[i] = operablePart[i] * 2;
					doubleEveryTwoDigits = false;
					
				} else {
					resultingNumber[i] = operablePart[i];
					doubleEveryTwoDigits = true;
				}
			}
			return resultingNumber;
		}
		
		
		
		
		private int susmAllDigits(int[] operable) {
			// 2 digit numbers get their digits added
			// 12 --> 1 + 2 = 3
			
			// Sum of all indeces while separating two digit numbers and adding them
			int sum = 0;

			for(int i=0; i<operable.length; i++) {
				if(operable[i] > 9) { //Two digit number
					int firstDigit = (int)((double)operable[i] / 10.0);
					int secondDigit = operable[i] % 10;
							
					sum+= (firstDigit + secondDigit);
							
					} else {
						sum += operable[i];
					}
				}
			return sum;
		}
		
	
}
