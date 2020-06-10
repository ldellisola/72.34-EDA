package Algorithms;

import java.security.InvalidParameterException;

public class ExactSearch {
	public static int indexOf(char[] target, char[] query) {
		
		if(query.length == 0 || target.length == 0) {
			throw new InvalidParameterException("Invalid Arguments");
		}
		
		for(int i = 0 ; i <= target.length - query.length ; i++) {
			
			boolean allEqual = true;
			for(int j = 0 ; j < query.length && allEqual; j++) {
				if(target[i+j] != query[j])
					allEqual = false;
			}
			if(allEqual)
				return i;
			
		}
		
		return -1;
	}	

}
