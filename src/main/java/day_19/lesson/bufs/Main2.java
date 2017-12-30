package day_19.lesson.bufs;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class Main2 {

    public static void main(String[] args) throws IOException {
        RandomAccessFile accessFile =
                new RandomAccessFile("F:/inno/log.txt", "rw");

        final FileChannel channel = accessFile.getChannel();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                ByteBuffer buffer = ByteBuffer.allocate(42);

                int data = 0;
                try {
                    data = channel.read(buffer);

                    while (data != -1) {
                        System.out.println("Reading " + data);
                        buffer.flip();

                        while (buffer.hasRemaining()) {
                            System.out.print((char) buffer.get());
                        }
                        buffer.clear();

                        data = channel.read(buffer);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };


        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);
        Thread thread3 = new Thread(runnable);
        Thread thread4 = new Thread(runnable);
        Thread thread5 = new Thread(runnable);

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();

//accessFile.close();
    }
}
