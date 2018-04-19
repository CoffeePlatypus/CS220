import java.util.ArrayList;

public class Dictionary {
	private ArrayList<String> words;
	
	public Dictionary(ArrayList<String> w) {
		words=w;
	}
	
	public boolean isMispelled(String word) {
		return !words.contains(word);
	}
	

}
