import java.util.Collection;
import java.util.ArrayList;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AgentCoulson implements Agent
{
    private Stack<Point> path;
    private int x;
    private int y;
    private int orientation;
    private Point home = new Point(0,0);
    private Point size = new Point(0,0);
    private ArrayList<Point> dirt = new ArrayList<Point>();
    private ArrayList<Point> obstacles = new ArrayList<Point>();
    private boolean power;
    
    private Stack<Point> testSearch(Point testhome, Point testsize, ArrayList<Point> testdirt, ArrayList<Point> testobstacles){
        System.out.println("testhome: " + testhome.x + "," + testhome.y);
        System.out.println("testsize: " + testsize.x + "," + testsize.y);
        for( Point p : testobstacles) {
            System.out.println("testObs: " + p.x + "," + p.y);
        } 
        for( Point p : testdirt) {
            System.out.println("testDirt: " + p.x + "," + p.y);
        } 
        Stack<Point> returnPath = new Stack<Point>();
        returnPath.add(new Point(testhome.x,testhome.y));
        returnPath.add(new Point(testhome.x+1, testhome.y));
        return returnPath;
    }
   
    public void init(Collection<String> percepts) {
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
						//System.out.println(":::::::::::::  home at " + this.x + "," + this.y);
					}
				} 
				else if (perceptName.equals("ORIENTATION")) {
			    	Matcher m = Pattern.compile("\\(\\s*ORIENTATION\\s+([^\\s]+)\\s*\\)").matcher(percept);
			    	if (m.matches()) {
			    	    if(m.group(1) == "NORTH") this.orientation = 0;
			            if(m.group(1) == "EAST")  this.orientation = 1;
			            if(m.group(1) == "SOUTH") this.orientation = 2;
			            if(m.group(1) == "WEST")  this.orientation = 3;
						//System.out.println("::::::::::::: orientation " + m.group(1) + " " +  this.orientation);
					}
				} 
				else if (perceptName.equals("SIZE")){
				    Matcher m = Pattern.compile("\\(\\s*SIZE\\s+([0-9]+)\\s+([0-9]+)\\s*\\)").matcher(percept);
				    if(m.matches()) {
				        this.size.x = Integer.parseInt(m.group(1));
					    this.size.y = Integer.parseInt(m.group(2));
    					//System.out.println(":::::::::::::  size is " + this.size.x + "," + this.size.y);
					}
				}
				else if(perceptName.equals("AT")) {
			        Matcher m = Pattern.compile("\\(\\s*AT\\s*DIRT\\s+([0-9]+)\\s+([0-9]+)\\s*\\)").matcher(percept);
			        if (m.matches()) {
			            System.out.println(":::::::::::::  Dirt at " + m.group(1) + "," + m.group(2));
			            dirt.add(new Point(Integer.parseInt(m.group(1)),Integer.parseInt(m.group(2))));
   			        }
   			        Matcher n = Pattern.compile("\\(\\s*AT\\s*OBSTACLE\\s+([0-9]+)\\s+([0-9]+)\\s*\\)").matcher(percept);
   			        if(n.matches()) {
   			            //System.out.println(":::::::::::::  OBSTACLE at " + n.group(1) + "," + n.group(2));
   			            obstacles.add(new Point(Integer.parseInt(n.group(1)),Integer.parseInt(n.group(2))));
   			        }
			    }
				else {
					System.out.println("other percept:" + percept);
				}
			} else {
				System.err.println("strange percept that does not match pattern: " + percept);
			}
		}
        if(this.obstacles == null)  System.out.println("------------obstacles null!");
        if(this.dirt == null)  System.out.println("------------obstacles null!");
        System.out.println("------------blerg!");
		this.path = testSearch(this.home, this.size, this.dirt, this.obstacles);
        System.out.println("------------more blerg");
    }

    public String nextAction(Collection<String> percepts) {
		//EF dirt á staðnum, returna suck. 
        //Annars poppa næsta stak af listanum og stefna á þann punkt.
        //Ef listinn tómur, slökkva.
        //String[] actions = { "TURN_ON", "TURN_OFF", "TURN_RIGHT", "TURN_LEFT", "GO", "SUCK" };
        if(!power) {
            power = true;
            System.out.println("------------Power on:");
            return "TURN_ON";
        }
        if(path == null) System.out.println("------------NULL!!:");
	    if(path.empty()) {
	        power = false;
            System.out.println("------------- Power off:");
    	    return "TURN_OFF";
    	}
    
		for (String p : percepts) {
			if (p.equals("DIRT"))
				return "SUCK";
		}
   
	    Point dest = path.peek();
	    System.out.println("------------DEST" + dest.x + "," + dest.y); 
        if(dest.x == this.x){
	        if(dest.y > this.y){
	            if(orientation == 0){
                    path.pop();
                                System.out.println("------------GO:");
	                return "GO";
	            }
	            if(orientation == 1) {
	                System.out.println("------------TURN Left");
	                orientation--;     
	                return "TURN_LEFT";
	            }
	            else {
                    System.out.println("------------Turn right:");
                    orientation = (orientation+1)%4;
	                return "TURN_RIGHT";
	            }
	        }
	        if(dest.y < this.y){
	            if(orientation == 2){
	                path.pop();
	                return "GO";
	            }
	            if(orientation == 3) {
                    System.out.println("------------Left:");
                    orientation--;
	                return "TURN_LEFT";
                }
	            else {
                    System.out.println("------------Right:");
                    orientation = (orientation+1)%4;
                    return "TURN_RIGHT";
                }
	        }
	    }
	    else{
	        if(dest.x > this.x){
	            if(orientation == 1){
                    path.pop();
                    System.out.println("------------gow:");
	                return "GO";
	            }
	            if(orientation == 2){
                    System.out.println("------------lewt:");
                    orientation--;
	                return "TURN_LEFT";
	            }
	            else {
	                System.out.println("------------riwt:");   
                    orientation = (orientation+1)%4;
	                return "TURN_RIGHT";
	            }
	        }
	        if(dest.x < this.x){
	            if(orientation == 3){
	                path.pop();
                    System.out.println("------------goW:");
	                return "GO";
	            }
	            if(orientation == 0) {
                    System.out.println("------------lewttt:");
                    orientation--;
	                return "TURN_LEFT";
	            }
	            else {
                    System.out.println("------------rojt:");   
                    orientation = (orientation+1)%4;
	                return "TURN_RIGHT";
	            }
	        }
	    }
        System.out.println("------------Power OFF! D:");
        return "TURN_OFF";   
	}    
}
