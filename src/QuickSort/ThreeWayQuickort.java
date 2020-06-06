package QuickSort;

public class ThreeWayQuickort {
    public void sort(Comparable[] a) {
        sort(a, 0, a.length);
    }
    
    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;
        int lt = lo, gt = hi;
        Comparable v = a[lo];
        int i = lo;
        
        while (i <= gt) {
            int cmp = a[i].compareTo(v);
            if (cmp < 0) exch(a, lt++, i++);
            else if (cmp > 0) exch(a, i, gt--);
            else i++;
        }
    }
    
    private static void exch(Comparable[] a, int b, int c) {
        Comparable temp = a[b];
        a[b] = a[c];
        a[c] = temp;
    }
}
