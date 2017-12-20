package day_10.home_work;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

public class Summator {
    private static final ExecutorService service = Executors.newFixedThreadPool(20);
    private static final AtomicLong atomicLong = new AtomicLong(0);

    private static final ArrayBlockingQueue<Long> queue = new ArrayBlockingQueue<>(1);

    public static void doJob(List<File> list) {
        for (File file : list) {
            service.submit(() -> {
                long temp = 0;
                long localCount = 0;
                try (Scanner scanner = new Scanner(file)) {
                    while (scanner.hasNext()) {
                        temp = scanner.nextLong();
                        if (temp > 0) {
                            localCount += temp;
                            queue.put(temp);
                        }
                    }
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.printf("%d - в файле %s \n", localCount, file.getName());
            });
        }
        service.shutdown();


        Thread thread = new Thread(() -> {
            while (!service.isTerminated()) {
                try {
                    System.out.println(atomicLong.addAndGet(queue.take()));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
    }
}
