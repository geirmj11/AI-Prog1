import java.util.Collection;
import java.util.Stack;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

public class DepthSearch
{
	public static Stack<Point> getPath(Point home, Point size, ArrayList<Point> dirts, ArrayList<Point> bumps)
	{
		State cur = new State(home, null,dirts,bumps);
		
		//Shearch for the path.
		Stack<State> frontier = new Stack<State>();
		ArrayList<State> visited = new ArrayList<State>();
		while (cur.dirt.size() > 0)
		{		
			if (!visited.contains(cur))
			{
				visited.add(cur);
				for (State p : cur.legalMoves(size.x,size.y))
					frontier.add(p);
			}
			if  (frontier.size() > 0)
				cur = frontier.pop();
			else
				break; // No solution found.
		}	
		
		//Building the path.
		Stack<Point> path = new Stack<Point>();
		for	(;cur != null; cur = cur.prevPos)
			path.push(cur.curPos);
		
		return path;
	}
}
