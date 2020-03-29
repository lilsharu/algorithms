package UnionFind.Assignment;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private boolean[][] grid;
    private WeightedQuickUnionUF union;
    private int numOpen;
    private final int n;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) throw new IllegalArgumentException();
        grid = new boolean[n][n];
        union = new WeightedQuickUnionUF(n * n + 2);
        this.n = n;
        for (int i = 1; i <= n; i++) {
            union.union(0, i);
            union.union(n * n + 1, n * n + 1 - i);
//            System.out.println(n);
//            System.out.print(union.find(i));
//            System.out.println(union.find(0));
//            System.out.println(union.find(n * n) + " " + union.find(n * n - i));
        }
    }

    // test client (optional)
    public static void main(String[] args) {

    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row > n || col > n) throw new IllegalArgumentException();
        if (grid[row - 1][col - 1]) return;
        grid[row - 1][col - 1] = true;
        if (col + 1 < n && isOpen(row, col + 1)) {
            union.union(getIndex(row, col), getIndex(row, col + 1));
        }
        if (row - 1 > 0 && isOpen(row - 1, col)) {
            union.union(getIndex(row, col), getIndex(row - 1, col));
        }
        if (col - 1 > 0 && isOpen(row, col - 1)) {
            union.union(getIndex(row, col), getIndex(row, col - 1));
        }
        if (row + 1 <= n && isOpen(row + 1, col)) {
            union.union(getIndex(row, col), getIndex(row + 1, col));
        }
        numOpen++;
    }

    private int getIndex(int row, int column) {
        return n * (row - 1) + column;
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row > n || col > n || row <= 0 || col <= 0) throw new IllegalArgumentException();
        return grid[row - 1][col - 1];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row > n || col > n || row <= 0 || col <= 0) throw new IllegalArgumentException();
        return !grid[row - 1][col - 1];
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return numOpen;
    }

    // does the system percolate?
    public boolean percolates() {
        return (union.find(0) == union.find(n * n + 1));
    }
}