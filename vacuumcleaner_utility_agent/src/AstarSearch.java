import java.util.*;

public class AstarSearch
{
    public int heuristic(Point check, ArrayList<Point> dirts) {
        int returnValue = 0;
        for(Point p : dirts) {
            int temp = ((p.x-check.x)*(p.x-check.x)+(p.y-check.y)*(p.y-check.y)); //manhattan distance í næsta punkt
            if(temp < returnValue) returnValue = temp;
        }
        return returnValue;
    }
    /*
	public static Stack<Point> getPath(Point home, Point size, ArrayList<Point> dirts, ArrayList<Point> bumps)
	{
		State cur = new State(home, null,new HashSet<Point>(dirts),new HashSet<Point>(bumps));

		//Shearch for the path.
		Queue<State> frontier = new LinkedList<State>();
		HashSet<State> visited = new HashSet<State>();
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
		visited = new HashSet<State>();
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
	*/
}
