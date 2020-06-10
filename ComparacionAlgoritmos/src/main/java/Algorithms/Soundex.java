package Algorithms;


import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Arrays;

public class Soundex {
	

	private static String GenerateEncoding(String searchableText,Boolean isPreprocessed) {
		if(!isPreprocessed) {
			searchableText = searchableText.replaceAll("![a-zA-Z]", "").toUpperCase();
		}
		
		String firstLetter = searchableText.substring(0,1);
		
		char arr[] = (searchableText.replaceAll("(A|E|I|O|U|Y)","0"))
								.replaceAll("(H|W)", "9")
								.replaceAll("(B|P|V|F)","1")
								.replaceAll("(C|G|J|K|Q|S|X|Z)", "2")
								.replaceAll("(D|T)", "3")
								.replace('L', '4')
								.replaceAll("(M|N)", "5")
								.replace('R', '6').toCharArray();
		
		for(int i = 1 ; i < arr.length-1 ; i++) {
			if(arr[i] == '9' && arr[i-1] == arr[i+1]) {
				arr[i-1] = '9';
			}
		}
		
		for(int i = 0 ; i < arr.length-1 ; i++) {		
			int t = i+1;
			
			while(t < arr.length && arr[i] == arr[t]) {
				arr[t]='0';
				t++;
			}
			
		}
		
		String temp = firstLetter.replaceAll("(A|E|I|O|U|Y)","0")
								.replaceAll("(H|W)", "9")
								.replaceAll("(B|P|V|F)","1")
								.replaceAll("(C|G|J|K|Q|S|X|Z)", "2")
								.replaceAll("(D|T)", "3")
								.replace('L', '4')
								.replaceAll("(M|N)", "5")
								.replace('R', '6');
		
		boolean addLetter = false;
		if(temp.charAt(0) == arr[0]) {
			arr[0] = firstLetter.charAt(0);
		}else {
			addLetter = true;
		}
		
		String str = new String(arr).replaceAll("(0|9)", "");
		
		if(addLetter) {
			str = firstLetter + str;
		}
		
		
		
		while(str.length()<4 ) {
			str += '0';
		}
		
		return str.substring(0, 4);
	}
	
	
	public static double GetSimilarity(String a, String b, Boolean areProcessed) throws Exception {
		
		if(a == null || a.isEmpty()|| b == null || b.isEmpty()|| areProcessed == null) {
			throw new InvalidParameterException("Invalid Arguments");
		}
		
		String encodingA = GenerateEncoding(a, areProcessed);
		String encodingB = GenerateEncoding(b, areProcessed);
		
		double count = 0;
		
		for(int i = 0 ; i < encodingA.length(); i++) {
			if(encodingA.charAt(i) == encodingB.charAt(i))
				count++;
		}
		
		
		return count / (double) encodingA.length();
	}
	
	public static String GetEncoding(String searchableText,Boolean isPreprocessed) throws Exception  {
		if(searchableText == null || isPreprocessed == null) {
			throw new InvalidParameterException("Invalid Arguments");
		}

		return GenerateEncoding(searchableText, isPreprocessed);
	}
}
