package lesson_03;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class MyArrayListTest {
    MyArrayList<String> myArrayList;

    @Before
    public void setUp() throws Exception {
        myArrayList = new MyArrayList<String>();
        myArrayList.add("string");
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void size() {
        assertEquals(myArrayList.size(), 1);
    }

    @Test
    public void isEmpty() {
        assertFalse(myArrayList.isEmpty());
    }

    @Test
    public void contains() {
        assertTrue(myArrayList.contains("string"));
    }

    @Test
    public void toArray() {
        String[] str2 = (String[]) myArrayList.toArray();
        assertEquals(str2[0], "string");
    }

    @Test
    public void toArray1() {
    }

    @Test
    public void add() {
        myArrayList.add("string");
    }

    @Test
    public void remove() {
    }

    @Test
    public void containsAll() {
    }

    @Test
    public void addAll() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add("string_" + i);
        }
        MyArrayList<String> myArrayList = new MyArrayList<>(9);
        myArrayList.addAll(list);
        assertEquals(5, myArrayList.size());
        for (int i = 0; i < myArrayList.size(); i++) {
            System.out.println(myArrayList.get(i));
        }
    }

    @Test
    public void removeAll() {
    }

    @Test
    public void removeIf() {
    }

    @Test
    public void retainAll() {
    }

    @Test
    public void clear() {
    }

    @Test
    public void spliterator() {
    }

    @Test
    public void stream() {
    }

    @Test
    public void parallelStream() {
    }

    @Test
    public void indexOf() {
    }

    @Test
    public void iterator() {
    }

    @Test
    public void replaceAll() {
    }

    @Test
    public void sort() {
    }

    @Test
    public void addAll1() {
    }

    @Test
    public void get() {
        add();
        assertEquals("string", myArrayList.get(0));
    }

    @Test
    public void set() {
    }

    @Test
    public void add1() {
    }

    @Test
    public void remove1() {
    }

    @Test
    public void lastIndexOf() {
        MyArrayList<String> myArrayList = new MyArrayList<>();
        myArrayList.add("string");
        myArrayList.add("string");
        myArrayList.add("string");
        myArrayList.add("string");
        System.out.println(myArrayList.lastIndexOf("string"));
//        System.out.println(myArrayList.get(0));
    }

    @Test
    public void listIterator() {
    }

    @Test
    public void listIterator1() {
    }

    @Test
    public void subList() {
    }
}