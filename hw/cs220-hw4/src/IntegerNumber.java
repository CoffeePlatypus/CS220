
public class IntegerNumber<E> implements Filter<E>{

	@Override
	public boolean accepts(E e) {
		try{
			Integer.decode(e.toString());
			return true;
		}catch(NumberFormatException e1) {
			return false;
		}
	}

}
