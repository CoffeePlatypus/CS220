
public class Process implements Schedulable {
	private int ticksToComplete;
	private int ticks; //////////////a turn is a tick
	private int atTime;
	private int startTime;
	private String name;
	
	public Process(String name,int ticksRequiredToComplete) {
		ticksToComplete=ticksRequiredToComplete;
		ticks=0;
		this.name=name;
	}

	@Override
	public void takeTurn(int atTime) { //to keep track of global time;
		ticks++;
		this.atTime=atTime;

	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name=name;
	}

	@Override
	public boolean isCompleted() {
		return ticks==ticksToComplete;
	}

	@Override
	public int getTurnsTaken() {
		return ticks;
	}

	@Override
	public int getTicksRequiredToComplete() {
		return ticksToComplete;
	}

	@Override
	public void setStartOfTimeInScheduler(int time) {  //set time??
		startTime=time;

	}

	@Override
	public int getStartOfTimeInScheduler() {
		return startTime;
	}

	@Override
	public int getTimeToComplete() {
		if(isCompleted()) {
			return getTimeCompleted()-getStartOfTimeInScheduler();
		}
		return -1;
	}

	@Override
	public int getTimeCompleted() { // how can you know the time it takes to complete before it is completed?
		if(isCompleted()) {
			return atTime;
		}
		return -1;
	}

	public String toString() {
		return getName() + " " + getStartOfTimeInScheduler() + " " + getTimeCompleted() +" " + getTicksRequiredToComplete() +" "+ getTimeToComplete();
	}
	
	public int compareTo(Process other) {
		if(getStartOfTimeInScheduler()==other.getStartOfTimeInScheduler()) {
			return 0;
		}else if(getStartOfTimeInScheduler()<other.getStartOfTimeInScheduler()) {
			return -1;
		}else{
			return 1;
		}
		
	}
}
