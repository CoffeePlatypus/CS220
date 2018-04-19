
public abstract class BinaryFilter<E> implements Filter<E> {
	Filter<E> left, right;
	public BinaryFilter(Filter<E> f1,Filter<E>f2) {
		left=f1;
		right=f2;
	}
	
	public abstract boolean accepts(E e);

}
