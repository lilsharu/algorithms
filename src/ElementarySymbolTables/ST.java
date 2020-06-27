package ElementarySymbolTables;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class ST<Key extends Comparable<Key>, Value> {

    public ST() {

    }

    public void put(Key key, Value val) {

    }

    public Value get(Key key) {
        return null;
    }

    public void delete(Key key) {
        put(key, null);
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    public boolean isEmpty() {
        return true;
    }

    public int size() {
        return 0;
    }

    public Iterable<Key> keys() {
        return null;
    }

    public static void main(String[] args) {
        ST<String, Integer> st = new ST<>();

        for (int i = 0; !StdIn.isEmpty(); i++) {
            String key = StdIn.readString();
            st.put(key, i);
        }

        for (String s : st.keys()) {
            StdOut.println(s + " " + st.get(s));
        }
    }
}
