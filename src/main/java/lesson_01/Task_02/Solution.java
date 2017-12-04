package lesson_01.Task_02;


import java.util.ArrayList;
import java.util.List;

/* Даны 2 отсортированных массива.
 Найти все элементы в 1м массиве,
 которых нет во 2-м */
public class Solution {
    public static void main(String[] args) {
        Integer[] first = new Integer[50];

        for (int i = 0; i < first.length; i++) {
            first[i] = i;
        }

        Integer[] second = first.clone();

        second[10] = 0;
        second[12] = 0;
        second[30] = 0;

        System.out.println(distinct(first, second));
    }

    protected static List<Integer> distinct(Integer[] first, Integer[] second) {
        List<Integer> result = new ArrayList();
        int positionFirst = 0;
        int positionSecond = 0;

        while (positionSecond < second.length && positionFirst < first.length) {
            if (first[positionFirst] == second[positionSecond]) {
                positionFirst++;
                positionSecond++;
            } else if (first[positionFirst] < second[positionSecond]) {
                result.add(first[positionFirst]);
                positionFirst++;
            } else {
                positionSecond++;
            }
        }
        if (positionFirst < first.length) {
            for (; positionFirst < first.length; positionFirst++) {
                result.add(first[positionFirst]);
            }
        }

        return result;
    }
}
