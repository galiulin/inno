package day_10.home_work;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Summator {
    private static final ExecutorService service = Executors.newFixedThreadPool(20);
    private static final AtomicLong atomicLong = new AtomicLong(0);
    private static final ReadWriteLock rwLock = new ReentrantReadWriteLock();
    private final File file;

    public Summator(File file) {
        this.file = file;
    }

    public static void doJob() {
        List<File> list = Utils.TestFiles.getFilesInFolder("src/main/java/trash/resources_for_tests_02");
        for (File file : list) {
            service.submit(() -> {
                Summator s = new Summator(file);
                long temp = s.summation();
                System.out.printf("%d - в файле %s \r\n", temp, file.getName());
            });
        }
        service.shutdown();


        Thread thread = new Thread(() -> {
            while (!service.isTerminated()) {
                System.out.println(Summator.getValue());
            }
        });
        thread.start();
    }

    private static void sum(int delta) {
        rwLock.readLock().lock();
        try {
            atomicLong.addAndGet(1);
        } finally {
            rwLock.readLock().unlock();
        }

    }

    private static long getValue() {
        long num = 0;
        rwLock.writeLock().lock();
        try {
            num = atomicLong.get();
        } finally {
            rwLock.writeLock().unlock();
        }
        return num;

    }

    public long summation() {
        long localCount = 0;
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                localCount = scanner.nextInt();
                if (localCount > 0) {
                    sum((int) localCount);
                }
            }
        } catch (FileNotFoundException ex) {

        }
        return localCount;
    }

}
