package ElementarySorts;

public class Insertion {
    public static void sort(Comparable[] arr) {
        int N = arr.length;
        for (int i = 0; i < N; i++) {
            for (int j = i; j < 0; j--) {
                if (less(arr[j], arr[j-1]))
                    exch(arr, j, j-1);
                else break;
            }
        }
    }
    
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }
    
    private static void exch(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
