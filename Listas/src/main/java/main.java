import java.util.Iterator;

public class main {

	public static void main(String[] args) {
		
		int[] arr = {5,2,2,6,8,54,9,5,4,2,46,9,0,0,8,64,3};
		
		MergeSort(arr, 0, arr.length-1);
		
		for(int i = 0 ; i < arr.length ; i++) {
			System.out.println(arr[i]);
		}
		
//		SortedLinkedListAllowsRemove<Integer> list = new SortedLinkedListAllowsRemove<>();
//		
//		list.add(1);
//		list.add(2);
//		list.add(3);
//		list.add(4);
//		list.add(5);
//		list.add(6);
//		list.add(7);
//
////		for (Integer integer : list) {
////			System.out.println(integer);
////		}
////		
//		System.out.println(list.getMax());
//		System.out.println(list.getMin());
//		
//		list.add(8);
//		list.add(0);
//		
//		
//		System.out.println(list.getMax());
//		System.out.println(list.getMin());
//		
//		Integer num = 0 ;
//		Iterator<Integer> it = list.iterator();
//		
//		do {
//			num = it.next();
//			if(num % 2 == 0) {
//				System.out.println("Borro: " + num);
//				it.remove();
//			}
//		}while(it.hasNext());
//		

		

	}
	
	static public void MergeSort(int[] arr, int first, int last) {
		
		if(last-first <=1) {
			if(arr[first] > arr[last]) {
				int a = arr[first];
				arr[first] = arr[last];
				arr[last] = a;
			}
			return;
		}
		
		
		int pivot = (first + last )/2;
		
		MergeSort(arr, first, pivot);
		MergeSort(arr,pivot+1, last);
		
		if(arr[pivot -1] > arr[pivot+1]) {
			int[] temp = new int[pivot -1 - first];
			
			for(int i = first; i < pivot-1; i++)
				temp[i-first] = arr[i];
			
			for(int i = pivot-1; i <=last ; i++) {
				arr[i-(pivot-1)] = arr[i];
			}
			
			for(int i = last - (pivot -1) ; i <= last; i++) {
				arr[i] = temp[i- (last-(pivot-1))];
			}
			
			
		}
		
		
		
		
		
		
		
	}
	
	
	static public void QuickSort(int[] arr, int first, int last) {
		
		if(first >= last) {
			return;
		}
		
		int pivot = arr[last];
		
		int i = first -1;
		
		for(int j = first; j < last ;j++) {
			if(arr[j] < pivot) {
				i++;
				int a = arr[i];
				arr[i] = arr[j];
				arr[j] = a;
			}
		}
		
		int a = arr[i+1];
		arr[i+1] = arr[last];
		arr[last] = a;
		
		int pivotPoint = i+1;
						
		QuickSort(arr, first, pivotPoint-1);
		QuickSort(arr, pivotPoint+1,last);
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}









