import java.util.Iterator;
import java.util.NoSuchElementException;

public interface LinearStructure<E> {
	public int size();
	public boolean add(E e);
	public E remove() throws NoSuchElementException;
	public boolean isEmpty();
	public E peek() throws NoSuchElementException;
	public void clear();
	public Iterator<E> iterator();
}