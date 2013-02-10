import java.util.Collection;
import java.util.ArrayList; 

public class Point
{
	public Point(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public int x;
	public int y;

    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (obj == this)
            return true;
        if (obj.getClass() != getClass())
            return false;
		Point other = (Point)obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false
			
		return true;
    }
}