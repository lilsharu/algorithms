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
        root = put(root, key, val);
    }

    public Node put(Node x, Key key, Value val) {
        if (x == null) return new Node(key, val);
        int cmp = key.compareTo(x.key);
        if (cmp < 0)
            x.left = put(x.left, key, val);
        else if (cmp > 0)
            x.right = put(x.right, key, val);
        else
            x.val = val;
        return x;
    }

    public Value get(Key key) {
        Node current = root;

        while (current != null) {
            int comp = key.compareTo(current.key);
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
