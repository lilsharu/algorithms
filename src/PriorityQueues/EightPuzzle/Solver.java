package PriorityQueues.EightPuzzle;

import edu.princeton.cs.algs4.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Solver {
    
    private boolean solvable;
    private int steps;
    
    private List<Board> path = new ArrayList<>();
    
    private static class Node implements Comparable<Node>{
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
        
        int count = 0;
        
        MinPQ<Node> boardQueueRegular = new MinPQ<>();
        MinPQ<Node> boardQueueSwapped = new MinPQ<>();
        
        Node mainRoot    = new Node(0, initial, null);
        Node swappedRoot = new Node(0, initial.swapped(), null);
        
        boardQueueRegular.insert(mainRoot);
        boardQueueSwapped.insert(swappedRoot);
        
        while (!boardQueueRegular.min().current.isGoal() && !boardQueueSwapped.min().current.isGoal()) {
            count++;
            Node regular = boardQueueRegular.min();
            for (Board b : regular.current.neighbors()) {
                if (b.equals(regular.previous.current)) continue;
                Node nextNode = new Node(count, b, regular);
                boardQueueRegular.insert(nextNode);
            }
            
            Node swapped = boardQueueSwapped.min();
            for (Board b : regular.current.neighbors()) {
                if (b.equals(swapped.previous.current)) continue;
                Node nextNode = new Node(count, b, regular);
                boardQueueRegular.insert(nextNode);
            }
        }
        
        if (boardQueueSwapped.min().current.isGoal()) {
            solvable = false;
            steps = -1;
        }
        
        else {
            solvable = true;
            Node current = boardQueueRegular.min();
            while (current.current != null) {
                path.add(current.current);
                current = current.previous;
            }
            steps = path.size();
        }
    }
    
    
    
    public boolean isSolvable() {
        return solvable;
    }
    
    public int moves() {
        return steps;
    }
    
    public Iterable<Board> solution() {
        return path;
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
}
