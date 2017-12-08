package day_04;

import java.util.TreeSet;

public class Lesson {

    public static void main(String[] args) {
/*
        LinkedHashMap<Integer, String> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put(1, "one");
        linkedHashMap.put(2, "two");
        linkedHashMap.put(3, "three");
        linkedHashMap.put(4, "four");
        linkedHashMap.put(5, "five");
        System.out.println("HashMap");
        for (Integer key : linkedHashMap.keySet()){
            System.out.println(linkedHashMap.get(key));
        }*/

        TreeSet<String> treeSet = new TreeSet<>();
        treeSet.add("one");
        treeSet.add("two");
        treeSet.add("three");
        treeSet.add("four");
        treeSet.add("five");

        for (String line : treeSet) {
            System.out.println(line);
        }
    }
}
