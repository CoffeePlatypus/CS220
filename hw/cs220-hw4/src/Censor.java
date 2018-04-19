
public class Censor<E> implements Filter<E>{
	private String[] censoredWords;
	public Censor(String[] s) {
		censoredWords=s;
	}

	@Override
	public boolean accepts(E e) {
		for(int i=0;i<censoredWords.length;i++) {
			Contains<E> badWord=new Contains<E>(censoredWords[i]);
			if(badWord.accepts(e)) {
				return false;
			}
		}
		return true;
	}

}
