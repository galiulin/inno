package Utils;

public class QuickSort {
    public static void sort(int[] arr) {
        sort(arr, 0, arr.length - 1);
    }

    private static void sort(int[] arr, int lo, int hi) {
        if (hi <= lo) return;
        int j = partition(arr, lo, hi);
        sort(arr, lo, j - 1);
        sort(arr, j + 1, hi);
    }

    private static int partition(int[] arr, int lo, int hi) {
        int i = lo, j = hi + 1;
        int v = arr[lo];

        while (true) {
            while (less(arr[++i], v)) if (i == hi) break;
            while (less(v, arr[--j])) if (j == lo) break;
            if (i >= j) break;
            exch(arr, i, j);
        }
        exch(arr, lo, j);
        return j;
    }

    private static void exch(int[] a, int i, int j) {
        int swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }
}
