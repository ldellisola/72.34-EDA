import java.security.InvalidParameterException;
import java.util.Arrays;import java.util.stream.Collector;
import java.util.stream.Collectors;

public class LevPalabra {
	public int matrix[][];
	int iSize,jSize;
	
	private int getDistance() {
		return matrix[iSize-1][jSize-1];
	}
	
	public String toString() {
		
		StringBuilder bld = new StringBuilder();
		
		for(int i = 0; i < iSize ; i ++ ) {
			for(int j = 0; j < jSize ; j++) {
				
				bld.append('|').append('\t').append(matrix[i][j]).append('\t');
			}
			
			bld.append('|').append('\n');
		}
		
		return bld.toString();
		
	}
	
	public int GetLevenshteinNivelPalabra(String a, String b) {
		
		if(a == null || a.isEmpty() || b == null || b.isEmpty()) {
			throw new InvalidParameterException("Invalid Arguments");
		}
		
		// SetUp
		a = a.toUpperCase();
		b = b.toUpperCase();
		
		// Por consulta durante el parcial, no se descartan los tildes
		
		String[] topList = Arrays.stream(a.split("(,|\\.|;| |\n|\t)"))
				.filter((t)->{return !t.isEmpty();})
				.toArray(s-> new String[s]);
		String[] leftList =Arrays.stream(b.split("(,|\\.|;| |\n|\t)"))
				.filter((t)->{return !t.isEmpty();})
				.toArray(s-> new String[s]);
		
		jSize = 1 + topList.length;
		iSize = 1 + leftList.length;
		
		matrix = new int[iSize][jSize];
		
		
		for(int i = 0 ; i < leftList.length+1 ; i ++) {
			matrix[i][0] = i;
		}
		for(int i = 0 ; i < topList.length+1 ; i ++)
		{
			matrix[0][i] = i;
		}
		
		
		int values[] = new int[3];
				
		for(int i = 1; i < leftList.length + 1 ; i ++ ) {
			for(int j = 1; j < topList.length + 1 ; j++) {
				
				// Si los caracteres anteriores son iguales, entonces la distancia puede ser
				// la distancia anterior. Si son distintos es la anterior +1
				values[0] = matrix[i-1][j-1] + (leftList[i-1].equalsIgnoreCase(topList[j-1])? 0:1);
				// La distancia tambien puede ser la de arriba +1
				values[1] = matrix[i-1][j] + 1;
				// o la de abajo +1
				values[2] = matrix[i][j-1] + 1;
				
				// Elijo el menor valor de los 3
				Arrays.sort(values);
				matrix[i][j] =values[0];				
				
			}
		}
		
		
		return getDistance();
		
	}
	
	
	private class LevenshteinNotInitializedException extends RuntimeException{
		
		public LevenshteinNotInitializedException(){
			super("");
		}
		
	}
}
