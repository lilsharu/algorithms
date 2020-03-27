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
        for (int i = 1; i <= n; i++) {
            union.union(0, i);
            union.union(n * n + 1, n * n + 1 - i);
        }
        N = n;
    }

    // test client (optional)
    public static void main(String[] args) {

    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) throws IndexOutOfBoundsException {
        if (row > grid.length || col > grid.length) throw new IllegalArgumentException();
        grid[row - 1][col - 1] = false;
        if (isOpen(row - 1, col)) union.union(N * row + col, N * row + col + 1);
        if (isOpen(row - 1, col - 2)) union.union(N * row + col, N * row + col - 1);
        if (isOpen(row - 2, col - 1)) union.union(N * row + col, (N - 1) * row + col + 1);
        if (isOpen(row, col - 1)) union.union(N * row + col, (N + 1) * row + col + 1);
        numOpen++;
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row >= grid.length || col >= grid.length) throw new IllegalArgumentException();
        return !grid[row][col];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row >= grid.length || col >= grid.length) throw new IllegalArgumentException();
        return grid[row][col];
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return numOpen;
    }

    // does the system percolate?
    public boolean percolates() {
        return (union.find(0) == union.find(N * N - 1));
    }
}