package Mergesort.CollinearPoints;

import edu.princeton.cs.algs4.StdDraw;

import java.util.Comparator;

public class Point implements Comparable<Point> {
    
    private final int x;
    private final int y;
    
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public void draw() {
        StdDraw.point(x, y);
    }
    
    public void drawTo(Point that) {
        StdDraw.line(x, y, that.x, that.y);
    }
    
    public String toString() {
        return String.format("(%d, %d)", x, y);
    }
    
    public int compareTo(Point that) {
        if (y < that.y) return -1;
        if (y > that.y) return 1;
        if (x < that.x) return -1;
        if (x > that.x) return 1;
        else return 0;
    }
    
    public double slopeTo(Point that) {
        if (x == that.x && y == that.y) return Double.NEGATIVE_INFINITY;
        if (x - that.x == 0) return Double.POSITIVE_INFINITY;
        if (y - that.y == 0) return 0;
        
        return ((double) y - that.y) / (x - that.x);
    }
    
    public Comparator<Point> slopeOrder() {
        return new PointsBySlope(this);
    }
    
    private static class PointsBySlope implements Comparator<Point>{
        Point in;
        public PointsBySlope(Point in) {
            this.in = in;
        }
        
        public int compare(Point o1, Point o2) {
            double slope1 = in.slopeTo(o1);
            double slope2 = in.slopeTo(o2);
            
            return Double.compare(slope1, slope2);
        }
    }
}
