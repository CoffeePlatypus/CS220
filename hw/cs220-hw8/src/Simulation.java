import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Simulation {
	private static int allotment;
	
	public static void main(String[] args) {
		try {
			File inputFile= new File(args[0]);
			//System.out.println(inputFile.exists());
			ArrayList<Process> processes=readFile(inputFile);
			//System.out.println("File processes");
			mergeSort(processes);
			//System.out.println(processes);
			RoundRobinScheduler yum= new RoundRobinScheduler();
			yum.setAllotment(allotment);
			//ArrayList<Schedulable> completed=new ArrayList<Schedulable>();
			while((processes.size()!=0) || (yum.size()!=0)) {
				int time=yum.getTime();
				//System.out.println("Time " +time);
				while(processes.size()>0 && processes.get(0).getStartOfTimeInScheduler()==time) {
					yum.add(processes.remove(0));	
				}
				Schedulable done=yum.tick();
				if(done!=null) {
					//completed.add(done);
					System.out.println(done);
				}
			}
			
		} catch (IOException e) {
			System.out.println("File Error");
		}
	}
	
	public static void merge(List<Process> left, List<Process> right, List<Process> result) {
		while(!left.isEmpty() && !right.isEmpty()) {
			Process n1=left.get(0);
			Process n2=right.get(0);
			
			if(n1.compareTo(n2)<0) {
				result.add(left.remove(0));
			}else{
				result.add(right.remove(0));
			}
		}
		while(!left.isEmpty()) {
			result.add(left.remove(0));
		}
		while(!right.isEmpty()) {
			result.add(right.remove(0));
		}	
	}
	
	public static void mergeSort(List<Process> list) {
		if(list.size()<=1) {
			return;
		}
		
		List<Process> left=new LinkedList<Process>();
		List<Process> right=new LinkedList<Process>();
		int half=list.size()/2;
		
		for(int i=0;i<half;i++) {
			left.add(list.remove(0));
		}
		
		while(!list.isEmpty()) {
			right.add(list.remove(0));
		}
		
		mergeSort(left);
		mergeSort(right);
		merge(left, right, list);
	}

	public static ArrayList<Process> readFile(File inputFile) throws IOException {
		ArrayList<Process> processes=new ArrayList<Process>();
		BufferedReader rin= new BufferedReader(new FileReader(inputFile));
		allotment=Integer.parseInt(rin.readLine());
		String line=rin.readLine();
		while(line!=null) {
			Scanner scanner= new Scanner(line);
			Process one=new Process(scanner.next(),scanner.nextInt());
			one.setStartOfTimeInScheduler(scanner.nextInt());
			processes.add(one);
			scanner.close();
			line=rin.readLine();
		}
		rin.close();
		return processes;
	}

}
