
public class ContainsAll<E> implements Filter<E>{
	private String letters;
	public ContainsAll(String s) {
		letters=s;
	}

	public boolean accepts(E e) {
		for(int i=0;i<letters.length();i++) {
			if(e.toString().indexOf(letters.charAt(i))<0) {
				return false;
			}
		}
		return true;
	}

}
