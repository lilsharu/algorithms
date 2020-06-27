package ElementarySymbolTables;

public class BST<Key extends Comparable<Key>, Value> {
    private Node root;

    private class Node {
        private Key key;
        private Value val;
        private Node left, right;

        public Node(Key key, Value val) {
            this.key = key;
            this.val = val;
        }
    }

    public void put(Key key, Value val) {

    }

    public Value get(Key key) {
        Node current = root;

        while (current != null && !current.key.equals(key)) {
            if (less(key, current.key)) current = current.left;
            else if (key.equals(current.key)) return current.val;
            else current = current.right;
        }

        return null;
    }

    public void delete(Key key) {

    }

    public Iterable<Key> iterator() {

    }

    private boolean less(Key a, Key b) {
        return a.compareTo(b) < 0;
    }
}
