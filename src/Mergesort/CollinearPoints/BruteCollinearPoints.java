package Mergesort.CollinearPoints;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class BruteCollinearPoints {
    
    private final ArrayList<LineSegment> segmentList = new ArrayList<>();
    
    public BruteCollinearPoints(Point[] points) {
        try {
            
            Arrays.sort(points);
            int len = points.length;
    
            for (int p = 0; p < len - 3; p++) {
                for (int q = p + 1; q < len - 2; q++) {
                    double slope1 = points[p].slopeTo(points[q]);
                    if (slope1 == Double.NEGATIVE_INFINITY) throw new IllegalArgumentException("Can not have repeat points");
                    for (int r = q + 1; r < len - 1; r++) {
                        double slope2 = points[p].slopeTo(points[r]);
                        if (slope2 == Double.NEGATIVE_INFINITY)
                            throw new IllegalArgumentException("Can not have repeat points");
                        if ((int)(slope1 * 100) != (int)(slope2 * 100)) continue;
                        for (int s = r + 1; s < len; s++) {
                            double slope3 = points[p].slopeTo(points[s]);
                            if (slope3 == Double.NEGATIVE_INFINITY)
                                throw new IllegalArgumentException("Can not have repeat points");
                            if ((int)(slope2 * 100) == (int)(slope3 * 100)) {
                                segmentList.add(new LineSegment(points[p], points[s]));
                            }
                        }
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
        In in = new In(args[0]);
        int n = in.readInt();
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
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
