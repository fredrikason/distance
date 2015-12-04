package numbers.distance;

/**
 * A point in the Euclidean 2-dimensional space represented by the numbers (x,y).
 * 
 * @author Fredrik Andersson
 *
 */
public class Point {
	
	private final int x;
	private final int y;
	
	public Point(final int x, final int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Computes the ordinary/Euclidean distance between this point and some other point.
	 *  
	 * @param p second point
	 * @return the distance
	 */
	public double distance(Point p) {
		return Math.sqrt(Math.pow(x-p.x, 2) + Math.pow(y-p.y, 2));
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Point other = (Point) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Point (" + x + ", " + y + ")";
	}
	
}
