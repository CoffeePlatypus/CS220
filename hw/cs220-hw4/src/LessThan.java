
public class LessThan<E> implements Filter<E>{
	private String less;
	public LessThan(String s) {
		less=s;
	}
	
	public boolean accepts(E e) {
		return (less.compareTo(e.toString().toUpperCase())>0);
	}

}
