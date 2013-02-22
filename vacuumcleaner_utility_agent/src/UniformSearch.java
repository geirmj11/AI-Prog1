import java.util.*;

public class UniformSearch
{
	public static Stack<Point> getPath(Point home, Point size, ArrayList<Point> dirt, ArrayList<Point> bumps)
	{
		WeightedState cur = new WeightedState(home, null,new HashSet<Point>(dirt),new HashSet<Point>(bumps));
        System.out.println("Go find some paths,");
		//Shearch for the path.
		PriorityQueue<WeightedState> frontier = new PriorityQueue<WeightedState>();
		HashSet<WeightedState> visited = new HashSet<WeightedState>();
		HashSet<WeightedState> inFrontier = new HashSet<WeightedState>();
		while (cur.dirt.size() > 0)
		{			
			if (!visited.contains(cur))
			{
				visited.add(cur);
				for (WeightedState leagalMove : cur.legalMoves(size.x,size.y)){
					if (!inFrontier.contains(leagalMove)) {
						inFrontier.add(leagalMove);
						frontier.add(leagalMove);
					}
				}
			}
			if  (frontier.size() > 0)
				cur = frontier.poll();
			else
				break; // No solution found.
		}
        System.out.println("Get home: (" + home.x + "," + home.y + ")");		
		//Get home ! :)
		frontier = new PriorityQueue<WeightedState>();
		visited = new HashSet<WeightedState>();
		inFrontier = new HashSet<WeightedState>();
		while (!cur.curPos.equals(home))
		{		
			for (WeightedState leagalMove : cur.legalMoves(size.x,size.y)){
				if (!inFrontier.contains(leagalMove) && !visited.contains(leagalMove)) {
					inFrontier.add(leagalMove);
					frontier.add(leagalMove);
				}
			}
			if  (frontier.size() > 0){
				visited.add(cur);
				cur = frontier.poll();
			}
			else
				break; // No solution found.
		}
		//713
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
