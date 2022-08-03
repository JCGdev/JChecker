package com.jcgdev.cc.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CCRegexValidator {

	public static boolean isValid(String cc) {
		Pattern pattern = Pattern.compile("[0-9]{16}");
		Matcher matcher = pattern.matcher(cc);
		
		return matcher.matches();
	}
}
