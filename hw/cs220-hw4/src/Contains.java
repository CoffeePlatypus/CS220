
public class Contains<E> implements Filter<E>{
	private String word;
	public Contains(String s) {
		word=s.toUpperCase();
	}
	public boolean accepts(E e) {
		String text=e.toString().toUpperCase();
		for(int i=0;i<text.length();i++) {
			if(text.charAt(i)==word.charAt(0)) {
				if(check(i,text)) {
					return true;
				}
			}	
		}
		return false;
	}
	private boolean check(int i, String text) {
		i++;
		int j;
		for(j=1;j<word.length() && i<text.length(); i++,j++) {
			if(text.charAt(i)!=word.charAt(j)) {
				return false;
			}
		}
		if(j<word.length()) {
			return false;
		}else{
			return true;
		}
	}

}
