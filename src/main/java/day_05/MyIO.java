package day_05;

import java.io.*;
import java.util.Arrays;

public class MyIO {
    public static boolean isFileExist(String file){
        File file1 = new File(file);
        return file1.canRead() && file1.canWrite();
    }
    public static void main(String[] args) throws IOException {
        System.out.println(isFileExist("file.txt"));
        try (RandomAccessFile file = new RandomAccessFile("file.txt", "rw")) {
            byte[] temp = new byte[1];
            long length = file.length();
            for (int i = 0; i < length; i++) {
//                System.out.println(file.readByte());
                file.read(temp, 0, 1);
                System.out.println(Arrays.toString(temp));
            }
        }
    }
}
