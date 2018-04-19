
public class BranchParametersImpl implements BranchParameters { 
	private double position, theta, scalingFactor, positionRange, thetaRange, scalingFactorRange,relativePosition, relativeTheta, relativeScalingFactor;
	
	//public BranchParametersImpl( double position, double theta, double scalingFactor) {
		//this.position=position;
		//this.theta=theta;
		//this.scalingFactor=scalingFactor;
		//positionRange=0; thetaRange=0; scalingFactorRange=0;
	//}

	@Override
	public void setRelativePosition(double position, double range) {  
		this.position=position;
		positionRange=range;
	}

	@Override
	public void setTheta(double theta, double range) {
		this.theta=theta;
		thetaRange=range;
	}

	@Override
	public void setScalingFactor(double sf, double range) {
		scalingFactor=sf;
		scalingFactorRange=range;
	}

	@Override
	public double generateRelativePosition() {
		double a=(position-positionRange);
		relativePosition=Math.random()*(2*positionRange)+a;
		return relativePosition;
	}

	@Override
	public double generateTheta() {
		double a=theta-thetaRange;
		relativeTheta=Math.random()*(2*thetaRange)+a;
		return relativeTheta;
	}

	@Override
	public double generateScalingFactor() {
		double a=scalingFactor-scalingFactorRange;
		relativeScalingFactor=Math.random()*(2*scalingFactorRange)+a;
		return relativeScalingFactor;
	}

	@Override
	public double getRelativePosition() {
		return position;
	}

	@Override
	public double getTheta() {
		return theta;
	}

	@Override
	public double getScalingFactor() {
		return scalingFactor;
	}

	@Override
	public double getRelativePositionRange() {
		return positionRange;
	}

	@Override
	public double getThetaRange() {
		return thetaRange;
	}

	@Override
	public double getScalingFactorRange() {
		return scalingFactorRange;
	}

}
