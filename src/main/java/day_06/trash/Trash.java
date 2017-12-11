package day_06.trash;

import java.io.*;

public class Trash {
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("file.txt"));

            String r = reader.readLine();

            char[] arr = r.toCharArray();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
