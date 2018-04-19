import java.util.ArrayList;

public class MispelledWordList {
	private ArrayList<MisspelledWord> wrongWords;
	
	public MispelledWordList() {
		wrongWords=new ArrayList<MisspelledWord>();
	}
	
	public boolean addWord(String word, int line) {
		MisspelledWord tempWord=new MisspelledWord(word,line);
		if(wrongWords.contains(tempWord)) {
			MisspelledWord temp=wrongWords.remove(tempWord);
			temp.again(line);
			wrongWords.add(wrongWords.size(), temp);
		}
	}
	
	public boolean contains(Object obj) {
		
	}

}
