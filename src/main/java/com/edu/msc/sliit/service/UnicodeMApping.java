package com.edu.msc.sliit.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.msc.sliit.model.BraileeToSinhalaText;

@Service
public class UnicodeMApping {
	
	public static HashMap<Integer, String[]> sinhalaUnicodes=new HashMap<Integer, String[]>();
	
	@Autowired
	BraileeToSinhalaText braileeToSinhalaText;

	public void uncodeMAppingSinhala(HashMap<Integer, int[]> calculatCordinates) {	
		//printHashMap2(calculatCordinates);
		//System.out.println();
		System.out.println("Testing-----------------------");
		//unicodeStringHashMap(calculatCordinates);
		unicodeStringHashMap(calculatCordinates);
		System.out.println("Testing-----------------------");
		
	}
	public HashMap<Integer, String[]> unicodeStringHashMap(HashMap<Integer, int[]> calculateBanayValuesForEachCordinates) {
		int row=0;
		HashMap<Integer, int[]> testOne=new HashMap<Integer, int[]>(); 
		int test[]=new int[26];
		test[0]=5;
		test[1]=1;		
		test[2]=5;
		test[3]=28;		
		test[4]=5;
		test[5]=55;		
		test[6]=5;
		test[7]=59;
		test[8]=5;
		test[9]=10;
		test[10]=5;
		test[11]=20;		
		test[12]=5;
		test[13]=37;	
		test[14]=5;
		test[15]=51;		
		test[16]=5;
		test[17]=17;		
		test[18]=5;
		test[19]=34;			
		test[20]=5;
		test[21]=45;		
		test[22]=5;
		test[23]=21;		
		test[24]=5;
		test[25]=42;		
		for (int i = 0; i < calculateBanayValuesForEachCordinates.size(); i++) {			
			testOne.put(i, test);
		}
		Iterator hmIterator = testOne.entrySet().iterator(); // test one should replace with calculateBanayValuesForEachCordinates
		while (hmIterator.hasNext()) {
			 Map.Entry mapElement = (Map.Entry) hmIterator.next();
			 int[] cordinates = ((int[]) mapElement.getValue());
			 String sinRowUnicodes[]=new String[cordinates.length];
			 for (int i = 0; i < cordinates.length; i++) {
				 sinRowUnicodes[i]=braileeToSinhalaText.sinhalaLetters.get(cordinates[i])+"*";
			 }
			 sinhalaUnicodes.put(row, sinRowUnicodes);
			 row++;			
		}
		printHashMap(sinhalaUnicodes);
		return sinhalaUnicodes;
		// System.out.println(Arrays.toString(cordinates));
	}
	
	public String normalizeHashMap(HashMap<Integer, String[]> unicodeStringHashMap) {
		String word="";
		Iterator hmIterator = unicodeStringHashMap.entrySet().iterator();
		while (hmIterator.hasNext()) {
			 Map.Entry mapElement = (Map.Entry) hmIterator.next();
			 String[] cordinates = ((String[]) mapElement.getValue());
			 System.out.println("cordinates.length : "+cordinates.length);
			 for (int i = 0; i < cordinates.length; i++) {
				 
				 word=Normalization.sinhalaNormalization(word+cordinates[i]);
				  //word = word + Normalization.sinhalaNormalization(word+cordinates[i]);
				  //System.out.println(word);
				  
				  
			 }
			 word=word+"\n";
			 //System.out.println(word);					
		}
		return word;
	}
	
	
	
	
	private void printHashMap(HashMap<Integer, String[]> calculateBanayValuesForEachCordinates) {
		Iterator hmIterator = calculateBanayValuesForEachCordinates.entrySet().iterator();
		while (hmIterator.hasNext()) {
			 Map.Entry mapElement = (Map.Entry) hmIterator.next();
			 String[] cordinates = ((String[]) mapElement.getValue());
			 System.out.println(Arrays.toString(cordinates));					
		}		
	}
	
	private void printHashMap2(HashMap<Integer, int[]> calculateBanayValuesForEachCordinates) {
		Iterator hmIterator = calculateBanayValuesForEachCordinates.entrySet().iterator();
		while (hmIterator.hasNext()) {
			 Map.Entry mapElement = (Map.Entry) hmIterator.next();
			 int[] cordinates = ((int[]) mapElement.getValue());
			 System.out.println(Arrays.toString(cordinates));					
		}		
	}
	
	
	
	
	
	/*
	private void unicodeStringHashMap(HashMap<Integer, int[]> calculateBanayValuesForEachCordinates) {
		int row=0;
		Iterator hmIterator = calculateBanayValuesForEachCordinates.entrySet().iterator();
		while (hmIterator.hasNext()) {
			 Map.Entry mapElement = (Map.Entry) hmIterator.next();
			 int[] cordinates = ((int[]) mapElement.getValue());
			 String sinRowUnicodes[]=new String[cordinates.length];
			 for (int i = 0; i < cordinates.length; i++) {
				 sinRowUnicodes[i]=cordinates[i]+"*";
			 }
			 sinhalaUnicodes.put(row, sinRowUnicodes);
			 row++;			
		}
		printHashMap(sinhalaUnicodes);
		// System.out.println(Arrays.toString(cordinates));
	}*/
	
	
	
	
	
}
