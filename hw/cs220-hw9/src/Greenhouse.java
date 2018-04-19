import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Greenhouse {

	public static void main(String[] args) {  
		try{
			BufferedReader rin= new BufferedReader(new FileReader(args[0]));
			String line1=rin.readLine();
			Scanner scanner=new Scanner(line1);
			int width=scanner.nextInt();
			int height=scanner.nextInt();
			int k=scanner.nextInt();
			scanner.close();
			SVGPlantCreator plant=new SVGPlantCreator();
			String nextLine=rin.readLine();
			while(nextLine!=null) {
				Scanner scanner2=new Scanner(nextLine);
				double position=scanner2.nextDouble();
				double positionRange=scanner2.nextDouble();
				double theta=scanner2.nextDouble();
				double thetaRange=scanner2.nextDouble();
				double scalingFactor=scanner2.nextDouble();
				double	scalingFactorRange=scanner2.nextDouble();
				scanner2.close();
				BranchParameters branchType= new BranchParametersImpl();
				branchType.setRelativePosition(position, positionRange);
				branchType.setTheta(-theta, thetaRange);
				branchType.setScalingFactor(scalingFactor, scalingFactorRange);
				plant.addBranchParameters(branchType);
				nextLine=rin.readLine();
			}
			rin.close();
			//plant.addBranchParameters(branchType);
			System.out.println(plant.createPlant(width, height, k));
			
			
		} catch (IOException e) {
			System.out.println("File Error");
		}
		
		
	}

}
