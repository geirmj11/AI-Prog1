import java.util.Collection;
import java.util.Stack;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BreathSearch
{
	public static Stack<Point> getPath(Point home, Point size, ArrayList<Point> dirts, ArrayList<Point> bumps)
	{
		State cur = new State(home, null,dirts,bumps);
		
		Queue<State> frontier = new LinkedList<State>();
		//Shearch for the path.
		while (cur.dirt.size() > 0)
		{
			for (State p : cur.legalMoves(size.x,size.y))
				frontier.add(p);
			cur = frontier.remove();
		}	
		
		//Building the path.
		Stack<Point> path = new Stack<Point>();
		for	(;cur != null; cur = cur.prevPos)
			path.push(cur.curPos);
		
		return path;
	}
}
