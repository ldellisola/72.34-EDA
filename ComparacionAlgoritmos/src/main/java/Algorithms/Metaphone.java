package Algorithms;

import java.security.InvalidParameterException;

public class Metaphone {
	
	public static String GetEncoding(String text) throws Exception {
		if(text == null) {
			throw new InvalidParameterException("Invalid Argument");
		}
		
		
		
		return GenerateEncoding(text);
	}
	
	public static double GetSimilarity(String a, String b) throws Exception {
		if(a == null || a.isEmpty()|| b == null || b.isEmpty()) {
			throw new InvalidParameterException("Invalid Argument");
		}
		
		String encA = GenerateEncoding(a);
		String encB = GenerateEncoding(b);
		
		double max, actual=0;
		
		if(encA.length() > encB.length()) {
			max = encA.length();
			for(int i = 0 ; i < encB.length() ; i++)
				if(encA.charAt(i) == encB.charAt(i))
					actual++;
		}else {
			max = encB.length();
			for(int i = 0 ; i < encA.length() ; i++)
				if(encA.charAt(i) == encB.charAt(i))
					actual++;
		}
		
		
		return actual/max;
		
		
		
		
		
	}
	
	

	private static String GenerateEncoding(String text) {
		org.apache.commons.codec.language.Metaphone met = new org.apache.commons.codec.language.Metaphone();
		
		met.setMaxCodeLen(text.length());
		return met.encode(text);
	}
	
	

}
