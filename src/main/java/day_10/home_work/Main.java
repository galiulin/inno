package day_10.home_work;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        int count = 0;
        try (Scanner scanner = new Scanner(new File("test_file.txt"))) {
            while (scanner.hasNext()) {
                int temp = scanner.nextInt();
                if (temp > 0){
                    count += temp;
                }
                System.out.println(count);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
