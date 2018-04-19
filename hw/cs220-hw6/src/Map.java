import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Map {
	private char[][] mapGrid;
	//private Location end;
	public Map(File f) throws IOException {
		try{
			System.out.println("1");
			Scanner scanner=new Scanner(new FileReader(f));
			System.out.println("1");
			int height=scanner.nextInt();
			int width=scanner.nextInt();
			mapGrid=new char[height][width];
			System.out.println("1");
			for(int i=0; i<height; i++) {
				String line=scanner.nextLine();
				for(int j=0; j<width; j++) {
					mapGrid[i][j]=line.charAt(j);
				}
			}
			scanner.close();
		}catch (IOException e1) {
			throw new IOException();
		}
	}
	
	public Map(char[][] map) {
		mapGrid=map;
		//end=null;
		
	}
	
	public int getWidth() {
		return mapGrid[0].length;
	}
	
	public int getHeight() {
		return mapGrid.length;
	}
	
	public boolean isExit(Location pt) {
		return mapGrid[pt.getRow()][pt.getColumn()]=='^';
	}
	
	public boolean isStart(Location pt) {
		return mapGrid[pt.getRow()][pt.getColumn()]=='$';
	}
	
	public boolean isTreasure(Location pt) {
		return mapGrid[pt.getRow()][pt.getColumn()]=='!';
	}
	
	public boolean isWall(Location pt) {
		return mapGrid[pt.getRow()][pt.getColumn()]=='#';
	}
	
	public boolean isTunnle(Location pt) {
		return mapGrid[pt.getRow()][pt.getColumn()]=='_';
	}
	
	public ArrayList<Location> getExits() {// use ArrayList
		ArrayList<Location> exits= new ArrayList<Location>();
		for(int i=0; i<getHeight(); i++) {
			for(int j=0; j<getWidth(); j++) {
				Location atIJ= new Location(i,j, null, null);
				if(isExit(atIJ)) {
					exits.add(atIJ);
				}
			}
		}
		return exits;
	}
	
	public ArrayList<Location> getTreasures(){
		ArrayList<Location> treasures= new ArrayList<Location>();
		for(int i=0; i<getHeight(); i++) {
			for(int j=0; j<getWidth(); j++) {
				Location atIJ= new Location(i,j, null, null);
				if(isTreasure(atIJ)) {
					treasures.add(atIJ);
				}
			}
		}
		return treasures;
	}
	public Location getStart() {
		for(int i=0; i<getHeight(); i++) {
			for(int j=0; j<getWidth(); j++) {
				Location atIJ= new Location(i,j, null, null);
				if(isStart(atIJ)) {
					return atIJ;
				}
			}
		}
		return null;
	}
	
	public ArrayList<Direction> path(LinearStructure<Location> c) {// use ArrayList
		Location start=getStart();
		return path(start,c);
		
	}
	
	public ArrayList<Direction> path(Location start, LinearStructure<Location> hold, char whatToFind) {
		hold.clear();
		ArrayList<Direction> pathDirections= new ArrayList<Direction>();
		ArrayList<Location> visited= new ArrayList<Location>();
		//System.out.println(whatToFind);
		//System.out.println("Start"+start);
		hold.add( start );
		while(!hold.isEmpty()) {
			Location current = hold.remove();
			//System.out.println("current "+current);
			if(isWhatToFind(whatToFind,current)) {
				pathDirections.addAll(current.getDirectionsToHere());
				return pathDirections;
			}
			if(!isWall(current) && !visited.contains(current)) {
				//System.out.println(current.getColumn()+1<getWidth());
				//System.out.println(current.getColumn()-1<0);
				//System.out.println(current.getRow()+1<getHeight());
				//System.out.println(current.getRow()-1<0);
				visited.add(current);
				if(current.getColumn()+1<getWidth()) {
					hold.add(new Location(current.getRow(), current.getColumn()+1, current, Direction.EAST));
				}
				if(current.getRow()+1<getHeight()) {
					hold.add(new Location(current.getRow()+1, current.getColumn(), current, Direction.SOUTH));
				}
				if(current.getColumn()-1>=0) {
					hold.add(new Location(current.getRow(), current.getColumn()-1, current, Direction.WEST));
				}
				if(current.getRow()-1>=0) {
					hold.add(new Location(current.getRow()-1, current.getColumn(), current, Direction.NORTH));
				}
			}else{
				//System.out.println(!isWall(current));
				//System.out.print(!visited.contains(current));
			}
		}
		return null; // no path from start to a cell labeled as whatToFind
	}
	
	private boolean isWhatToFind(char whatToFind, Location current) {
		//if((whatToFind=='t' && isTreasure(current)) || (whatToFind=='e' && isExit(current))) {
			//System.out.print("isWhatToFind");
		//}
		return (whatToFind=='t' && isTreasure(current)) || (whatToFind=='e' && isExit(current));
	}

	public ArrayList<Direction> path( Location start, LinearStructure<Location> hold) {
		ArrayList<Direction> path = new ArrayList<Direction>();
		ArrayList<Location> treasures=getTreasures();
		int found = 0;
		boolean exited = false;
		char whatToFind;
		Location beginning = new Location( start.getRow(), start.getColumn(),null,null );
		while( found <= treasures.size() && !exited ) {
			//System.out.println("Looking for # treasure: "+found);
			beginning = new Location( beginning.getRow(), beginning.getColumn(), null, null );
			//System.out.println("beginning "+beginning);
			if(found<treasures.size()) {
				whatToFind='t';
			}else{
				whatToFind='e';
			}
			ArrayList<Direction> littlePath= path( beginning, hold, whatToFind );
			if(littlePath==null) {
				return null;
			}
			Location end= atEnd(beginning, littlePath);
			if( end == null ){
				return new ArrayList<Direction>();
			}else{
				//else addToPath( end, path );
				path.addAll(littlePath);
			}
			if(whatToFind=='t') {
				found++;
				mapGrid[end.getRow()][end.getColumn()]='_';
				beginning=end;
			}else{
				exited=true;
			}
		}
		return path;
	}

	public Location atEnd(Location end, ArrayList<Direction> littlePath) {
		Direction go;
		//System.out.println(littlePath);
		if(littlePath==null){
			return null;
		}
		for(int i=0; i<littlePath.size(); i++) {
			go=littlePath.get(i);
			end=end.getLocation(end, go);
		}
		//System.out.println("end"+end);
		return end;
	}
}