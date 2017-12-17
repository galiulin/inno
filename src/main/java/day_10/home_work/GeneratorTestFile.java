package day_10.home_work;

import java.io.*;
import java.util.Random;

public class GeneratorTestFile {
    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("test_file" + (i < 10 ? "_0" : "_") + i + ".txt")))) {
                for (int j = 0; j < 50000; j++) {
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
}
