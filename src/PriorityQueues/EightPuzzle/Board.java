import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class Board {
    
    private Board[] neighbors = null;
    private int[][] tiles;
    private int n;
    
    private int manhattan;
    private int hamming;
    
    public Board(int[][] tiles) {
        this.tiles = (tiles);
        n = tiles.length;
        
        calcManhattan();
        calcHamming();
    }
    
    public String toString() {
        StringBuilder stringOutput = new StringBuilder(n * n);
        stringOutput.append(n).append("\n");
        for (int[] a : tiles) {
            stringOutput.append(" ");
            for (int integer : a) {
                stringOutput.append(integer).append(" ");
            }
            stringOutput.append("\n");
        }
        return stringOutput.toString();
    }
    
    public int dimension() {
        return n;
    }
    
    private void calcHamming() {
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (j + n * (i) + 1 != tiles[i][j] && tiles[i][j] != 0) count++;
            }
        }
        hamming = count;
    }
    
    private void calcManhattan() {
        int manCount = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int temp = tiles[i][j];
                if (temp != 0 && j + n * i + 1 != temp--) {
                    int col = (temp) / n;
                    int row = (temp) % n;
                    
                    manCount += Math.abs(col - i) + Math.abs(row - j);
                }
            }
        }
        
        manhattan = manCount;
    }
    
    public int hamming() {
        return hamming;
    }
    
    public int manhattan() {
        return manhattan;
    }
    
    public boolean isGoal() {
        return this.hamming == 0;
    }
    
    public boolean equals(Object y) {
        if (this == y) return true;
        if (y == null || getClass() != y.getClass()) return false;
    
        Board that = (Board) y;
        if (this.tiles.length != that.tiles.length) return false;
        for (int i = 0; i < tiles.length; i++) {
            if (this.tiles[i].length != that.tiles[i].length) return false;
            for (int j = 0; j < tiles[i].length; j++) {
                if (this.tiles[i][j] != that.tiles[i][j]) return false;
            }
        }
    
        return true;
    }
    
    public Board twin() {
        int[][] copy = getCopy();
        
        if (copy[0][0] != 0 && copy[0][1] != 0)
            swap(copy, 0, 0, 0, 1);
        else
            swap(copy, 1, 0, 1, 1);
        
        return new Board(copy);
    }
    
    public Iterable<Board> neighbors() {
        if (neighbors == null || neighbors.length == 0) {
            findNeighbors();
        }
        return () -> new Iterator<>() {
            int currentIndex = 0;
    
            @Override
            public boolean hasNext() {
                return currentIndex < neighbors.length;
            }
    
            @Override
            public Board next() {
                try {
                    return neighbors[currentIndex++];
                }
                catch (IndexOutOfBoundsException e) {
                    throw new NoSuchElementException("There is no such element");
                }
            }
        };
    }
    
     private void findNeighbors() {
        int row = -1;
        int col = -1;
        
        overall:
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (tiles[i][j] == 0) {
                    row = j;
                    col = i;
                    break overall;
                }
            }
        }
        
        List<Board> boardList = new ArrayList<>();
        
        try {
            int[][] arr = getCopy();
            swap(arr, col, row, col, row - 1);
            boardList.add(new Board(arr));
        }
        catch (IndexOutOfBoundsException e) {
            //Do Nothing
        }
        
        try {
            int[][] arr = getCopy();
            swap(arr, col, row, col - 1, row);
            boardList.add(new Board(arr));
        }
        catch (IndexOutOfBoundsException e) {
            // Do Nothing
        }
        
        try {
            int[][] arr = getCopy();
            swap(arr, col, row, col + 1, row);
            boardList.add(new Board(arr));
        }
        catch (IndexOutOfBoundsException e) {
            // Do Nothing
        }
        
        try {
            int[][] arr = getCopy();
            swap(arr, col, row, col, row + 1);
            boardList.add(new Board(arr));
        }
        catch (IndexOutOfBoundsException e) {
            // Do Nothing
        }
        
        neighbors = boardList.toArray(new Board[boardList.size()]);
    }
    
    private int[][] getCopy() {
        return getCopy(tiles);
    }
    private int[][] getCopy(int[][] arr) {
        int[][] copy = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                copy[i][j] = arr[i][j];
            }
        }
        return copy;
    }
    
    private void swap(int[][] option, int i1, int j1, int i2, int j2) {
        int temp = option[i1][j1];
        option[i1][j1] = option[i2][j2];
        option[i2][j2] = temp;
    }
}
