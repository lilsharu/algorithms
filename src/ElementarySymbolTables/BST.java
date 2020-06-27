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

        while (current != null) {
            int comp = key.compareTo(current.key)
            if (comp < 0) current = current.left;
            else if (comp > 0) current = current.right;
            else return current.val;
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
