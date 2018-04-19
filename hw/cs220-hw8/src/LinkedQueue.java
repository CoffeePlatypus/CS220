import java.util.NoSuchElementException;

public class LinkedQueue<E> implements Queue<E> {
	private Node sentinel;
	private int size;
	
	public LinkedQueue() {
		sentinel= new Node(null, null, null);//value, next,prev
		sentinel.setNext(sentinel);
		sentinel.setPrevious(sentinel);
	}

	@Override
	public boolean enqueue(E e) {
		Node node=new Node(e, sentinel.getNext(),sentinel);	//Node(E value, Node next,Node previous)
		sentinel.getNext().setPrevious(node);
		sentinel.setNext(node);
		size++;
		return true;
	}

	@Override
	public E dequeue() throws NoSuchElementException {
		if(isEmpty()) {
			throw new NoSuchElementException();
		}
		E hold=sentinel.getPrevious().getValue();
		sentinel.getNext().setPrevious(sentinel.getPrevious());
		sentinel.getPrevious().setNext(sentinel.getNext());
		sentinel=sentinel.getPrevious();
		size--;
		return hold;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size()==0;
	}

	@Override
	public E peek() throws NoSuchElementException {
		if(isEmpty()) {
			throw new NoSuchElementException();
		}
		E hold=sentinel.getPrevious().getValue();
		return hold;
	}
	
	private class Node {
		private Node next;
		private Node previous;
		private E value;
		
		public Node(E value, Node next,Node previous) {
			setNext(next);
			setPrevious(previous);
			setValue(value);
		}
		
		public void setNext(Node next) {
			this.next=next;
		}
		
		private void setPrevious(Node previous) {
			this.previous=previous;
		}
		
		public void setValue(E value) {
			this.value=value;
		}
		
		public E getValue() {
			return value;
		}
		
		public Node getNext() {
			return next;
		}
		
		public Node getPrevious() {
			return previous;
		}
	}
}
