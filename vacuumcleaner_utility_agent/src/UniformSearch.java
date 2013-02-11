import java.util.*;

public class UniformSearch
{
	public static Stack<Point> getPath(Point home, Point size, ArrayList<Point> dirt, ArrayList<Point> bumps)
	{
		WeightedState cur = new WeightedState(home, null,new HashSet<Point>(dirt),new HashSet<Point>(bumps));

		//Shearch for the path.
		PriorityQueue<WeightedState> frontier = new PriorityQueue<WeightedState>();
		HashSet<WeightedState> visited = new HashSet<WeightedState>();
		while (cur.dirt.size() > 0)
		{
			if (!visited.contains(cur))
			{
				visited.add(cur);
				for (WeightedState p : cur.legalMoves(size.x,size.y)){
					if (!frontier.contains(p)) 
						frontier.add(p);
					else{// Need to update the exsting state.
						boolean needUpdate = false;
						for(WeightedState s : frontier) {
							if (s.equals(p) && s.weight > p.weight)
								needUpdate = true;
						}
						if (needUpdate) {// Update the weight. 
							frontier.remove(p); // This contains the old weight
							frontier.add(p);	// and this the new one.
						}
					}
				}
			}
			if  (frontier.size() > 0)
				cur = frontier.poll();
			else
				break; // No solution found.
		}
		
		//Get home ! :)
		Queue<WeightedState> pathHome = new LinkedList<WeightedState>();
		visited = new HashSet<WeightedState>();
		while (!cur.curPos.equals(home))
		{		
			if (!visited.contains(cur))
			{
				visited.add(cur);
				for (WeightedState p : cur.legalMoves(size.x,size.y))
					pathHome.add(p);
			}
			if (pathHome.size() > 0)
				cur = pathHome.remove();
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
