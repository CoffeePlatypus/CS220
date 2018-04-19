
public interface BranchParameters {
	public void	setRelativePosition(double position, double range);
	public void	setTheta(double theta, double range);
	public void	setScalingFactor(double sf, double range);
	public double generateRelativePosition();
	public double generateTheta();
	public double generateScalingFactor();
	public double getRelativePosition();
	public double getTheta();
	public double getScalingFactor();
	public double getRelativePositionRange();
	public double getThetaRange();
	public double getScalingFactorRange();
}
