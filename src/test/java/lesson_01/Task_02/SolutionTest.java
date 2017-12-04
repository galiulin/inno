package lesson_01.Task_02;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class SolutionTest {
    class TestSolution extends Solution {
    }

    /*тест отсутствующих элементов в начале, ~середине и конце*/
    @Test
    public void distinct_01() {

        Integer[] arr_01 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30};
//        Integer[] arr_02 = {3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18};

        List<Integer> targetList = new ArrayList<Integer>(Arrays.asList(arr_01));

        List<Integer> result = new ArrayList<Integer>();
        result.add(targetList.remove(0));
        result.add(targetList.remove(0));
        result.add(targetList.remove(0));
        result.add(targetList.remove(0));
        result.add(targetList.remove(3));
        result.add(targetList.remove(3));
        result.add(targetList.remove(3));
        result.add(targetList.remove(targetList.size() - 3));
        result.add(targetList.remove(targetList.size() - 2));
        result.add(targetList.remove(targetList.size() - 1));

//        System.out.println(Arrays.toString(arr_01));
//        System.out.println(targetList);
//        System.out.println(result);

        Integer[] arr_02 = targetList.toArray(new Integer[targetList.size()]);


//        List <Integer> result = Arrays.asList(1,2,19,20);
        assertEquals(result, TestSolution.distinct(arr_01, arr_02));
    }

    @Test
    public void distinct_02() {
        Integer[] arr_01 = {3, 4, 5, 6, 7, 8, 9, 10};
        Integer[] arr_02 = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        assertEquals(0, TestSolution.distinct(arr_01, arr_02).size());
    }

    @Test
    public void distinct_03() {
        Integer[] arr_01 = {3, 4, 5, 6, 7, 8, 9, 10};
        Integer[] arr_02 = {0, 1, 2, 3, 4, 5, 6, 11, 12, 13, 14, 15};
        List<Integer> list = Arrays.asList(7, 8, 9, 10);
        assertEquals(list, TestSolution.distinct(arr_01, arr_02));
    }
}