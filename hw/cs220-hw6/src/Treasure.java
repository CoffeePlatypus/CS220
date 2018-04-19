import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Treasure {
	
	public static void main(String args[]) {
		try {
			File f=new File(args[0]);
			//System.out.println(f.exists()); The problem is I the file doesn't and I don't know why;
			if(args[1]=="STACK") {
				Map map=new Map(f);
				LinearStack<Location> stack=new LinearStack<Location>();
				ArrayList<Direction> ultimatePath=map.path(stack);
				if(ultimatePath!=null) {
					System.out.println(printDirections(ultimatePath));
				}else{
					System.out.println("No Path");
				}
				
			}else{
				Map map=new Map(f);
				LinearQueue<Location> queue=new LinearQueue<Location>();
				ArrayList<Direction> ultimatePath=map.path(queue);
				System.out.println(printDirections(ultimatePath));
			}
		}catch (IOException e){
			System.out.println("Error reading file");
		}
	}
	
	public static String printDirections(ArrayList<Direction> ultimatePath) {
		String go=""+ultimatePath.get(0);
		for(int i=1; i<ultimatePath.size(); i++) {
			go= go+ ", "+ultimatePath.get(i);
		}
		return go;
	}

}
