package com.jcgdev.cc;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.lang.Nullable;

import com.jcgdev.cc.validation.exceptions.InvalidCreditCardException;

public class CreditCard {
	
	private String number;	
	private String expireMonth;
	private String expireYear;
	private String CVV;
	
	public CreditCard() {}
	
	
	public CreditCard(String number, @Nullable String expireMonth,
					  @Nullable String expireYear, @Nullable String CVV) {
		 
		this.number = number;
  		this.expireMonth = expireMonth;
		this.expireYear = expireYear;
		this.CVV = CVV; 
	}
	

	public CreditCard(String pattern) {
												//3l232324731771745|06|2027|6432
		Pattern targetPattern = Pattern.compile("[0-9]*\\|[0-9]{2}\\|[0-9]{4}\\|([0-9]{3}|[0-9]{4})");
		Matcher matcher = targetPattern.matcher (pattern);
	
		if (!matcher.matches()) {
			throw new InvalidCreditCardException("Invalid credit card pattern");
			
		} else {
			String[] splitted = pattern.split("\\|");
			
			this.number = splitted[0];
			this.expireMonth = splitted[1];
			this.expireYear = splitted[2];
			this.CVV = splitted[3];
		}
	}

	
	
	public String getNumber() {
		if(this.number == null) {
			return "";
		}
		return number;
	}
	
	public int[] getNumberAsIntArray() {
		char[] array = this.getNumber().toCharArray();
		int[] resultingArray = new int[array.length];
		
		if(this.number == null) {
			return null;
			
		} else {
			for(int i=0; i<array.length; i++) {
				resultingArray[i] = Character.getNumericValue(array[i]);
			}
		}
		
		return resultingArray;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	

	
	public String getExpireMonth() {
		if(this.expireMonth == null) {
			return "";
		}
		return expireMonth;
	}
	public void setExpireMonth(String expireMonth) {
		this.expireMonth = expireMonth;
	}
	
	
	public String getExpireYear() {
		if(this.expireYear == null) {
			return "";
		}
		return expireYear;
	}
	public void setExpireYear(String expireYear) {
		this.expireYear = expireYear;
	}
	
	
	public String getCVV() {
		if(this.CVV == null) {
			return "";
		}
		return CVV;
	}
	public void setCVV(String CVV) {
		this.CVV = CVV;	
	}
	
	
	@Override
	public String toString() {
		return String.format("%s|%s|%s|%s", this.number,
											this.expireMonth,
											this.expireYear,
											this.CVV);
	}
}
