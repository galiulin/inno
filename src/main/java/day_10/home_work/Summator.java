package day_10.home_work;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.*;

public class Summator {
    private static final ExecutorService service = Executors.newFixedThreadPool(20);

    private static final AtomicLong atomicLong = new AtomicLong(0);

    private static final Lock lock = new ReentrantLock();
//    private static final Lock getLock = new ReentrantReadWriteLock.ReadLock();

    private static final ReadWriteLock rwLock = new ReentrantReadWriteLock();

    private static final Condition condition = lock.newCondition();

    private final File file;

    public Summator(File file) {
        this.file = file;
    }

    public static void doJob() {
        List<File> list = Utils.TestFiles.getFilesInFolder("src/main/java/trash/resources_for_tests_02");
        for (File file : list) {
            service.submit(() -> {
                Summator s = new Summator(file);
                s.summation();
            });
        }
        service.shutdown();


        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!service.isTerminated()) {
                    System.out.println(Summator.getValue());
                }
            }
        });
        thread.start();
    }

    private static void sum(int delta) {
        lock.lock();
        try {
            atomicLong.addAndGet(delta);
            condition.signalAll();
        } finally {
            lock.unlock();
        }

    }

    private static long getValue() {
        long num = 0;
        lock.lock();
        try {
            condition.await();
            num = atomicLong.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            condition.signalAll();
            lock.unlock();
        }
        return num;

    }

    public void summation() {
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                sum(scanner.nextInt());
            }
        } catch (FileNotFoundException ex) {

        }
    }

}
