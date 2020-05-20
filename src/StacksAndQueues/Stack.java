package StacksAndQueues;

import java.util.*;

public class Stack<T> implements Iterable<T>{
    private Node first = null;
    
    private class Node {
        T    item;
        Node next;
    }
    
    public boolean isEmpty() {
        return first == null;
    }
    
    public void push(T item) {
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
    }
    
    public T pop() {
        T item = first.item;
        first = first.next;
        return item;
    }
    
    public Iterator<T> iterator() {
        return new ListIterator();
    }
    
    private class ListIterator implements Iterator<T> {
        private Node current = first;
        
        public boolean hasNext() {
            return current != null;
        }
        
        public void remove() {
            /* not supported */
        }
        
        public T next() {
            T item = current.item;
            current = current.next;
            return item;
        }
    }
}
