
public class AsLongAs<E> implements Filter<E>{
	private int num;
	public AsLongAs(int n) {
		num=n;
	}
	
	public boolean accepts(E e) {
		return e.toString().length()>=num;
	}

}
