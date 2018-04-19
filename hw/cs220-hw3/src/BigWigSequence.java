
public class BigWigSequence {
	private String v="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890-$#_*&%/.,?\'\";:<>()[]{}!@ \r\n\t";
	public BigWigSequence() {
		
	}
	
	public int charToIndex(char c) {
		if(v.indexOf(c)==0) {
			return 0;
		}else if(v.indexOf(c)>-1) {
			int indexOf=v.indexOf(c);
			v=c+v.substring(0,indexOf)+v.substring(indexOf+1,v.length());
			//System.out.println(v);
			return indexOf;
		}else{
			return -1;
		}
		//return index of c or -1
		//moves c to beginning
		
	}
	
	public char intToChar(int i) {
			char charAtI=v.charAt(i);
			v=charAtI+v.substring(0,i)+v.substring(i+1,v.length());
			return charAtI;
		
		//returns the char at 1
		//moves c to beginning
	}
	
	public int length() {
		return v.length();
	}
	
	public char charAt(int i) {
		return v.charAt(i);
	}
	
	public boolean contains(char c) {
		return v.indexOf(c)>-1;
	}

}
