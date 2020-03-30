package UnionFind.PercolationAssignment;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private boolean[][] grid;

    private final WeightedQuickUnionUF union;
    private final WeightedQuickUnionUF unionForFull;

    private int numOpen;
    private final int topSite;
    private final int bottomSite;
    private final int n;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) throw new IllegalArgumentException();
        else {
            grid = new boolean[n][n];
            union = new WeightedQuickUnionUF(n * n + 2);
            unionForFull = new WeightedQuickUnionUF(n * n + 1);
            this.n = n;
            numOpen = 0;

            topSite = 0;
            bottomSite = n * n + 1;
        }
    }

    // test client (optional)
    public static void main(String[] args) {
        Percolation p = new Percolation(6);
        p.open(1, 1);
        p.open(2, 1);
        p.open(3, 1);
        p.open(3, 3);
        p.open(3, 2);
        p.open(4, 1);
        p.open(4, 2);
        p.open(5, 1);
        p.open(5, 2);
        p.open(6, 2);
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row > n || col > n || row <= 0 || col <= 0) throw new IllegalArgumentException();
        else if (!isOpen(row, col)) {
            int pos = getIndex(row, col);
            if (row == 1) {
                union.union(pos, topSite);
                unionForFull.union(pos, topSite);
            }
            if (row == n) {
                union.union(pos, bottomSite);
            }
            connectIfOpen(pos, row, col + 1);
            connectIfOpen(pos, row - 1, col);
            connectIfOpen(pos, row, col - 1);
            connectIfOpen(pos, row + 1, col);

            grid[row - 1][col - 1] = true;
            numOpen++;
        }
    }

    private int getIndex(int row, int column) {
        return n * (row - 1) + column;
    }

    private void connectIfOpen(int pos, int row, int col) {
        try {
            if (isOpen(row, col)) {
                int pos2 = getIndex(row, col);
                union.union(pos, pos2);
                unionForFull.union(pos, pos2);
            }
        }
        catch (IllegalArgumentException e) {
            // Do Nada
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row > n || col > n || row <= 0 || col <= 0) {
            throw new IllegalArgumentException();
        }
        else return grid[row - 1][col - 1];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row > n || col > n || row <= 0 || col <= 0) throw new IllegalArgumentException();
        else if (isOpen(row, col)) {
            return unionForFull.find(topSite) == unionForFull.find(getIndex(row, col));
        }
        return false;
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return numOpen;
    }

    // does the system percolate?
    public boolean percolates() {
        return union.find(bottomSite) == union.find(topSite);
    }
}