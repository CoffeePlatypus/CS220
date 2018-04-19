import java.util.ArrayList;

public class MisspelledWord {
	private String notWord;
	private int ocurrences;
	private ArrayList<Integer> onLines=new ArrayList<Integer>();
	
	public MisspelledWord(String word, int line) {
		notWord=word;
		ocurrences++;
		onLines.add(line);
	}
	
	public String getNotWord() {
		return notWord;
	}
	
	public int getOcurrences() {
		return ocurrences;
	}
	
	public void again(int line) {
		ocurrences++;
		onLines.add(line);
	}
	
	public String toString() {
		return notWord+ "("+ocurrences+") "+ printLineNumbers();
	}

	public String printLineNumbers() {
		String result="";
		for(int i=0; i<onLines.size(); i++) {
			if(i==0) {
				result=onLines.get(i).toString();
			}else {
				result=result+" , "+ onLines.get(i).toString();
			}
		}
		return result;
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof MisspelledWord) {
			return notWord.equals(((MisspelledWord)obj).getNotWord());
		}
		return false;
	}
	
	public int compareTo(MisspelledWord other) {
		if(ocurrences==other.getOcurrences()) {
			return notWord.compareTo(other.getNotWord());
		}else if(ocurrences>other.getOcurrences()) {
			return 1;
		}else{
			return -1;
		}
		
	}

}
