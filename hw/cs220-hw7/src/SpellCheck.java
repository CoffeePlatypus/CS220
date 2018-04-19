import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.text.html.HTMLDocument.Iterator;

public class SpellCheck {
	private static Dictionary dictionary;
	private static ArrayList<MisspelledWord> wrongWords= new ArrayList<MisspelledWord>();
	
	public static void main(String[] args) {
		//git\cs220\cs220-hw7\bin>java SpellCheck C:\temp\dictionary1.txt C:\temp\test1.txt 2
		try {
			Long start=System.currentTimeMillis();
			File dictionaryFile= new File(args[0]);
			File documentFile= new File(args[1]);
			//System.out.println(dictionaryFile.exists()+" docFile "+documentFile.exists());
			int length=Integer.parseInt(args[2]);
			makeDictionary(dictionaryFile);
			//System.out.println("Made Dictionary");
			checkFiles(documentFile,length);
			//System.out.println("Checked Files");
			sortWords();
			//System.out.println("sortedFiles");
			PrintWords();
			Long finish=System.currentTimeMillis();
			System.out.println("Time "+(finish-start)+" Milli Seconds");
			
		} catch (IOException e) {
			System.out.println("File Error");
		}
	}

	public static void PrintWords() {
		for(int i=0;i<wrongWords.size();i++) {
			System.out.println(wrongWords.get(i));
		}
	}

	public static void sortWords() {
		for (int i=1; i<wrongWords.size(); i++) {
			MisspelledWord currentValue = wrongWords.get(i);
		    int j = i-1;
		    while (j >= 0 && wrongWords.get(j).compareTo(currentValue)<0) {
		    	wrongWords.set(j+1, wrongWords.get(j));
		        j--;
		    }
		    wrongWords.set(j+1, currentValue);
		}
	}

	public static void checkFiles(File documentFile, int length)throws IOException {
		BufferedReader rin= new BufferedReader(new FileReader(documentFile));
		String line=rin.readLine();
		int lineNumber=0;
		while(line!=null) {
			lineNumber++;
			//if(lineNumber%1000==0){
				//System.out.println("Line "+lineNumber);
			//}
			Scanner scanner= new Scanner(line);
			scanner.useDelimiter("[^a-zA-Z\\'\\-]");
			while(scanner.hasNext()) {
				String word=scanner.next();
				if(word.length()>length && dictionary.isMispelled(word)){
					MisspelledWord wrong = new MisspelledWord(word,lineNumber);
					if(wrongWords.contains(wrong)){
						int index =wrongWords.indexOf(wrong);
						MisspelledWord original=wrongWords.get(index);
						original.again(lineNumber);
						wrongWords.set(index, original);
					}else{
						wrongWords.add(wrong);
					}
				}
			}
			scanner.close();
			line=rin.readLine();
		}
		rin.close();
	}

	public static void makeDictionary(File dictionaryFile) throws IOException {
		ArrayList<String> words=new ArrayList<String>();
		BufferedReader fin = new BufferedReader(new FileReader(dictionaryFile));
		String line= fin.readLine();
		while(line!=null) {
			words.add(line);
			line=fin.readLine();
		}
		dictionary=new Dictionary(words);
		fin.close();
	}
}
