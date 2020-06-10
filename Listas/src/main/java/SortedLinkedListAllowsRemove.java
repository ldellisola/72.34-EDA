import java.util.Iterator;

public class SortedLinkedListAllowsRemove<T extends Comparable<? super T>> 
											extends SortedLinkedList<T> {
	
	
	@Override
	
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		
	
		return new RemovableIterator(this.firstElement);
	}
	
	
	private class RemovableIterator extends SortedLinkedList<T>.SortedIterator{

		public RemovableIterator(SortedLinkedList<T>.Node start) {
			super(start);
			// TODO Auto-generated constructor stub
		}
		
		@Override
		public void remove() {
			SortedLinkedListAllowsRemove.this.delete(this.current.value);
		}
		
	}

}
