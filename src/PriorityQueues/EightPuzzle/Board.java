package PriorityQueues.EightPuzzle;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Board {
    
    private Board[] neighbors = null;
    private int[][] tiles;
    private int n;
    
    private int manhattan;
    private int hamming;
    
    public Board(int[][] tiles) {
        this.tiles = tiles;
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
    
    public void calcHamming() {
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i + n * (j - 1) + 1 != tiles[i][j] && tiles[i][j] != 0) count++;
            }
        }
        hamming = count;
    }
    
    public void calcManhattan() {
        int manCount = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i + n * (j - 1) + 1 != tiles[i][j] && tiles[i][j] != 0) {
                    int col = (tiles[i][j] - 1) / n;
                    int row = (tiles[i][j] - 1) % n;
                    
                    manCount += Math.abs(col - j) + Math.abs(row - i);
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
        //return (y instanceof Board && checkEquality((Board) y));
    }
    
    public Board swapped() {
        int[][] copy = getCopy();
        
        if (copy[0][0] != 0 || copy[0][1] != 0)
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
                return neighbors[currentIndex++];
            }
        };
    }
    
    public void findNeighbors() {
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
            //Do Nothing
        }
        
        try {
            int[][] arr = getCopy();
            swap(arr, col, row, col + 1, row);
            boardList.add(new Board(arr));
        }
        catch (IndexOutOfBoundsException e) {
            //Do Nothing
        }
        
        try {
            int[][] arr = getCopy();
            swap(arr, col, row, col, row + 1);
            boardList.add(new Board(arr));
        }
        catch (IndexOutOfBoundsException e) {
            //Do Nothing
        }
        
        neighbors = boardList.toArray(new Board[boardList.size()]);
//        System.out.println(boardList);
//        System.out.println("Found Neighbors");
    }
    
    
    private class NeighborIterator implements Iterable<Board> {
        
        int currentPos = 0;
        
        @Override
        public Iterator<Board> iterator() {
            return new Iterator<Board>() {
                @Override
                public boolean hasNext() {
                    return currentPos < neighbors.length;
                }
    
                @Override
                public Board next() {
                    return neighbors[currentPos++];
                }
    
                @Override
                public void remove() {
                    throw new UnsupportedOperationException("Removing is not supported");
                }
            };
        }
    }
    
    private int[][] getCopy() {
        int[][] copy = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                copy[i][j] = tiles[i][j];
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
