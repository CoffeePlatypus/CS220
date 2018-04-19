
public class And<E> extends BinaryFilter<E> {

	public And(Filter<E> f1, Filter<E> f2) {
		super(f1, f2);
	}

	public boolean accepts(E e) {
		return left.accepts(e) && right.accepts(e);
	}
}
