import java.util.ArrayList;
import java.util.Arrays;

public class IndexServiceWithDuplicates<T extends Comparable<? super T>> implements IndexService<T> {
	
	private T array[] ;
	
	public IndexServiceWithDuplicates (T[] array){
		this.array  = array;
		Arrays.sort(this.array, (t,o)->t.compareTo(o) );

	}

	
	

	public int SearchIndex(T key) {
		
		return FindElement(array, key, 0);
		
	}
	

	public T[] Range(T left, T right, Boolean leftClosed, Boolean rightClosed) {
		

		int leftIndx = FindCloseElement(array, left, !leftClosed, true);
		
		int rightIndx = FindCloseElement(array, right, !rightClosed, false);
		
		
		return Arrays.copyOfRange(array,leftIndx , rightIndx);
	}

	
	private static <T extends Comparable<? super T>> int FindCloseElement(T[] array, T value, Boolean isOpen, Boolean isLeft) {
		
		if(array.length == 1)
			if(isOpen)					
				return  (isLeft? 1:-1);
			else 
				return 0;
		
		T middleValue = array[array.length/2];
		T closeValue = null;
		int closeValueIndex = -1;
		
		if(isLeft && 1 + array.length/2 < array.length) {
			closeValueIndex = array.length/2 +1;
			closeValue = array[closeValueIndex];

		}else if(isLeft && 1 + array.length/2 >= array.length) {
			return array.length;
		}
		else if(!isLeft && array.length/2 - 1 >=0) {
			closeValueIndex = array.length/2 - 1;
			closeValue = array[closeValueIndex];
			
			
		}else if(!isLeft && array.length/2 - 1 <0) {
			return -1;
		}
				
		
		if(middleValue.compareTo(value) == 0) {
			if(isOpen)					
				return array.length/2 + (isLeft? 1:-1);
			else 
				return array.length/2;
		}
		else if(isLeft && middleValue.compareTo(value) < 0 && closeValue.compareTo(value) > 0 
				|| !isLeft && middleValue.compareTo(value) > 0 && closeValue.compareTo(value) < 0) {
//			if(isOpen)					
//				return closeValueIndex + (isLeft? 1:-1);
//			else 
				return closeValueIndex;
				
		}
		else if(middleValue.compareTo(value) < 0) {

			return array.length/2 + 
					FindCloseElement(Arrays.copyOfRange(array,array.length/2 , array.length)
									, value, isOpen, isLeft);
		}
		else {
			return 0 + FindCloseElement(Arrays.copyOfRange(array,0 , array.length/2)
										, value, isOpen, isLeft);
		}
		
				
	}
	
	private static <T extends Comparable<? super T>>  int FindElement(T[] array, T value, int index){
		if(array.length == 0)
			return -1;
		
		T middleValue = array[array.length/2];
		
		T newArr[] = null;

		
		if(middleValue.equals(value)) {
			return index + array.length/2;
		}
		else if(middleValue.compareTo(value) < 0) {
			index += array.length/2;
			newArr = Arrays.copyOfRange(array,array.length/2 , array.length);
			
		}else if(middleValue.compareTo(value) > 0) {
			newArr = Arrays.copyOfRange(array,0, array.length/2);
		}
		
		
		
		return FindElement(newArr, value,index);
	
	}

}
