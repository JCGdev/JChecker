package com.jcgdev.cc.generation.algorithms;

import com.jcgdev.cc.CreditCard;

import java.lang.Math;
import java.util.Arrays;


public class LuhnGenerationAlgorithm implements CCGenerationAlgorithm {
	
	/* 
	 * 567676853635363  8 (validationDigit) 
	 * -- Operable --- 
	 * 
	 * 
	 */
	
	@Override
	public CreditCard[] execute(String bin, int genCount) {
		
		CreditCard[] cards = new CreditCard[genCount];		
		
		
		for(int i=0; i<genCount; i++) {
			String cc = this.generateRandomCCWithoutCheckDigit(bin);
			char[] ccWithoutLastIndex = cc.substring(0, cc.length()-1).toCharArray();
			int[] operablePart = this.convertCharArrayToIntArray(ccWithoutLastIndex);
			
			
			int[] resulting = this.doubleValueEverySecondDigit(operablePart);
			int sum = this.susmAllDigits(resulting);
			int checkDigit = (sum * 9) % 10;
			
			// 243457534545364x
			cc = cc.replace("x".toCharArray()[0], Integer.toString(checkDigit)
													.toCharArray()[0]);
			
			cards[i] = new CreditCard(cc, null, null, null);
			
		}
	
		
		return cards;
	}
	
	
	
	private String generateRandomCCWithoutCheckDigit(String bin) {
		
		char[] cc = bin.toCharArray();
		
		for (int i=0; i <= cc.length-2; i++) {
		    if (cc[i] == "x".toCharArray()[0]) {
		    	int randNum = (int) (Math.random() * 9);	 // return num between 0-9
		    	cc[i] = Integer.toString(randNum).charAt(0);	
		    }
		}
		
		// 656565657656545x  last digit has to be computed
		return new String(cc);
	}

	
	private int[] convertCharArrayToIntArray(char[] array) {
		int[] resultingArray = new int[array.length];
		

		for(int i=0; i<array.length; i++) {
			resultingArray[i] = Character.getNumericValue(array[i]);
		}
	
		return resultingArray;
	}

	
	
	// ------ dissected Luhn --------------------

	private int[] doubleValueEverySecondDigit(int[] operablePart) {
		// Doubling value every 2nd digit (Including validation digit)
		int[] resultingNumber = new int[operablePart.length];
		
		boolean doubleEveryTwoDigits = true;
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
				int firstDigit = (int) ((double)operable[i] / 10.0);
				int secondDigit = operable[i] % 10;
						
				sum += (firstDigit + secondDigit);
						
				} else {
					sum += operable[i];
				}
			}
		return sum;
	}
	
	
	
}
