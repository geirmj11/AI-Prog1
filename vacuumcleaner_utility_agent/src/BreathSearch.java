import java.util.Collection;
import java.util.Stack;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BreathSearch
{
	//public static Stack<Point> getPath(Point home, Point size, ArrayList<Point> dirts, ArrayList<Point> bumps)
	//{
	//	State cur = new State(home, null,dirts,bumps);
	//	
	//	//Shearch for the path.
	//	Queue<State> frontier = new LinkedList<State>();
	//	while (cur.dirt.size() > 0) {
	//		for (State p : cur.legalMoves(size.x,size.y))
	//			frontier.add(p);
	//		cur = frontier.remove();
	//	}	
	//	
	//	//Get home ! :)
	//	frontier = new LinkedList<State>();
	//	while (!cur.curPos.equals(home)) {		
	//		for (State p : cur.legalMoves(size.x,size.y))
	//			frontier.add(p);
	//		cur = frontier.remove();
	//	}	
	//	
	//	//Building the path.
	//	Stack<Point> path = new Stack<Point>();
	//	for	(;cur != null; cur = cur.prevPos)
	//		path.push(cur.curPos);
	//	path.pop();// taking out the first spot.
	//	return path;
	//}

	public static Stack<Point> getPath(Point home, Point size, ArrayList<Point> dirts, ArrayList<Point> bumps)
	{
		State cur = new State(home, null,dirts,bumps);
		
		//Shearch for the path.
		Queue<State> frontier = new LinkedList<State>();
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
				cur = frontier.remove();
			else
				break; // No solution found.
		}	
		
		//Get home ! :)
		frontier = new LinkedList<State>();
		visited = new ArrayList<State>();
		while (!cur.curPos.equals(home))
		{		
			if (!visited.contains(cur))
			{
				visited.add(cur);
				for (State p : cur.legalMoves(size.x,size.y))
					frontier.add(p);
			}
			if (frontier.size() > 0)
				cur = frontier.remove();
			else
				break; // No solution found.
		}	
		
		//Building the path.
		Stack<Point> path = new Stack<Point>();	
            System.out.println("Path:");
		for	(;cur != null; cur = cur.prevPos){			
            System.out.println("x: " + cur.curPos.x + " y:" + cur.curPos.y);
			path.push(cur.curPos);
		}
		path.pop();
		return path;
	}
}
