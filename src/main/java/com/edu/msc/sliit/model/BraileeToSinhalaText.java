package com.edu.msc.sliit.model;

import java.util.HashMap;

import org.springframework.context.annotation.Configuration;

@Configuration
public class BraileeToSinhalaText {
	
	public static HashMap<Integer, String> sinhalaLetters=new HashMap<Integer, String>();
	
	static {
		sinhalaLetters.put(1, "අ");
		sinhalaLetters.put(28, "ආ");
		sinhalaLetters.put(55, "ඇ");
		sinhalaLetters.put(59, "ඈ");
		sinhalaLetters.put(10, "ඉ");
		sinhalaLetters.put(20, "ඊ");
		
		sinhalaLetters.put(37, "උ");		
		sinhalaLetters.put(51, "ඌ");
		sinhalaLetters.put(17, "එ");
		sinhalaLetters.put(34, "ඒ");
		sinhalaLetters.put(12, "ඓ");		
		sinhalaLetters.put(45, "ඔ");
		
		sinhalaLetters.put(21, "ඕ");
		sinhalaLetters.put(42, "ඖ");
		
		sinhalaLetters.put(5, "ක");
	
		//Rest of Letters
	}
}
