package ElementarySorts;

public class Shellsort {
    public static void sort(Comparable[] arr) {
        int N = arr.length;
        
        int h = 1;
        while (h < N/3) h = 3*h + 1;
        
        while (h >= 1) {
            for (int i = h; i < N; i++) {
                for (int j = i; j >= h && less(arr[j], arr[j-h]); j-=h) {
                    exch(arr, j, j-h);
                }
            }
            
            h = h/3;
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
