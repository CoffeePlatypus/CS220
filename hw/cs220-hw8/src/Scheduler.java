
public	interface	Scheduler {
	public void add( Schedulable s );
	public Schedulable tick();
	public int getTime();
	public int size();
	public int getAllotment(); 
	public void setAllotment(int allotment);
}