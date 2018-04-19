import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class BigWig {
	private static BigWigSequence bws=new BigWigSequence();
	public static void main(String[] args) {
		try{
			if(args.length!=3) {
				System.out.println("Usage:	(-e|-d)	<input>	<output>");
			}else if(args[0].equals("-e")) {
			
				Scanner s1=new Scanner(new File(args[1]));
				s1.useDelimiter("");
				String text="";
				while(s1.hasNext()) {
					text= text+s1.next();
				}
				int[] count=new int[bws.length()];
				DataOutputStream dout= new DataOutputStream(new FileOutputStream(args[2]));
				for(int i=0;i<text.length();i++) {
					if(bws.contains(text.charAt(i))) {
						//System.out.println(text.charAt(i)+""+i);
						
						int s=bws.charToIndex(text.charAt(i));
						dout.writeShort((short)s);
						count[s]++;
						
					}
				}
				s1.close();
				dout.close();
				System.out.println("Encoding sucesssful");
				//Print histogram- number of times each number is printed
				for(int i=0;i<count.length;i++) {
					System.out.println(i+" "+count[i]);
				}
			
			}else if(args[0].equals("-d")) {
				String text="";
				DataInputStream fout=new DataInputStream(new FileInputStream(args[1]));
				try{
					while(true) {
						int s=(int)fout.readShort();
						text=text+ bws.intToChar(s);
					}
				}catch (EOFException e1){
					
				}
				PrintWriter printer= new PrintWriter(new File(args[2]));
				printer.println(text);
				printer.close();
				fout.close();
				System.out.println("Decoding Sucessful");
			}
		} catch (FileNotFoundException e1) {
			System.out.println("Error. Input file doesn't exist.");
		} catch (IOException e2) {
			System.out.println("Error creating the file.");
		}catch (IndexOutOfBoundsException e3) {
			System.out.println("Error");
		}
	}
}
