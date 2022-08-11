package com.jcgdev.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileIO {
	
	public static String read(File file, int bufferSize) {
		
		FileInputStream stream = null;
		try {
			stream = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e.getMessage());
		}
		
		return read(stream, bufferSize);
		
	}
	

	public static String read(InputStream inputStream, int bufferSize) {
		
		BufferedReader reader = null;
		StringBuilder fileContent = new StringBuilder();
		char[] buffer = new char[bufferSize];
		
		try {
			reader = new BufferedReader(new InputStreamReader(inputStream));  
			
			while(reader.read(buffer) != -1) {
				fileContent.append(buffer);
			}
			reader.close();
			
		} catch( IOException e) {	
			fileContent = null;
			throw new RuntimeException(e.getMessage()); 
		
		}	
			
		return fileContent.toString();
		
	}

	
	
}
