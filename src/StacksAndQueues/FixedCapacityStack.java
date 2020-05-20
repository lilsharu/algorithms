package StacksAndQueues;

import java.util.*;

public class FixedCapacityStack<T> implements  Iterable<T>{
    private T[] s;
    private int N = 0;
    
    public FixedCapacityStack(int capacity) {
        s = (T[]) new Object[capacity];
    }
    
    public boolean isEmpty() {
        return N == 0;
    }
    
    public void push(T item) {
        s[N++] = item;
    }
    
    public T pop() {
        T item = s[--N];
        s[N] = null;
        return item;
    }
    
    public Iterator<T> iterator() {
        return new ReverseArrayIterator();
    }
    
    private class ReverseArrayIterator implements Iterator<T> {
        private int i = N;
        
        public boolean hasNext() {
            return i > 0;
        }
        
        public void remove() {
            /* not supported */
        }
        
        public T next() {
            return s[--i];
        }
    }
}
