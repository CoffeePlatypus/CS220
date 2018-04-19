import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class WordSearch {
	
	public static void main(String args[]) {
		try {
			//test();
			Scanner scanner=new Scanner(new File( args[0] ));
			if(!checkFile(scanner)) {
				int[] error= new int[1];
				error[3]=1;
			}else if(args.length>2) {
				String[] error=new String[1];
				error[0].toLowerCase();
			}
			char[][] puzzle=makePuzzle(scanner);
			PrintWriter printer=new PrintWriter(args[1]);
			solvePuzzle(puzzle, scanner,printer);
			printer.close();
			scanner.close();
		}catch (FileNotFoundException e1) {
			System.out.println("Error. The word-search file does not exist");
		}catch (ArrayIndexOutOfBoundsException e2){
			System.out.println("Error reading the word-search file");
		}catch (NullPointerException e3) {
			System.out.println("Usage: <word-search> <output>");
		}
		catch (Exception e) {
			System.out.println("Error creating the output file");
		}
	}
	public static void solvePuzzle(char[][] puzzle, Scanner scanner, PrintWriter printer) {
		String solution="";
		while(scanner.hasNext()) {
			String next=scanner.next();
			solution=wordPosition(puzzle,(next.toUpperCase()));
			//System.out.println(solution);
			printer.println(solution);
		}
	}
	public static char[][] makePuzzle(Scanner scanner){
		int rows=0;
		int cols=0;
		if(scanner.hasNextInt()) {
			cols=scanner.nextInt();
		}
		if(scanner.hasNextInt()) {
			rows=scanner.nextInt();
		}
		System.out.println(rows+" "+cols);
		char[][] puzzle=new char[rows][cols];
		String line="";
		int k=0;
		for(int i=0; i<rows; i++) {
			if(!scanner.hasNext()) {
				//int[] error= new int[1];
				//error[3]=1;
			}
			line=scanner.next();
			if(line.length()!=cols) {
				int[] error= new int[1];
				error[3]=1;
			}
			for(int j=0; j<cols && j<line.length();j++) {
				puzzle[i][j]=line.charAt(j);
				
			}
			k=i;
		}
		return puzzle;
	}
	public static boolean checkFile(Scanner scanner) {
		if(!scanner.hasNext()) {
			return false;
		}else if(scanner.next().equals("#WS-FILE")) {
			return true;
		}else{
			return false;
		}
	}
	public static void test() {
		char[][] puzzle={{'j','u','l','i','a'},
				 {'a','k','l','a','w'},
				 {'m','i','a','p','a'},
				 {'r','l','t','l','o'},
				 {'t','l','u','o','e'}};
	System.out.println(wordPosition(puzzle,"julia"));
	System.out.println(wordPosition(puzzle,"walk"));
	System.out.println(wordPosition(puzzle,"am"));
	System.out.println(wordPosition(puzzle,"tia"));
	System.out.println(wordPosition(puzzle,"tal"));
	System.out.println(wordPosition(puzzle,"kale"));
	System.out.println(wordPosition(puzzle,"ril"));
	System.out.println(wordPosition(puzzle,"lir"));
	}
	public static String wordPosition(char[][] puzzle, String word) {
		for(int i=0; i<puzzle.length; i++) {
			for(int j=0; j<puzzle[i].length;j++) {
				if(word.charAt(0)==puzzle[i][j]) {
					if(checkRight(i,j, word, puzzle)) {
						return word +" ("+(i+1)+", "+(j+1)+") Right"+'\n';
					}else if(checkDownRight(i,j,word,puzzle)) {
						return word +" ("+(i+1)+", "+(j+1)+") Down-Right"+'\n';
					}else if(checkDown(i,j,word,puzzle)) {
						return word +" ("+(i+1)+", "+(j+1)+") Down"+'\n';
					}else if(checkDownLeft(i,j,word, puzzle)) {
						return word +" ("+(i+1)+", "+(j+1)+") Down-Left"+'\n';
					}else if(checkLeft(i,j,word, puzzle)) {
						return word +" ("+(i+1)+", "+(j+1)+") Left"+'\n';
					}else if(checkUpLeft(i,j,word,puzzle)) {
						return word +" ("+(i+1)+", "+(j+1)+") Up-Left"+'\n';
					}else if(checkUp(i,j,word,puzzle)) {
						return word +" ("+(i+1)+", "+(j+1)+") Up"+'\n';
					}else if(checkUpRight(i,j,word,puzzle)) {
						return word +" ("+(i+1)+", "+(j+1)+") Up-Right"+'\n';
					}
				}
				//System.out.println(i+","+j);
			}		
		}
		return word+" Not Present"+'\n';			
	}	
	public static boolean checkDownLeft(int i, int j, String word, char[][] puzzle) {
		int indexOfWord=1;
		for(int k=i+1, l=j-1; k<puzzle.length && l>=0 && indexOfWord<word.length(); k++, l--) {
			if(word.charAt(indexOfWord)!=puzzle[k][l]) {
				return false;
			}
			indexOfWord++;
		}
		if(indexOfWord!=word.length()) {
			return false;
		}
		return true;
	}
	public static boolean checkUpLeft(int i, int j, String word, char[][] puzzle) {
		int indexOfWord=1;
		for(int k=i-1, l=j-1; k>=0 && l>=0 && indexOfWord<word.length(); k--, l--) {
			if(word.charAt(indexOfWord)!=puzzle[k][l]) {
				return false;
			}
			indexOfWord++;
		}
		if(indexOfWord!=word.length()) {
			return false;
		}
		return true;
	}
	public static boolean checkDownRight(int i, int j, String word, char[][] puzzle) {
		int indexOfWord=1;
		for(int k=i+1, l=j+1; k<puzzle.length && l<puzzle[k].length && indexOfWord<word.length(); k++, l++) {
			if(word.charAt(indexOfWord)!=puzzle[k][l]) {
				return false;
			}
			indexOfWord++;
		}
		if(indexOfWord!=word.length()) {
			return false;
		}
		return true;
	}
	public static boolean checkUpRight(int i, int j, String word, char[][] puzzle) {
		int indexOfWord=0;
		for(int k=i, l=j; k>0 && l<puzzle[k].length && indexOfWord<word.length();k--, l++){
			if(word.charAt(indexOfWord)!=puzzle[k][l]) {
				return false;
			}
			indexOfWord++;
		}
		if(indexOfWord!=word.length()) {
			return false;
		}
		return true;
	}
	public static boolean checkDown(int i, int j, String word, char[][] puzzle) {
		int indexOfWord=1;
		for(int k=i+1; k<puzzle.length && indexOfWord<word.length();k++) {
			if(word.charAt(indexOfWord)!=puzzle[k][j]) {
				return false;
			}
			indexOfWord++;
		}
		if(indexOfWord!=word.length()){
			return false;
		}
		return true;
	}
	public static boolean checkUp(int i, int j, String word, char[][] puzzle) {
		int indexOfWord=1;
		for(int k=i-1; k>0 && indexOfWord<word.length(); k--){
			if(word.charAt(indexOfWord)!=puzzle[k][j]) {
				return false;
			}
			indexOfWord++;
		}
		if(indexOfWord!=word.length()) {
			return false;
		}
		return true;
	}
	public static boolean checkLeft(int i, int j, String word, char[][] puzzle) {
		int indexOfWord=0;
		for(int l=j; l>0 && indexOfWord<word.length(); l--) {
			if(word.charAt(indexOfWord)!=puzzle[i][l]) {
				return false;
			}
			indexOfWord++;
		}
		if(indexOfWord!=word.length()) {
			return false;
		}
		return true;
	}
	public static boolean checkRight(int i, int j, String word, char[][] puzzle) {
		int indexOfWord=0;
		for(int l=j; l<puzzle[i].length && indexOfWord<word.length(); l++) {
			if(word.charAt(indexOfWord)!=puzzle[i][l]){
				return false;
			}
			indexOfWord++;
		}
		if(indexOfWord!=word.length()) {
			return false;
		}
		return true;
	}
}