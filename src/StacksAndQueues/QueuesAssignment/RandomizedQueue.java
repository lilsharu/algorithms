import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    
    private Item[] items;
    private int size = 0;
   
    public RandomizedQueue() {
        items = (Item[]) new Object[2];
    }
    
    public boolean isEmpty() {
        return size == 0;
    }
    
    public int size() {
        return size;
    }
    
    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException("Item to input can not be null");
        
        if (isFull())
            doubleStorage();
        
        items[size++] = item;
    }
    
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Itemhe Queue is Empty");
        
        int randomIndex = StdRandom.uniform(size--);
        
        Item toRemove = items[randomIndex];
        items[randomIndex] = items[size];
        items[size] = null;
        
        if (isOversized())
            halveStorage();
        
        
        return toRemove;
    }
    
    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException("Itemhe Queue is Empty");
    
        return items[StdRandom.uniform(size)];
    }
    
    private boolean isFull() {
        return items.length == size;
    }
    
    private boolean isOversized() {
        return items.length > 2 && size <= items.length / 4;
    }
    
    private void doubleStorage() {
        resizeStorage(items.length * 2);
    }
    
    private void halveStorage() {
        resizeStorage(items.length / 2);
    }
    
    private void resizeStorage(int newSize) {
        Item[] newStorage = (Item[]) new Object[newSize];
        
        for (int i = 0; i < size; i++) {
            newStorage[i] = items[i];
        }
        items = newStorage;
    }
    
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }
    
    private class RandomizedQueueIterator implements Iterator<Item> {
        private final Item[] randomItems;
        private int current;
        
        public RandomizedQueueIterator() {
            randomItems = (Item[]) new Object[size];
            for (int i = 0; i < size; i++) {
                randomItems[i] = items[i];
            }
            
            StdRandom.shuffle(randomItems);
            current = 0;
        }
        
        public boolean hasNext() {
            return current < size;
        }
        
        public Item next() {
            if (hasNext()) {
                return randomItems[current++];
            }
            else {
                throw new NoSuchElementException("Itemhe Queue is Empty when moving next");
            }
    
        }
        
        public void remove() {
            throw new UnsupportedOperationException("Remove Feature is not Supported");
        }
    }
    
    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> tester = new RandomizedQueue<>();
    
        StdOut.printf("Size: %d\n", tester.size());
        StdOut.println();
        
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
        
        StdOut.println();
        
        StdOut.printf("Sample: %d\n", tester.sample());
    
        StdOut.println();
    
        StdOut.printf("Size: %d\n", tester.size());
        
        StdOut.printf("\nRemoved %d\n", tester.dequeue());
        print(tester);
        StdOut.printf("\nRemoved %d\n", tester.dequeue());
        print(tester);
        StdOut.printf("\nRemoved %d\n", tester.dequeue());
        print(tester);
        StdOut.printf("\nRemoved %d\n", tester.dequeue());
        print(tester);
        StdOut.printf("\nRemoved %d\n", tester.dequeue());
    
        StdOut.println();
    
        StdOut.printf("Is Empty? %b\n", tester.isEmpty());
    }
    
    private static void print(RandomizedQueue<Integer> tester) {
        for (int t : tester) {
            StdOut.print(t + " ");
        }
        StdOut.println();
    }
}
