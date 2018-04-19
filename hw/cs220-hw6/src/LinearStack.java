import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinearStack<E> implements LinearStructure<E> {
	private E[] stack;
	private int top;
	private static final int DEFAULT=100;
	
	public LinearStack() {
		stack=(E[]) new Object[DEFAULT];
		top=0;
	}
	
	public int size() {
		return top;
	}

	public boolean add(E e) {
		if(top+1==getCapacity()) {
			ensureCapacity(top+DEFAULT);
		}
		stack[top++]=e;
		return true;
	}

	public void ensureCapacity(int newCap) {
		E[] temp=(E[]) new Object[newCap];
		for(int i=0;i<getCapacity();i++) {
			temp[i]=stack[i];
		}
		stack=temp;
	}

	public int getCapacity() {
		return stack.length;
	}

	public E remove() throws NoSuchElementException {
		if(size()==0) {
			throw new NoSuchElementException();
		}else{
			E atTop=stack[top-1];
			stack[top--]=null;
			return atTop;
		}
	}

	public boolean isEmpty() {
		return size()==0;
	}

	public E peek() throws NoSuchElementException {
		if(size()==0) {
			throw new NoSuchElementException();
		}else{
			return stack[top];
		}
	}

	public void clear() {
		stack=(E[]) new Object[DEFAULT];
		top=0;
	}
	private class Stackerator implements Iterator<E> {
		int nextIndex;
		
		public Stackerator() {
			nextIndex=0;
		}
		
		public boolean hasNext() {
			return nextIndex<size();
		}

		public E next() {
			if(!hasNext()) {
				throw new NoSuchElementException();
			}
			return stack[nextIndex++];
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}
		
	}
	public Iterator<E> iterator() {
		return new Stackerator();
	}
}
