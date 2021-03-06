import java.util.*;

public class WeightedState implements Comparable<WeightedState>
{
	public WeightedState(Point curPos, WeightedState prevPos, HashSet<Point> dirt, HashSet<Point> bumps)
	{
		this.curPos = curPos;
		this.prevPos = prevPos;
		this.bumps = bumps;
		this.dirt = (HashSet<Point>)dirt.clone();
		this.weight = 0;
		if (prevPos != null)
			weight = prevPos.weight;
	}
	
	public HashSet<Point> bumps;
	public HashSet<Point> dirt;
	
	public WeightedState prevPos;
	public Point curPos;
	public int weight;
	
	ArrayList<WeightedState> legalMoves(int height, int width) {		
		ArrayList<WeightedState> l = new ArrayList<WeightedState>();
		if (curPos.x + 1 <= height)
			l.add(new WeightedState(new Point(curPos.x + 1,curPos.y),this, dirt, bumps));
		if (curPos.x - 1 > 0)                                         
			l.add(new WeightedState(new Point(curPos.x - 1,curPos.y),this, dirt, bumps));
		if (curPos.y + 1 <= width)                                      
			l.add(new WeightedState(new Point(curPos.x,curPos.y + 1),this, dirt, bumps));
		if (curPos.y - 1 > 0)                                         
			l.add(new WeightedState(new Point(curPos.x,curPos.y - 1),this, dirt, bumps));
		
		for (int i = 0; i < l.size(); i++)
			if (bumps.contains(l.get(i).curPos)) {
				l.remove(i);
				i--;
			}
		
		for (WeightedState ws : l){
			if (dirt.contains(ws.curPos))
				ws.dirt.remove(ws.curPos);
			if (prevPos == null)
				continue;
			
			if (ws.curPos.equals(prevPos.curPos))
				ws.weight = this.weight + 3;//Requires a uturn.
			else if (prevPos.curPos.x == ws.curPos.x ||
				  prevPos.curPos.y == ws.curPos.y)
				  ws.weight = this.weight + 1; // Can go forward.
			else 
				ws.weight = this.weight + 2; // has to turn.
				
		}
		return l;
	}	
	
    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (obj == this)
            return true;
        if (obj.getClass() != getClass())
            return false;
		
		WeightedState other = (WeightedState)obj;
		
		if (!curPos.equals(other.curPos))
			return false;
		
		if (dirt.size() != other.dirt.size())
			return false;
	
		for (Point d : dirt)
			if (!other.dirt.contains(d))
				return false;
		
		return true;
    }
	
	@Override
    public int hashCode(){
        int hash = curPos.hashCode();
		for (Point d : dirt)
			hash += d.hashCode();
        return hash;
    }
	
	@Override
	public int compareTo(WeightedState other) {
		if (weight < other.weight) return -1;
		if (weight > other.weight) return +1;
		
		if (dirt.size() < other.dirt.size())
			return -1;
		else if (dirt.size() > other.dirt.size())
			return 1;
		
		if (curPos.compareTo(other.curPos) != 0)
			return curPos.compareTo(other.curPos);
		
		for (Point d : dirt)
			if (!other.dirt.contains(d))
				return -1;
		
		return 0;
	}

}
