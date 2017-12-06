package lesson_03;

import java.util.ArrayList;
import java.util.Random;

public class Lec_01 {
    public static void main(String[] args) {
//        while (true){
//            System.out.println(randomDivider(6));
//        }
        ArrayList<Number> arrayList = new ArrayList<>();
    }

    static double randomDivider(int x){
        Random random = new Random();
        int arg = random.nextInt(100) - 1;

        return x / arg;
    }
}
