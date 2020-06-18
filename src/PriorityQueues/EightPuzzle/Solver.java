import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.List;

public class Solver {
    
    private final boolean solvable;
    private final int     steps;
    
    private List<Board> path = new ArrayList<>();
    
    private static class Node implements Comparable<Node> {
        int moves;
        Board current;
        Node previous;
        
        public Node(int moves, Board current, Node previous) {
            this.moves = moves;
            this.current = current;
            this.previous = previous;
        }
        
        public int getPriority() {
            return current.manhattan() + moves;
        }
        
        public int compareTo(Node b) {
            return Integer.compare(getPriority(), b.getPriority());
        }
    }
    
    public Solver(Board initial) {
        if (initial == null) throw new IllegalArgumentException("Argument can not be null!");
        
        if (initial.isGoal()) {
            steps = 0;
            solvable = true;
            path.add(initial);
        }
        else {
    
            MinPQ<Node> boardQueueRegular = new MinPQ<>(new NodeComparator());
            MinPQ<Node> boardQueueSwapped = new MinPQ<>(new NodeComparator());
    
            Node mainRoot    = new Node(0, initial, null);
            Node swappedRoot = new Node(0, initial.twin(), null);
    
    
            boardQueueRegular.insert(mainRoot);
            boardQueueSwapped.insert(swappedRoot);
    
            while (!boardQueueRegular.min().current.isGoal() && !boardQueueSwapped.min().current.isGoal()) {
                Node regular = boardQueueRegular.delMin();
                for (Board b : regular.current.neighbors()) {
                    if (notAlreadyInPath(regular, b)) {
                        boardQueueRegular.insert(new Node(regular.moves + 1, b, regular));
                    }
                }
        
                Node swapped = boardQueueSwapped.delMin();
                for (Board b : swapped.current.neighbors()) {
                    if (notAlreadyInPath(swapped, b)) {
                        boardQueueSwapped.insert(new Node(swapped.moves + 1, b, swapped));
                    }
                }
            }
            Node finalNode = boardQueueRegular.delMin();
            solvable = finalNode.current.isGoal();
            path.add(finalNode.current);
            if (solvable) {
                while ((finalNode = finalNode.previous) != null) {
                    path.add(0, finalNode.current);
                }
                steps = path.size() - 1;
            } else {
                path  = null;
                steps = -1;
            }
        }
    }
    
    private boolean notAlreadyInPath(Node previous, Board current) {
        while ((previous = previous.previous) != null) {
            if (previous.current.equals(current))
                return false;
        }
        
        return true;
    }
    
    public boolean isSolvable() {
        return solvable;
    }
    
    public int moves() {
        return steps;
    }
    
    public Iterable<Board> solution() {
        if (solvable) {
            return () -> new Iterator<>() {
                int current;
                @Override
                public boolean hasNext() {
                    return current < path.size();
                }
                @Override
                public Board next() {
                    try {
                        return path.get(current++);
                    } catch (IndexOutOfBoundsException e) {
                        throw new NoSuchElementException("There is no next element");
                    }
                }
        
                @Override
                public void remove() {
                    throw new UnsupportedOperationException("The operation of removal is not supported");
                }
            };
        }
        
        return null;
    }
    
    public static void main(String[] args) {
        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                 tiles[i][j] = in.readInt();
            
        Board initial = new Board(tiles);
    
        // solve the puzzle
        Solver solver = new Solver(initial);
    
        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }
    
    private static class NodeComparator implements Comparator<Node> {
        @Override
        public int compare(Node a, Node b) {
            return Integer.compare(a.getPriority(), b.getPriority());
        }
    }
}
