package StacksAndQueues.QueuesAssignment;

import edu.princeton.cs.algs4.StdOut;

import java.util.*;

public class Deque<T> implements Iterable<T> {
    
    private Node first, last;
    
    private int size = 0;
    
    private class Node {
        T item;
        Node next = null;
        Node previous = null;
    }
    
    public Deque() {
    
    }
    
    public boolean isEmpty() {
        return size == 0;
    }
    
    public int size() {
        return size;
    }
    
    public void addFirst(T item) {
        if (item == null) throw new IllegalArgumentException("Item can not be null");
        
        Node oldFirst = first;
        
        first = new Node();
        first.item = item;
        
        first.previous = null;
        
        if (isEmpty()) {
            first.next = null;
            last = first;
        }
        else {
            first.next = oldFirst;
            oldFirst.previous = first;
        }
        
        size++;
    }
    
    public void addLast(T item) {
        if (item == null) throw new IllegalArgumentException("Item can not be null");
        
        Node oldLast = last;
        
        last = new Node();
        last.item = item;
        
        last.next = null;
        
        if (isEmpty()) {
            last.previous = null;
            first = last;
        }
        else {
            last.previous = oldLast;
            oldLast.next = last;
        }
        
        size++;
    }
    
    public T removeFirst() {
        if (isEmpty()) throw new NoSuchElementException("The Deque is empty, nothing to remove");
        T item = first.item;
        
        first = first.next;
    
        size--;
        if (isEmpty()) {
            last = null;
            first = null;
        }
        else
            first.previous = null;
    
        return item;
        
    }
    
    public T removeLast() {
        if (isEmpty()) throw new NoSuchElementException("The Deque is empty, nothing to remove");
        T item = last.item;
        
        last = last.previous;
        
        size--;
        if (isEmpty()) {
            last = null;
            first = null;
        }
        else {
            last.next = null;
        }
        
        return item;
    }
    
    public Iterator<T> iterator() {
        return new DequeIterator();
    }
    
    private class DequeIterator implements Iterator<T> {
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
    
    public static void main(String[] args) {
        Deque<Integer> tester = new Deque<>();
    
        tester.addLast(9);
        print(tester);
        tester.addLast(100);
        print(tester);
        tester.addFirst(10);
        print(tester);
        tester.addFirst(0);
        print(tester);
    
        StdOut.println(tester.size);
        
        StdOut.print(String.format("Removed %d", tester.removeFirst()));
        print(tester);
        StdOut.print(String.format("Removed %d", tester.removeLast()));
        print(tester);
        StdOut.print(String.format("Removed %d", tester.removeLast()));
        print(tester);
        StdOut.print(String.format("Removed %d", tester.removeFirst()));
        if (tester.isEmpty()) StdOut.println("Tester is Empty");
    }
    
    private static void print(Deque<Integer> tester) {
        for (int t : tester) {
            StdOut.print(t + " ");
        }
        StdOut.println();
    }
}
