package day_18.lesson.gc;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {

    static int loop_counter = 1_000_000_000;

    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("waiting");
        scanner.next();
        while (scanner.hasNext()) {
            scanner.next();
            System.out.println("start");
            getList();
        }
    }

    //"C:\Program Files\Java\jdk1.7.0_71\bin\jvisualvm.exe"
    static List<String> getList() throws InterruptedException {
        List<String> list = new ArrayList<>();

        Random random = new Random();
        for (int i = 0; i < loop_counter; i++) {
            Integer someNumber = random.nextInt();
            list.add(someNumber.toString());
            //System.out.println(someNumber);
        }

        Thread.sleep(1000);
        return list;
    }
}
