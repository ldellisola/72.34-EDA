import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		
		
//		LevPalabra lev = new LevPalabra();
//		String a = "hotel Hilton,  habitación      doble";
//		String b = "habitación   doble de de lujo en hotel Hilton";
//		
//		System.out.println("LEV: " + lev.GetLevenshteinNivelPalabra(a, b));
//		
//		System.out.println(lev.toString());
//		
		
		System.out.println("Primer dataset");
		System.out.println( "sorpresaV1= " + sorpresaV1( new int[] { 30, 20, 15, 80, 10, 20},  6) );
		System.out.println( "sorpresaV2= " + sorpresaV2( new int[] { 30, 20, 15, 80, 10, 20},  6) );
		System.out.println( "sorpresaV3= " + sorpresaV3( new int[] { 30, 20, 15, 80, 10, 20},  6) );
	
		System.out.println("Segundo dataset");
		System.out.println( "sorpresaV1= " + sorpresaV1( new int[] { 30, 20, 15, 80, 10, 20},  4) );
		System.out.println( "sorpresaV2= " + sorpresaV2( new int[] { 30, 20, 15, 80, 10, 20},  4) );
		System.out.println( "sorpresaV3= " + sorpresaV3( new int[] { 30, 20, 15, 80, 10, 20},  4) );
	
		System.out.println("Tercer dataset");
		int[] auxi= new int[100];
		auxi[0]= 30; 
		auxi[1]= 20;
		auxi[2]= 10; 
		auxi[3]= 120;
		auxi[4]= 140; 
		auxi[5]= 150;
		auxi[6]= 150; 
				
		System.out.println( "sorpresaV1= "+ sorpresaV1( auxi,7) ) ;
		System.out.println( "sorpresaV2= "+ sorpresaV2( auxi,7) ) ;
		System.out.println( "sorpresaV3= "+ sorpresaV3( auxi,7) ) ;
	
		System.out.println("Cuarto dataset");
		auxi= new int[100];
		auxi[0]= 20; 
		auxi[1]= 30;
		auxi[2]= 60; 
		auxi[3]= 70;
		auxi[4]= 50; 
		auxi[5]= 40;
		
		System.out.println( "sorpresaV1= "+ sorpresaV1( auxi,6) ) ;
		System.out.println( "sorpresaV2= "+ sorpresaV2( auxi,6) ) ;
		System.out.println( "sorpresaV3= "+ sorpresaV3( auxi,6) ) ;
			
		System.out.println("Quinto dataset"); 
		System.out.println( "sorpresaV1= " + sorpresaV1( new int[] { 30, 20, 15, 80, 10, 20}, 8) ); 
		System.out.println( "sorpresaV2= " + sorpresaV2( new int[] { 30, 20, 15, 80, 10, 20}, 8) );
		System.out.println( "sorpresaV3= " + sorpresaV3( new int[] { 30, 20, 15, 80, 10, 20}, 8) );
		 	


	}
	
	static public  boolean  sorpresaV1(int[] arreglo, int n)
	{
		if (arreglo== null || n < 0 || arreglo.length < n)
			throw new RuntimeException("bad parameter");

		for ( int rec= 0;  rec <  n - 1; rec++)
			for ( int iter = rec + 1 ;  iter <=  n - 1  ; iter++)
				if  ( arreglo[rec] == arreglo[iter] )
					return false;
	
		return true;
	}

	
	static public  boolean  sorpresaV2(int[] arreglo, int n)
	{
		if (arreglo== null || n < 0 || arreglo.length < n)
			throw new RuntimeException("bad parameter");

		for ( int rec= 0;  rec <=  n - 1; rec++)
			for ( int iter = 0 ;  iter <=  n - 1  ; iter++)
				if  ( rec != iter && arreglo[rec] == arreglo[iter] )
					return false;

		return true;
	}

	
	static public  boolean  sorpresaV3(int[] arreglo, int n)
	{
		if (arreglo== null || n < 0 || arreglo.length < n)
			throw new RuntimeException("bad parameter");
				
		List<Integer> num = new ArrayList<Integer>();
		
		return sort(arreglo,0,n-1,num);
	}
	
	 static boolean sort(int arr[], int l, int r, List<Integer> num) 
	    { 
	        if (l < r) 
	        { 
	            // Find the middle point 
	            int m = (l+r)/2; 
	  
	            // Sort first and second halves 
	           boolean hasRep = sort(arr, l, m,num) || sort(arr , m+1, r,num); 
	           
	  
	            // Merge the sorted halves 
	            return hasRep || merge(arr, l, m, r);
	        }
	        else
	        {
	        	return false;
	        }
	    }
	 
	 static boolean merge(int arr[], int l, int m, int r) 
	    { 
	        // Find sizes of two subarrays to be merged 
	        int n1 = m - l + 1; 
	        int n2 = r - m; 
	  
	        /* Create temp arrays */
	        int L[] = new int [n1]; 
	        int R[] = new int [n2]; 
	  
	        /*Copy data to temp arrays*/
	        for (int i=0; i<n1; ++i) 
	            L[i] = arr[l + i]; 
	        for (int j=0; j<n2; ++j) 
	            R[j] = arr[m + 1+ j]; 
	  
	  
	        /* Merge the temp arrays */
	  
	        // Initial indexes of first and second subarrays 
	        int i = 0, j = 0; 
	        
	        boolean ret = false;
	  
	        // Initial index of merged subarry array 
	        int k = l; 
	        while (i < n1 && j < n2) 
	        { 
	            if (L[i] <= R[j]) 
	            { 
	            	if(L[i]==R[j]) {
	            		ret = true;
	            	}
	                arr[k] = L[i]; 
	                i++; 
	            } 
	            else
	            { 
	                arr[k] = R[j]; 
	                j++; 
	            } 
	            
	            k++; 
	        } 
	  
	        /* Copy remaining elements of L[] if any */
	        while (i < n1) 
	        { 
	            arr[k] = L[i]; 
	            i++; 
	            k++; 
	        } 
	  
	        /* Copy remaining elements of R[] if any */
	        while (j < n2) 
	        { 
	            arr[k] = R[j]; 
	            j++; 
	            k++; 
	        } 
	        
	        return ret;
	    } 

}























