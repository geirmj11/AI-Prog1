import java.util.*;

public class State
{
	public State(Point curPos, State prevPos, HashSet<Point> dirt,  HashSet<Point> bumps)
	{
		this.curPos = curPos;
		this.prevPos = prevPos;
		this.bumps = bumps;
		this.dirt = (HashSet<Point>)dirt.clone();
	}
	
	public HashSet<Point> bumps;
	public HashSet<Point> dirt;
	
	public State prevPos;
	public Point curPos;
	
	ArrayList<State> legalMoves(int height, int width) {
		//System.out.println("height: " + height + " width " + width);
		
		ArrayList<State> l = new ArrayList<State>();
		if (curPos.x + 1 <= height)
			l.add(new State(new Point(curPos.x + 1,curPos.y),this, dirt, bumps));
		if (curPos.x - 1 > 0)                                         
			l.add(new State(new Point(curPos.x - 1,curPos.y),this, dirt, bumps));
		if (curPos.y + 1 <= width)                                      
			l.add(new State(new Point(curPos.x,curPos.y + 1),this, dirt, bumps));
		if (curPos.y - 1 > 0)                                         
			l.add(new State(new Point(curPos.x,curPos.y - 1),this, dirt, bumps));
	
		for (int i = 0; i < l.size(); i++)
			if (bumps.contains(l.get(i).curPos)) {
				l.remove(i);
				i--;
			}
				
		for (State s : l)
			if (dirt.contains(s.curPos))
				s.dirt.remove(s.curPos);
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
		
		State other = (State)obj;
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
}
