import java.util.Collection;
import java.util.ArrayList; 

public class State
{
	public State(Point curPos, Point prevPos, ArrayList<Point> bumps,ArrayList<Point> dirt)
	{
		this.curPos = curPos;
		this.prevPos = prevPos;
		this.bumps = bumps;
	}
	
	public ArrayList<Point> bumps;
	public ArrayList<Point> dirt;
	
	public Point prevPos;
	public Point curPos;
	
	ArrayList<State> legalMoves(int height, int width) {
		ArrayList<State> l = new ArrayList<State>();
		if (curPos.x + 1 < height)
			l.add(new State(new Point(curPos.x + 1,curPos.y),curPos,bumps,dirt));
		if (curPos.x - 1 >= 0)                                  
			l.add(new State(new Point(curPos.x - 1,curPos.y),curPos,bumps,dirt));
		if (curPos.y + 1 < width)     
			l.add(new State(new Point(curPos.x,curPos.y + 1),curPos,bumps,dirt));
		if (curPos.y - 1 >= 0)        
			l.add(new State(new Point(curPos.x,curPos.y - 1),curPos,bumps,dirt));
		
		for (Point p : bumps)
			for (int i = 0; i < l.size(); i++)
				if (l.get(i).curPos.equals(p)){
					l.remove(l.get(i));
					i--;
				}
				
		for (Point d : dirt)
			for (State s : l)
				if (s.curPos.equals(d))
					s.dirt.remove(s);
					
		return l;
	}
	
	State DoMove(int x, int y) {
		State s = new State(new Point(x,y),curPos,bumps,dirt);
		for (Point d : dirt)
			if (s.curPos.equals(d))
				s.dirt.remove(s);
		return s;
	}
}
