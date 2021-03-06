package day_09.home_work;

import java.io.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Suffering<T extends FileInfo> implements Callable {
    private final File file;
    private final char[] arrWord;
    private final String word;

    private static final Lock lock = new ReentrantLock();
//    private static final Condition condition = lock.newCondition();

    private static final BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(50, true);

    public final static AtomicInteger atomicInteger = new AtomicInteger();

    private static void incAtomInt() {
        lock.lock();
        try {
            queue.add(atomicInteger.incrementAndGet());
//            condition.signal();
        } finally {
            lock.unlock();
        }
    }

    public static int getInt() {
        int i = 0;
        if (true) {
            try {
                if (!Main.service.isTerminated()) {
                    i = queue.take();
                } else {
                    i = atomicInteger.get();
                }
            } catch (NullPointerException ex) {
                /*do nothing*/
            } catch (InterruptedException e) {
                /* do nothing */
            }
        }
        return i;
    }

    public Suffering(File file, String word) {
        this.file = file;
        this.arrWord = word.toLowerCase().toCharArray();
        this.word = word;
    }

    @Override
    public T call() throws Exception {
        return (T) new FileInfo(file.getName(), readFileAndConsider());
    }

    private int readFileAndConsider() throws IOException {
        int localCount = 0;
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(file), "UTF-8"))) {
            String line;
            while (reader.ready() && (line = reader.readLine()) != null) {
                int i = 0;
                i += containString(line.toLowerCase(), word);
                localCount += i;
            }
        }
        return localCount;
    }

    private int containString(String line, String word) {
        int poz = -1;
        int localCount = 0;
        while ((poz = line.indexOf(word, poz + 1)) != -1) {
            localCount++;
            incAtomInt();
        }
        return localCount;
    }
}
