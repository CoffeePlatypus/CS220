
public class RoundRobinScheduler implements Scheduler {
	private LinkedQueue<Schedulable> line=new LinkedQueue<Schedulable>();
	private int globalTime;
	private int allotment;
	private Schedulable processing; 
	
	public RoundRobinScheduler() {
		globalTime=0;
		allotment=1;
		processing=null;
	}
	
	@Override
	public void add(Schedulable individual) {
		line.enqueue(individual);
	}

	@Override
	public Schedulable tick() {/////////////// returns Scheduable completeted then
		if(size()==0){
			globalTime++;
			return null;
		}else if(processing==null) {
			processing=line.dequeue();
		}
		processing.takeTurn(globalTime);
		globalTime++;
		if(processing.isCompleted()) {
			Schedulable temp=processing;
			processing=null;
			return temp;
		}else if(processing.getTurnsTaken()%getAllotment()==0 && (size()!=0)) {
			line.enqueue(processing);
			processing=null;
		}
		return null;
	}

	@Override
	public int getTime() {
		return globalTime;
	}

	@Override
	public int size() {
		if(processing==null) {
			return line.size();
		}else{
			return line.size()+1;
		}
	}

	@Override
	public int getAllotment() { // Im confused by Allotment?
		return allotment;
	}

	@Override
	public void setAllotment(int allotment) {
		this.allotment=allotment;
	}

}
