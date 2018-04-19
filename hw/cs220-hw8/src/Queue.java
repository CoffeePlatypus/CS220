import java.util.NoSuchElementException;

public	interface	Queue<E>	{
	public	boolean	enqueue(E	e);
	public	E	dequeue()	throws	NoSuchElementException;
	public	int	size();
	public	boolean	isEmpty();
	public	E	peek()	throws	NoSuchElementException;
}