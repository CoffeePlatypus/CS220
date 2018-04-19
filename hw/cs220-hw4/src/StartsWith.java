
public class StartsWith<E> implements Filter<E> {
	private String pre;
	public StartsWith(String s) {
		pre=s;
	}
	public boolean accepts(E e) {
		return e.toString().toUpperCase().startsWith(pre.toUpperCase());
	}

}
