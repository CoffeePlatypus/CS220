
public	interface	Schedulable	{
	public void takeTurn(int atTime);
	public boolean isCompleted();
	public int getTurnsTaken();
	public int getTicksRequiredToComplete();
	public String getName();
	public void setStartOfTimeInScheduler(int time);
	public int getStartOfTimeInScheduler();
	public int getTimeToComplete();
	public int getTimeCompleted();
}