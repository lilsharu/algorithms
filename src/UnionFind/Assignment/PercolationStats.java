package UnionFind.Assignment;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private double[] thresholds;
    private final int tests;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) throw new IllegalArgumentException();
        thresholds = new double[trials];
        tests = trials;
        for (int i = 0; i < trials; i++) {
            Percolation percolation = new Percolation(n);
            int threshold = 0;
            while (!percolation.percolates() && threshold <= n * n){
                int randomRow = StdRandom.uniform(1, n + 1);
                int randomColumn= StdRandom.uniform(1, n + 1);
                if (percolation.isFull(randomRow, randomColumn)) {
                    percolation.open(randomRow, randomColumn);
                    threshold++;
                }
            }
            thresholds[i] = (double) threshold / (n * n);
        }
    }

    // test client (see below)
    public static void main(String[] args) {
        PercolationStats pStats = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        StdOut.println("mean = " + pStats.mean());
        StdOut.println("stddev = " + pStats.stddev());
        StdOut.println("95% confidence interval = [" + pStats.confidenceLo() + ", " + pStats.confidenceHi() + "]");
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(thresholds);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(thresholds);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - ((1.96 * stddev()) / (Math.sqrt(tests)));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + ((1.96 * stddev()) / Math.sqrt(tests));
    }

}