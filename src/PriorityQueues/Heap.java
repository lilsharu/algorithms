package PriorityQueues;

public class Heap {
    public static void sort(Comparable[] arr) {
        int N = arr.length;
        for (int k = N/2; k >= 1; k--)
            sink(arr, k, N);
        while (N > 1) {
            exch(arr, 1, N);
            sink(arr, 1, --N);
        }
    }
    
    private static void sink(Comparable[] arr, int k , int N) {
        while (2 * k <= N) {
            int j = 2 * k;
            if (j < N && less(arr, j, j + 1)) j++;
            if (!less(arr, k, j)) break;
            exch(arr, k, j);
            k = j;
        }
    }
    
    private static boolean less(Comparable[] arr, int i, int j) {
        return arr[i - 1].compareTo(arr[j - 1]) > 0;
    }
    
    public static void exch(Comparable[] arr, int i, int j) {
        Comparable temp = arr[i - 1];
        arr[i - 1] = arr[j - 1];
        arr[j - 1] = temp;
    }
}
