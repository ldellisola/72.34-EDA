
import java.util.Iterator;

public class SortedLinkedList<T extends Comparable<? super T>>
implements SortedListService<T>{

	protected Node firstElement;
	
	private T max,min;
	private int size = 0;

	@Override
	public void dump() {
		for (Node rec= firstElement; rec != null; rec= rec.next) {
			System.out.println(rec.value);
		}
	
	}

	@Override
	public boolean isEmpty() {
		return firstElement == null;
	}

	@Override
	public void add(T element) {
		
		if(firstElement == null) {
			min = max = element;
		}
		
		Node prev = firstElement;
		Node rec = firstElement;
				
		size++;

		while(rec != null && rec.value.compareTo(element) < 0) {
			// go on
			prev = rec;
			rec = rec.next;
		}

		// repeated?
		if(rec != null && rec.value.compareTo(element) == 0) {
			System.err.println(String.format("Insertion failed. %s repeated", element));
			return;
		}

		// does the first element change?
		if (prev == rec) {
			firstElement = new Node(element, rec);
			min = element;

		} else {
			prev.next = new Node(element, rec);
			if(rec == null) {
				max = element;
			}
		}
		


	}

	@Override
	public void delete(T element) {
		
		Node temp = firstElement;
		Node SelectedNode = null;
		size--;
		
		boolean lookingMax = false;
		boolean lookingMin = false;
		
		lookingMax =  max == element;
		lookingMin =  min == element;
		
		if(lookingMax)
			max = firstElement.value;
		
		if(lookingMin)
			min = firstElement.value;
			
		
		if(firstElement.value.compareTo(element) == 0) {
			firstElement = firstElement.next;
			
			if(firstElement != null)
				min = firstElement.value;
			
			return;
		}
			
		
		while(temp.next != null) {
			if(temp.next.value == element) {
				SelectedNode = temp;
			}
			temp = temp.next;
		}
		
		if(SelectedNode == null) {
			throw new RuntimeException();
		}
		
		
				
		SelectedNode.next = SelectedNode.next.next;
		
		if(SelectedNode.next == null)
			max = SelectedNode.value;
		
		
				
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public T getMin() {
		
		return min;
	}

	@Override
	public T getMax() {
		return max;
	}

	
	
	public final class Node {

		protected T value;
		private Node next;

		// do not accept nulls in the data
		Node(T theValue, Node theNext) {
			if (theValue == null)
				throw new RuntimeException("Null is not accepted for data");
			value = theValue;
			next = theNext;
		}

	}



	@Override
	public Iterator<T> iterator() {

		return new SortedIterator(firstElement);
	}
	
	public class SortedIterator implements Iterator<T>{
		
		public Node current;
		
		public SortedIterator(Node start) {
			current = start;
		}
		
		private boolean exit = false;

		@Override
		public boolean hasNext() {
			return exit;
		}

		@Override
		public T next() {

			T value = current.value;
			current = current.next;
			
			if(current.next == null)
				exit = true;
			
			return  value;
		}
		
	}


}



















