package lesson_01.Task_01;

import Utils.QuickSort;

/*1. Дан массив целых положительных чисел.
 Дано целое положительное число.
 Посчитать количество пар элементов в массиве,
 которые в сумме дадут входное число. */

public class Solution {
    public static void main(String[] args) {
        int[] arr = {1, 56, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 156, 44};
        System.out.println(countPairs(arr, 10));
        System.out.println(countPairs(arr, 13));
        System.out.println(countPairs(arr, 200));
    }

    protected static int countPairs(int[] arr, int sum) {

        QuickSort.sort(arr);

        int lo = 0;
        int hi = arr.length - 1;
        int count = 0;

        while (lo < hi) {
            int s = arr[lo] + arr[hi];
            if (s == sum) {
                count++;
                lo++;
                hi--;
            } else {
                if (s < sum) lo++;
                else hi--;
            }
        }
        return count;
    }
}

