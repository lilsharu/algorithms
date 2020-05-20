package StacksAndQueues.QueuesAssignment;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.*;

public class RandomizedQueue<T> implements Iterable<T> {
    private Node first, last;
    private int size = 0;
    
    // construct an empty randomized queue
    
    public RandomizedQueue() {
    
    }
    
    private class Node {
        T item;
        Node next;
        Node previous;
    }
    
    public boolean isEmpty() {
        return size == 0;
    }
    
    public void enqueue(T item) {
        if (item == null) throw new IllegalArgumentException("Item can not be null");
    
        Node oldLast = last;
        
        last = new Node();
        last.item = item;
        last.next = null;
        last.previous = null;
        
        if (isEmpty()) first = last;
        else {
            oldLast.next = last;
            last.previous = oldLast;
        }
        
        size++;
    }
    
    public T dequeue() {
        if (isEmpty()) throw new NoSuchElementException("The RandomizedQueue is empty, nothing to remove");
    
        int random = StdRandom.uniform(size);
        
        Node toRemove = first;
        for (int i = 0; i < random; i++) {
            toRemove = toRemove.next;
        }
    
        size--;
        
        if (isEmpty()) {
            last = null;
            first = null;
        }
        else {
            toRemove.previous.next = toRemove.next;
            toRemove.next.previous = toRemove.previous;
        }
        
        return toRemove.item;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    
    // return a random item (but do not remove it)
    public T sample() {
        if (isEmpty()) throw new NoSuchElementException("The RandomizedQueue is empty, nothing to remove");
    
        int random = StdRandom.uniform(size);
    
        Node toRemove = first;
        for (int i = 0; i < random; i++) {
            toRemove = toRemove.next;
        }
        
        return toRemove.item;
    }
    
    // return an independent iterator over items in random order
    public Iterator<T> iterator() {
        return new RandomizedQueueIterator();
    }
    
    private class RandomizedQueueIterator implements Iterator<T> {
        private Node current = first;
        
        public boolean hasNext() {
            return current != null;
        }
        
        public T next() {
            T item = current.item;
            current = current.next;
            return item;
        }
        
        public void remove() {
            /* not supported */
        }
    }
    
    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> tester = new RandomizedQueue<>();
        
        StdOut.println(tester.size);
        
        tester.enqueue(1);
        print(tester);
        tester.enqueue(2);
        print(tester);
        tester.enqueue(3);
        print(tester);
        tester.enqueue(4);
        print(tester);
        tester.enqueue(5);
        print(tester);
        
        StdOut.println(tester.sample());
        
        StdOut.println(tester.size);
        
        tester.dequeue();
        print(tester);
        tester.dequeue();
        print(tester);
        tester.dequeue();
        print(tester);
        tester.dequeue();
        print(tester);
        tester.dequeue();
        print(tester);
    }
    
    private static void print(RandomizedQueue<Integer> tester) {
        for (int t : tester) {
            StdOut.print(t + " ");
        }
        StdOut.println();
    }
}
