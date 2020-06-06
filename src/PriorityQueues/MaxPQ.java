package PriorityQueues;

public class MaxPQ<Key extends Comparable<Key>> {
    private Key[] priorityQueue;
    private int   N;
    
    public MaxPQ(int capacity) {
        priorityQueue = (Key[]) new Comparable[capacity + 1];
    }
    
    public boolean isEmpty() {
        return N == 0;
    }
    
    public void insert(Key key) {
        priorityQueue[++N] = key;
    }
    
    public Key delMax() {
        Key max = priorityQueue[1];
        exch(1, N--);
        sink(1);
        priorityQueue[N + 1] = null;
        return max;
    }
    
    private void swim(int k) {
        while (k > 1 && less(k / 2, k)) {
            exch(k, k / 2);
            k = k / 2;
        }
    }
    
    private void sink(int k) {
        while (2 * k <= N) {
            int j = 2 * k;
            if (j < N && less(j, j + 1)) j++;
            if (!less(k, j)) break;
            exch(k, j);
            k = j;
        }
    }
    
    private boolean less(int a, int b) {
        return priorityQueue[a].compareTo(priorityQueue[b]) < 0;
    }
    
    private void exch(int a, int b) {
        Key temp = priorityQueue[a];
        priorityQueue[a] = priorityQueue[b];
        priorityQueue[b] = temp;
    }
}
