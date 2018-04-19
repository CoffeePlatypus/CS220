import java.util.ArrayList;

public class SVGPlantCreator implements PlantCreator {
	private ArrayList<BranchParameters> branchesProperties=new ArrayList<BranchParameters>();
	private int ch, cw;
	private double ultimateTheta;
	@Override	
	public String createPlant(int canvasWidth, int canvasHeight, double k) {
		String svg= "<svg version=\"1.1\" \n     baseProfile=\"full\" \n     width=\""+canvasWidth+"\" height=\""+canvasHeight+"\" \n     xmlns=\"http://www.w3.org/2000/svg\">";
		//BranchParameters param=new BranchParametersImpl();
		ultimateTheta=0;
		ch=canvasHeight;
		cw=canvasWidth;
		//svg=svg+"\n<circle cx=\""+canvasHeight+"\" cy=\""+canvasWidth+"\" r=\"10\" fill=\"red\"/>";
		//svg=svg+createBranch(canvasWidth/2, (canvasHeight/20), ultimateTheta, canvasHeight*.6,k, branchesProperties);
		svg=svg+createBranch(canvasWidth/2,canvasHeight-(canvasHeight/20), Math.PI/2, canvasHeight*.6,k);
		svg=svg+"\n</svg>";
		return svg;
	}

	@Override
	public void addBranchParameters(BranchParameters param) {
		branchesProperties.add(param);	
	}
	
	public String createBranch(double x1,double y1, double theta, double length, double k) {
		String txt="";
		double x2,y2, width;
		width=length/20;
		x2=getX2(x1,theta,length);
		y2=getY2(y1,theta,length);
		txt=txt+"\n<line x1=\""+x2+"\" y1=\""+y2+"\" x2=\""+x1+"\" y2=\""+y1+"\" stroke=\"saddlebrown\" stroke-width=\""+width+"\"/>";
		//txt=txt+"\n<circle cx=\""+x1+"\" cy=\""+y1+"\" r=\"5\" fill=\"red\"/>";
		if(length<k) {
			txt=txt+"\n<circle cx=\""+x2+"\" cy=\""+y2+"\" r=\"5\" fill=\"green\"/>";
			return txt;
		}else{
			for(int i=0; i<branchesProperties.size();i++) {
				//BranchParameters subBranch=params.get(i);
				double position=(branchesProperties.get(i).generateRelativePosition()*length);
				ultimateTheta=theta+branchesProperties.get(i).generateTheta();
				x1=getNewX1(x2,theta, position);
				y1=getNewY1(y2,theta, position);
				txt=txt+createBranch(x1,y1, ultimateTheta,length*branchesProperties.get(i).generateScalingFactor(),k);
			}
			return txt;
		}
	}
	
	public double getNewY1(double y1, double theta, double position) {
		return ch-(y1-(Math.sin(theta)*position));
	}

	public double getNewX1(double x1, double theta, double position) {
		return (x1+(Math.cos(theta)*position));
	}

	public double getX2(double x1, double theta, double length) {
		return x1+(Math.cos(theta)*length);
	}
	
	public double getY2(double y1, double theta, double length) {
		return (y1-(Math.sin(theta)*length)); //y-
	}
	
	public double getSlope(double x1, double x2, double y1, double y2) {
		return (y2-y1)/(x2-x1);
	}
	
}
