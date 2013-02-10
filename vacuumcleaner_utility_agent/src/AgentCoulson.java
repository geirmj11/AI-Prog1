import java.util.Collection;
import java.util.ArrayList;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Agent
{
    private Stack<Point> path;
    private int x;
    private int y;
    private int orientation;
    private Point home;
    private Point size;
    private ArrayList<Point> dirt;
    private ArrayList<Point> obstacles;
    private boolean power;
    
    public void init(Collection<String> percepts) {
            dirt = new ArrayList<Point>();
            obstacles = new ArrayList<Point>();
    	    	/*
	    	Possible percepts are:
			- "(SIZE x y)" denoting the size of the environment, where x,y are integers
			- "(HOME x y)" with x,y >= 1 denoting the initial position of the robot
			- "(ORIENTATION o)" with o in {"NORTH", "SOUTH", "EAST", "WEST"} denoting the initial orientation of the robot
			- "(AT o x y)" with o being "DIRT" or "OBSTACLE" denoting the position of a dirt or an obstacle
			Moving north increases the y coordinate and moving east increases the x coordinate of the robots position.
			The robot is turned off initially, so don't forget to turn it on.
	        */
	        //orientation 0 == norður, 1 == austur, 2 == suður, 3 == vestur.
    
        // Keyra search trees.
        
        Pattern perceptNamePattern = Pattern.compile("\\(\\s*([^\\s]+).*");
		for (String percept:percepts) {
			Matcher perceptNameMatcher = perceptNamePattern.matcher(percept);
			if (perceptNameMatcher.matches()) {
				String perceptName = perceptNameMatcher.group(1);
				if (perceptName.equals("HOME")) {
					Matcher m = Pattern.compile("\\(\\s*HOME\\s+([0-9]+)\\s+([0-9]+)\\s*\\)").matcher(percept);
					if (m.matches()) {
					    this.home.x = Integer.parseInt(m.group(1));
					    this.home.y = Integer.parseInt(m.group(2));
					    this.x = this.home.x;
					    this.y = this.home.y;
					    System.out.println("Home at " + m.group(1) + "," + m.group(2));
					}
				} 
				else if(perceptName.equals("SIZE")) {
				    Matcher m = Pattern.compile("\\(\\s*HOME\\s+([0-9]+)\\s+([0-9]+)\\s*\\)").matcher(percept);
					if (m.matches()) {
					    this.size.x = Integer.parseInt(m.group(1));
					    this.size.y = Integer.parseInt(m.group(2));
					    System.out.println("Size is " + m.group(1) + "," + m.group(2));
					}
			    }
			    else if(perceptName.equals("ORIENTATION")) {
			        Matcher m = Pattern.compile("\\(\\s*ORIENTATION\\s+([^\\s]+)\\s*)").matcher(percept);
			        if(m.group(1) == "NORTH") this.orientation = 0;
			        if(m.group(1) == "EAST")  this.orientation = 1;
			        if(m.group(1) == "SOUTH") this.orientation = 2;
			        if(m.group(1) == "WEST")  this.orientation = 3;
			        System.out.println("orientation is " + m.group(1) + " or: " + this.orientation);
			    }
			    else if(perceptName.equals("AT")) {
			        Matcher m = Pattern.compile("\\(\\s*AT\\s+([^\\s]+)\\\\s+([0-9]+)\\s+([0-9]+)\\s*\\)").matcher(percept);
			        if(m.group(1) == "DIRT") dirt.add(new Point(Integer.parseInt(m.group(2)),Integer.parseInt(m.group(3))));
   			        if(m.group(1) == "OBSTACLE") dirt.add(new Point(Integer.parseInt(m.group(2)),Integer.parseInt(m.group(3))));
   			        System.out.println(m.group(1) + " at " + m.group(2) + "," + m.group(3));
			    }
			} 
			//path = searchGaur.getPath(home, size, dirt, obstacles);
		}

    }

    public String nextAction(Collection<String> percepts) {
		//EF dirt á staðnum, returna suck. 
        //Annars poppa næsta stak af listanum og stefna á þann punkt.
        //Ef listinn tómur, slökkva.
        //String[] actions = { "TURN_ON", "TURN_OFF", "TURN_RIGHT", "TURN_LEFT", "GO", "SUCK" };
        if(!power) {
            power = true;
            return "TURN_ON";
        }
	    if(path.empty()) {
	        power = false;
    	    return "TURN_OFF";
    	}
    
		for (String p : percepts) {
			if (p.equals("DIRT"))
				return "SUCK";
		}
   
	    Point dest = path.peek();
        if(dest.x == this.x){
	        if(dest.y > this.y){
	            if(orientation == 0){
                    path.pop();
	                return "GO";
	            }
	            if(orientation == 1) return "TURN_LEFT";
	            else return "TURN_RIGHT";
	        }
	        if(dest.y < this.y){
	            if(orientation == 2){
	                path.pop();
	                return "GO";
	            }
	            if(orientation == 3) return "TURN_LEFT";
	            else return "TURN_RIGHT";
	        }
	    }
	    else{
	        if(dest.x > this.x){
	            if(orientation == 1){
                    path.pop();
	                return "GO";
	            }
	            if(orientation == 2) return "TURN_LEFT";
	            else return "TURN_RIGHT";
	        }
	        if(dest.x < this.x){
	            if(orientation == 3){
	                path.pop();
	                return "GO";
	            }
	            if(orientation == 0) return "TURN_LEFT";
	            else return "TURN_RIGHT";
	        }
	    }
        return "TURN_OFF";   
	}    
}
