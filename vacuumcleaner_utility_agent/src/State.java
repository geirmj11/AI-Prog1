import java.util.Collection;
import java.util.ArrayList; 

public class State
{
	public State(Point curPos, State prevPos, ArrayList<Point> dirt,  ArrayList<Point> bumps)
	{
		this.curPos = curPos;
		this.prevPos = prevPos;
		this.bumps = bumps;
		this.dirt = dirt;
	}
	
	public ArrayList<Point> bumps;
	public ArrayList<Point> dirt;
	
	public State prevPos;
	public Point curPos;
	
	ArrayList<State> legalMoves(int height, int width) {
		ArrayList<State> l = new ArrayList<State>();
		if (curPos.x + 1 <= height)
			l.add(new State(new Point(curPos.x + 1,curPos.y),this,dirt, bumps));
		if (curPos.x - 1 >= 0)                                         
			l.add(new State(new Point(curPos.x - 1,curPos.y),this,dirt, bumps));
		if (curPos.y + 1 <= width)                                      
			l.add(new State(new Point(curPos.x,curPos.y + 1),this,dirt, bumps));
		if (curPos.y - 1 >= 0)                                         
			l.add(new State(new Point(curPos.x,curPos.y - 1),this,dirt, bumps));
		
		for (Point p : bumps)
			for (int i = 0; i < l.size(); i++)
				if (l.get(i).curPos.equals(p)) {
					l.remove(l.get(i));
					i--;
				}
				
		for (State s : l)
			for (int i = 0; i < s.dirt.size(); i++)
				if (s.curPos.equals(dirt.get(i))) {
					s.dirt.remove(i);
					i--;
					System.out.println("Dirt at: x: " + s.curPos.x + " y: " + s.curPos.y);
				}
					
		return l;
	}
	
	State DoMove(int x, int y) {
		State s = new State(new Point(x,y),this,dirt,bumps);
		for (Point d : dirt)
			if (s.curPos.equals(d))
				s.dirt.remove(s);
		return s;
	}
	
	
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
		if (dirt.size() != dirt.size())
			return false;
			
		return true;
    }
}
