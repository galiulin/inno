package day_10.home_work;

import java.io.*;
import java.util.Random;

public class GeneratorTestFile {
    public static void main(String[] args) {
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("test_file.txt")))) {
            for (int i = 0; i < 50000; i++) {
                Random random = new Random();
                writer.write(random.nextInt(20000) - 5000 + " ");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
