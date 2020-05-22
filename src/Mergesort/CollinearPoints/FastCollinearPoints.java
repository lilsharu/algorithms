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
            
            ArrayList<Integer> slopeList = new ArrayList<>();
            ArrayList<ArrayList<Point>> pointList = new ArrayList<>();
            
            Arrays.sort(points);
            Point[] pointsCopy = new Point[points.length];
            System.arraycopy(points, 0, pointsCopy, 0, points.length);
    
            for (Point p : points) {
                Arrays.sort(pointsCopy, p.slopeOrder());
    
                double currentSlope = p.slopeTo(pointsCopy[1]);
                int    count        = 0;
    
                ArrayList<Point> pointsOnLine = new ArrayList<>();
    
                for (int i = 1; i < pointsCopy.length; i++) {
                    if (currentSlope == Double.NEGATIVE_INFINITY) {
                        throw new IllegalArgumentException("Can not have repeat points");
                    }
                    if ((int) (currentSlope * 100) == (int) (p.slopeTo(pointsCopy[i]) * 100)) {
                        count++;
                        pointsOnLine.add(pointsCopy[i]);
                    } else {
                        pointsOnLine.add(0, p);
                        pointsOnLine.sort(Point::compareTo);
                        if (count >= 3) {
                            int slopeComparison = (int)(currentSlope * 100);
                            if (slopeList.contains(slopeComparison)) {
                                ArrayList<Point> pointsNext = pointList.get(slopeList.indexOf(slopeComparison));
                                if (!pointsNext.containsAll(pointsOnLine)) {
                                    pointsNext.addAll(pointsOnLine);
                                    segmentList.add(new LineSegment(p, pointsOnLine.get(pointsOnLine.size() - 1)));
                                }
                            }
                            else {
                                slopeList.add(slopeComparison);
                                pointList.add(new ArrayList<>(pointsOnLine));
                                segmentList.add(new LineSegment(p, pointsOnLine.get(pointsOnLine.size() - 1)));
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
