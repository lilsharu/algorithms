package UnionFind.Assignment;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private boolean[][] grid;
    private WeightedQuickUnionUF union;
    private int numOpen;
    private int N;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) throw new IllegalArgumentException();
        grid = new boolean[n][n];
        union = new WeightedQuickUnionUF(n * n + 2);
        N = n;
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
    public void open(int row, int col) throws IndexOutOfBoundsException {
        if (row > N || col > N) throw new IllegalArgumentException();
        if (grid[row - 1][col - 1]) return;
        grid[row - 1][col - 1] = true;
        if (col + 1 < N && isOpen(row, col + 1)) {
            union.union(N * (row - 1) + col, N * (row - 1) + col + 1);
        }
        if (row - 1 > 0 && isOpen(row - 1, col)) {
            union.union(N * (row - 1) + col, N * (row - 1) + col - 1);
        }
        if (col - 1 > 0 && isOpen(row, col - 1)){
            union.union(N * (row - 1) + col, (N) * (row - 1) + col + 1);
        }
        if (row + 1 <= N && isOpen(row + 1, col)) {
            union.union(N * (row - 1) + col, N * (row) + col + 1);
        }
        numOpen++;
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row > N || col > N || row <= 0 || col <= 0) throw new IllegalArgumentException();
        return grid[row - 1][col - 1];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row > N || col > N || row <= 0 || col <= 0) throw new IllegalArgumentException();
        return !grid[row - 1][col - 1];
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return numOpen;
    }

    // does the system percolate?
    public boolean percolates() {
        return (union.find(0) == union.find(N * N + 1));
    }
}