import java.util.ArrayList;
import java.util.List;

public class Location {
  private int row, col;
  private Location from;
  private Direction fromDirection;

  public Location(int row, int col, Location from, Direction d) {
    this.row = row;
    this.col = col;
    this.from = from;
    this.fromDirection = d;
  }
  
  public Direction getMovedDirection() {
	  return fromDirection;
  }
  
  public List<Direction> getDirectionsToHere( ) {
	  List<Direction> result = new ArrayList<Direction>();
	  Location current = this;
	  while( current != null && current.fromDirection != null ) {
		  result.add(0, current.fromDirection );
		  current=current.from;
	  }
	  return result;
  }

  public String toString() {
    return "("  + col + "," + row + ")";
  }
    
    public Location getLocation(Direction d) {
        switch(d) {
            case EAST: return new Location( row,   col+1, this, d);
            case SOUTH: return new Location(row+1, col,   this, d);
            case WEST: return new Location( row,   col-1, this, d);
            case NORTH: return new Location(row-1, col,   this, d);
            default: return null;
        }
    }

  public Location getLocation(Location from, Direction d) {
    switch(d) {
      case EAST: return new Location( row,   col+1, from, d);
      case SOUTH: return new Location(row+1, col,   from, d);
      case WEST: return new Location( row,   col-1, from, d);
      case NORTH: return new Location(row-1, col,   from, d);
      default: return null;
    }
  }

  public int getColumn() {
    return col;
  }
  
  public boolean equals(Object other) {
    if(other instanceof Location ){
      Location loc = (Location)other;
      return loc.row == row && loc.col == col;
    } else {
    	return false;
    }
  }

  public Location getFrom() {
    return from;
  }

  public int getRow() {
    return row;
  }

}