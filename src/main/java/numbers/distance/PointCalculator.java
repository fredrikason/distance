package numbers.distance;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Calculates a specific number of the closest/farthest points in relation to a particular reference point.
 * 
 * Keeps track of all points using a small fixed size priority queue with priority equals to the distance to reference point.
 * 
 * @author Fredrik Andersson
 *
 */
public class PointCalculator {
	
	public static void main(String[] args) {
		
		String fileName = "/Users/Fredrik/Downloads/Problem 2 - Distance/points";
		MODE mode = MODE.FARTHEST;//MODE.CLOSEST
		int n = 20;//10
		int x = 1000;//-200
		int y = 25;//300
		
		PointInputStream pis = null;
		Point p = null;
		
		try {
			
			final CommandLine cl = new CommandLine(args);
			if (cl.hasOption("h")) {
				printUsage();
				System.exit(0);
			}
			if (cl.hasOption("f")) {
				fileName = cl.getOption("f");
			}
			if (cl.hasOption("n")) {
				n = Integer.parseInt(cl.getOption("n"));
			}
			if (cl.hasOption("m")) {
				mode = MODE.valueOfEncoding(cl.getOption("m"));
			}
			if (cl.hasOption("x")) {
				x = Integer.parseInt(cl.getOption("x"));
			}
			if (cl.hasOption("y")) {
				y = Integer.parseInt(cl.getOption("y"));
			}
		
			final Point ref = new Point(x, y);
			
			// we use an inverted comparator in order to add/remove using the head
			Comparator<Point> comparator;
			if (MODE.CLOSEST.equals(mode)) {
				comparator = new Comparator<Point>() {
					public int compare(Point p2, Point p1) {
						final double d1 = ref.distance(p1);
						final double d2 = ref.distance(p2);
						if (d1 > d2) {
							return 1;
						} else if (d1 < d2) {
							return -1;
						}
						return 0;
					}
				};
			} else {
				comparator = new Comparator<Point>() {
					public int compare(Point p1, Point p2) {
						final double d1 = ref.distance(p1);
		        		final double d2 = ref.distance(p2);
		        		if (d1 > d2) {
		        			return 1;
		        		} else if (d1 < d2) {
		        			return -1;
		        		}
		        		return 0;
					}
				};
			}
			
			long start = System.currentTimeMillis();
			System.out.println(String.format("Calculating the %s %s points to %s", n, mode, ref));
			
			PriorityQueue<Point> pointQueue = new PriorityQueue<Point>(n, comparator);
			pis = new PointInputStream(new BufferedInputStream(new FileInputStream(fileName)));
			
			int count = 0;
			while ((p = pis.readPoint()) != null) {
				//keep the queue small by removing from the queue if the point is closer/further away
				if (pointQueue.size() == n && comparator.compare(p, pointQueue.peek()) > 0) {
					pointQueue.poll();
				}
		        if (pointQueue.size() < n) {
		        	pointQueue.add(p);
		        }
		        count++;
			}
			
			List<Point> result = new ArrayList<Point>();
			for (int i = 0; i < n; i++) {
				result.add(pointQueue.poll());
			}
			
			// result in reversed order...
			for (int i = result.size() - 1; i >= 0; i--) {
				p = result.get(i);
				System.out.println(String.format("%s with distance d=%s", p, ref.distance(p)));
			}
			
			long duration = System.currentTimeMillis() - start;
			System.out.println(String.format("%s points processed in %d ms", count, duration));
		} catch (Exception ex) {
			printException(ex);
			printUsage();
		} finally {
			try {
				if (pis != null) {
					pis.close();
				}
			} catch (IOException ignored) {}
		}
	    
	}
	
	private static void printUsage() {
		System.out.println("Usage: [-f] <file> [-n] <number of points> [-m] <mode: c/f (closest/farthest)> [-x] <x number ref point> [-y] <y number ref point> [-h]");
	}
	
	private static void printException(Exception e) {
        e.printStackTrace();
    }
	
	private static class CommandLine {
		
		private final Map<String, List<String>> params = new HashMap<String, List<String>>();
		
		private CommandLine(String[] args) {
			List<String> options = null;
			for (int i = 0; i < args.length; i++) {
			    final String a = args[i];

			    if (a.charAt(0) == '-' && a.length() < 3) {
			        if (a.length() < 2) {
			            System.out.println("Error at argument " + a);
			            return;
			        }

			        options = new ArrayList<String>();
			        params.put(a.substring(1), options);
			    }
			    else if (options != null) {
			        options.add(a);
			    }
			    else {
			        System.out.println("Illegal parameter usage");
			        return;
			    }
			}
		}
		
		private boolean hasOption(final String option) {
			return params.containsKey(option);
		}
		
		private String getOption(final String option) {
			return params.get(option).get(0);
		}
	}
	
	public static enum MODE {
		
		CLOSEST("c"), FARTHEST("f");
		
		private String encoding;

		public String getCode() {
			return encoding;
		}
		
		MODE(final String encoding) {
			this.encoding = encoding;
		}
		
		private static MODE valueOfEncoding(String encoding) {
			if ("c".equals(encoding)) {
				return MODE.CLOSEST;
			} else if ("f".equals(encoding)) {
				return MODE.FARTHEST;
			} else {
				throw new IllegalArgumentException("Unknown encoding: " + encoding);
			}
		}
	}

}
