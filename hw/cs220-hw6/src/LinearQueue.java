import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinearQueue<E> implements LinearStructure<E> {
	private E[] queue;
	private int start, end, size;
	private static final int DEFAULT=100;
	
	public LinearQueue() {
		queue=(E[]) new Object[DEFAULT];
		start=0;
		end=0;
		size=0;
	}
	
	public int size() {
		return size;
	}

	public boolean add(E e) {
		if(start==end) {
			if(size!=0) {
				ensureCapacity(size+DEFAULT);
			}
		}
		queue[end]=e;
		if(end+1!=getCapacity()) {
			end++;
		}else{
			end=0;
		}
		//end=(end+1)%queue.length;
		size++;
		return true;
	}

	public void ensureCapacity(int newCap) {
		E[] temp=(E[]) new Object[newCap];
		for(int i=0; i<size; i++) {
			if(start+1>getCapacity()) {
				temp[i]=queue[start+1];
			}else{
				temp[i]=queue[end++];
			}
		}
		queue=temp;
		start=0;
		end=size;
	}

	public int getCapacity() {
		return queue.length;
	}

	
	public E remove() throws NoSuchElementException {
		if(start==end) {
			if(size==0) {
				throw new NoSuchElementException();
			}
		}
		E temp=queue[start];
		queue[start]=null;
		if(start+1==getCapacity()) {
			start=0;
		}else{
			start++;
		}
		size--;
		return temp;
	}

	public boolean isEmpty() {
		return size==0;
	}

	public E peek() throws NoSuchElementException {
		if(start==end) {
			if(size==0) {
				throw new NoSuchElementException();
			}
		}
		return queue[start];
	}

	@Override
	public void clear() {
		queue=(E[]) new Object[DEFAULT];
		start=0;
		end=0;
		size=0;
	}

	private class Queuerator implements Iterator<E> {
		int nextIndex;
		int iterated;
		
		public Queuerator() {
			nextIndex=start;
			iterated=0;
		}
		
		public boolean hasNext() {
			return iterated<size();
		}

		public E next() {
			if(!hasNext()) {
				throw new NoSuchElementException();
			}
			iterated++;
			E next=queue[nextIndex];
			if(nextIndex+1<queue.length) {
				nextIndex++;
			}else{
				nextIndex=0;
			}
			return next;

		}

		public void remove() {
			throw new UnsupportedOperationException();
		}
		
	}
	public Iterator<E> iterator() {
		return new Queuerator();
	}

}
