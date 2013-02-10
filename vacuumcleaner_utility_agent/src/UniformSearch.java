import java.util.Collection;
import java.util.Stack;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;
//procedure UniformCostSearch(Graph, root, goal)
//	node := root, cost = 0
//	frontier := priority queue containing node only
//	explored := empty set
//	do
//		if frontier is empty
//			return failure
//		node := frontier.pop()
//		if node is goal
//			return solution
//		explored.add(node)
//		for each of node's neighbors n
//			if n is not in explored
//				if n is not in frontier
//				frontier.add(n)
//			else if n is in frontier with higher cost
//				replace existing node with n
public class UniformSearch
{
	//public static Stack<Point> getPath(Point home, Point size, ArrayList<Point> dirts, ArrayList<Point> bumps)
	//{
	//	State cur = new State(home, null,dirts,bumps);
	//	
	//	Queue<State> frontier = new Queue<State>();
	//	//Shearch for the path.
	//	while (cur.dirt.size() > 0)
	//	{
	//		for (State p : cur.legalMoves(size.x,size.y))
	//			frontier.push(p);
	//		cur = frontier.pop();
	//	}	
	//	
	//	//Building the path.
	//	Stack<Point> path = new Stack<Point>();
	//	for	(;cur != null; cur = cur.prevPos)
	//		path.push(cur.curPos);
	//	
	//	return path;
	//}
}
