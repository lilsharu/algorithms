package Mergesort.CollinearPoints;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.HashMap;

public class FastCollinearPoints {
    
    private final ArrayList<LineSegment> segmentList = new ArrayList<>();
    
    public FastCollinearPoints(Point[] points) {
        try {
            HashMap<Double, ArrayList<Point>> pointMap = new HashMap<>();
            
            Arrays.sort(points);
            Point[] pointsCopy = new Point[points.length];
            System.arraycopy(points, 0, pointsCopy, 0, points.length);
    
            for (Point p : points) {
                Arrays.sort(pointsCopy, p.slopeOrder());
    
                double currentSlope = p.slopeTo(pointsCopy[1]);
                int    count        = 0;
    
                List<Point> pointsOnLine = new ArrayList<>();
    
                for (int i = 1; i < pointsCopy.length; i++) {
                    if (currentSlope == Double.NEGATIVE_INFINITY) {
                        throw new IllegalArgumentException("Can not have repeat points");
                    }
                    if ((int) (currentSlope * 100) == (int) (p.slopeTo(pointsCopy[i]) * 100)) {
                        count++;
                        pointsOnLine.add(pointsCopy[i]);
                    }else {
                        if (count >= 3) {
                            ArrayList<Point> dataOfSlope = pointMap.get(currentSlope);
                            pointsOnLine.add(0, p);
                            pointsOnLine.sort(Point::compareTo);
                            if (dataOfSlope == null) {
                                dataOfSlope = new ArrayList<>(pointsOnLine);
                                pointMap.put(currentSlope, dataOfSlope);
                                segmentList.add(new LineSegment(p, pointsOnLine.get(pointsOnLine.size() - 1)));
                            }
                            else if (!(dataOfSlope.contains(p))) {
                                segmentList.add(new LineSegment(p, pointsOnLine.get(pointsOnLine.size() - 1)));
                                dataOfSlope.addAll(pointsOnLine);
                                pointMap.put(currentSlope, dataOfSlope);
                            }
                        }
                        pointsOnLine.clear();
                        count        = 1;
                        currentSlope = p.slopeTo(pointsCopy[i]);
                    }
                }
            }
        }
        catch (NullPointerException npe) {
            throw new IllegalArgumentException(npe);
        }
    }
    
    public int numberOfSegments() {
        return segmentList.size();
    }
    
    public LineSegment[] segments() {
        return segmentList.toArray(LineSegment[]::new);
    }
    
    public static void main(String[] args) {
        
        // read the n points from a file
        In      in     = new In(args[0]);
        int     n      = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }
        
        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();
        
        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
