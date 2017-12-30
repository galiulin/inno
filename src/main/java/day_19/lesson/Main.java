package day_19.lesson;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class Main {
    public static void main(String[] args) throws IOException {
        RandomAccessFile accessFile =
                new RandomAccessFile("/home/sa/IdeaProjects/fromSurf/Innopolis/src/main/java/trash/resources_for_tests_02/test_file.txt", "rw");

        FileChannel channel = accessFile.getChannel();

        /*TODO попробовать переписать на IntBuffer*/
        ByteBuffer buffer = ByteBuffer.allocate(60);
        int data = channel.read(buffer);
        while (data != -1) {
            System.out.println(" Reading " + data);
            buffer.flip();

            while (buffer.hasRemaining()) {
                System.out.print((char) buffer.get());
            }
            buffer.clear();
            System.out.println();
            data = channel.read(buffer);
        }

        accessFile.close();
    }
}
