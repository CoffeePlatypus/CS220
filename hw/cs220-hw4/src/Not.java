
public class Not<E> implements Filter<E>{
	private Filter<E> reference;
	public Not(Filter<E> f) {
		reference=f;
	}

	@Override
	public boolean accepts(E e) {
		return !(reference.accepts(e));
	}

}
