package ElementarySymbolTables;

public class BST<Key extends Comparable<Key>, Value> {
    private Node root;

    private class Node {
        private Key   key;
        private Value val;
        private Node  left, right;
        private int count;

        public Node(Key key, Value val, int count) {
            this.key = key;
            this.val = val;
            this.count = count;
        }
    }

    public void put(Key key, Value val) {
        root = put(root, key, val);
    }

    public Node put(Node x, Key key, Value val) {
        if (x == null) return new Node(key, val, 1);
        int cmp = key.compareTo(x.key);
        if      (cmp < 0)
            x.left  = put(x.left, key, val );
        else if (cmp > 0)
            x.right = put(x.right, key, val);
        else
            x.val   = val;

        x.count = 1 + size(x.left) + size(x.right);

        return x;
    }

    public Value get(Key key) {
        Node current = root;

        while (current != null) {
            int comp = key.compareTo(current.key);
            if                        (comp < 0) current = current.left;
            else                   if (comp > 0) current = current.right;
            else return current.val;
        }

        return null;
    }

    public void delete(Key key) {

    }

    public Key floor(Key key) {
        Node x = floor(root, key);
        if (x == null) return null;
        return x.key;
    }

    private Node floor(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);

        if (cmp == 0) return x;

        if (cmp <  0) return floor(x.left, key);

        Node t = floor(x.right, key);
        if (t != null) return t;
        else           return x;
    }

    public Key ceiling(Key key) {
        Node x = ceiling(root, key);
        if (x == null) return null;
        else return x.key;
    }

    private Node ceiling(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);

        if (cmp == 0) return x;

        if (cmp < 0) return ceiling(x.right, key);

        Node t = ceiling(x.left, key);
        if (t != null) return t;
        else           return x;
    }

    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null) return 0;
        return x.count;
    }

    public Iterable<Key> iterator() {

    }
}
