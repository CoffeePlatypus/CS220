package hw5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Cruncher {
	
	public static void main(String args[]) {
		try {
			ArrayList<Prestidigitation> listOfNumbers=new ArrayList<Prestidigitation>();
			ArrayList<String> operations= new ArrayList<String>();
			Prestidigitation result = new Prestidigitation(0);
			Scanner scanner=new Scanner(new File( args[0] ));
			listOfNumbers.add(new Prestidigitation(scanner.nextLine()));
			listOfNumbers.add(new Prestidigitation(scanner.nextLine()));
			operations.add(scanner.next());
			while(scanner.hasNextLine()) {
				listOfNumbers.add(new Prestidigitation(scanner.nextLine()));
				operations.add(scanner.next());
			}
			scanner.close();
			switch(operations.get(0)) {
			//to
			case "+": result=(Prestidigitation) listOfNumbers.get(0).add(listOfNumbers.get(1)); break;
			case "*": result=(Prestidigitation) listOfNumbers.get(0).mul(listOfNumbers.get(1)); break;
			case "^": result=(Prestidigitation) listOfNumbers.get(0).pow(listOfNumbers.get(1)); break;
			}
			for(int i=1,j=2; i<operations.size() && j<listOfNumbers.size();i++,j++){
				switch(operations.get(i)) {
				case "+": result=(Prestidigitation) result.add(listOfNumbers.get(j)); break;
				case "*": result=(Prestidigitation) result.mul(listOfNumbers.get(j)); break;
				case "^": result=(Prestidigitation) result.pow(listOfNumbers.get(j)); break;
				}
			}
			System.out.println(result.toSting());

		} catch (FileNotFoundException e) {
			System.out.println("FileNotFound");
		} catch (IllegalArgumentException e) {
			System.out.println("Illegal Argument");
		} catch (UndefinedValueException e) {
			System.out.println("Undefined");
		}	
	}
}
