import java.util.*;

public class AstarSearch
{
    public static int searchHeuristic(WeightedState check) {
        //int returnValue = 0;
        //for(Point p : check.dirt) {
        //    int temp = Math.abs(p.x-check.curPos.x) + Math.abs(p.y-check.curPos.y); //manhattan distance í næsta punkt
        //    returnValue += temp;
        //}
        return check.dirt.size(); // returnValue;
    }
    //public static int homeHeuristic(WeightedState check) {
    //    //int returnValue = 0;
    //    //for(Point p : check.dirt) {
    //    //    int temp = Math.abs(p.x-check.curPos.x) + Math.abs(p.y-check.curPos.y); //manhattan distance í næsta punkt
    //    //    returnValue += temp;
    //    //}
    //    return Math.abs(home.x - check.curPos.x) + Math.abs(home.y - check.curPos.y);;
    //}


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
			for (WeightedState leagalMove : cur.legalMoves(size.x,size.y)){
				if (!inFrontier.contains(leagalMove) && !visited.contains(cur)) {
					inFrontier.add(leagalMove);
					frontier.add(leagalMove);
				}
			}
			if  (frontier.size() > 0) {
				cur.weight += searchHeuristic(cur);
				visited.add(cur);
				cur = frontier.poll();
			}
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
				cur.weight += Math.abs(home.x - cur.curPos.x) + Math.abs(home.y - cur.curPos.y);
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

