import java.util.List;

public class All<E> implements Filter<E>{
	private List<Filter<E>> all;
	
	public All(List<Filter<E>> l1) {
		all=l1;
	}

	public boolean accepts(E e) {
		while(!all.isEmpty()) {
			if(!all.remove(0).accepts(e)) {
				return false;
			}
		}
		return true;
	}

}
