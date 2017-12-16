package day_08.home_work;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Suffering<T extends FileInfo> implements Callable {
    private final File file;
    private final char[] word;
    private int localCount;

    private static Object monitor;

    public final static AtomicInteger atomicInteger = new AtomicInteger();

    public static void inc(){
        atomicInteger.incrementAndGet();
    }

    public static int getInt(){
        return atomicInteger.get();
    }

    public Suffering(File file, String word) {
        this.file = file;
        this.word = word.toLowerCase().toCharArray();
    }

    @Override
    public T call() throws Exception {
        readFileAndConsider();
        return (T) new FileInfo(file.getName(), localCount);
    }

    private void readFileAndConsider() throws IOException {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(file), "UTF-8"))) {
            String line;
            while (reader.ready() && (line = reader.readLine()) != null) {
               substringAndCheck(line.toLowerCase());
            }
        }
    }

    private void substringAndCheck(String review){
        String[] s = review.split(" ");
        for (int i = 0; i < s.length; i++) {
            if (s[i].length() < word.length) continue;
            else if (Utils.CountingOccurrencesRow.checkLine(s[i], word) > 0){
                localCount++;
                inc();
            }
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService =
                Executors.newFixedThreadPool(15);
        List<Future<Object>> futures = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            Future<Object> future =
                    executorService.submit(new Callable<Object>() {
                        @Override
                        public Object call() {
                            int result = 0;
                            for (int i = 0; i < 100; i++) {
                                try {
                                    Thread.sleep(Thread.currentThread().getId() * 10);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                result += i;
                            }
                            return result;
                        }
                    });
            futures.add(future);
        }

        while (true) {
            List<Future<Object>> tempFutures =
                    new ArrayList<>();
            tempFutures.addAll(0, futures);

            for (Future<Object> future :
                    tempFutures) {
                if (future.isDone()) {
                    try {
                        System.out.println(future.get());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                    futures.remove(future);
                }
            }

            if (futures.size() == 0)
                return;
        }

        /*Future<Object> future = futures.get(0);

        try {
            System.out.println(
                    future.get(5, TimeUnit.SECONDS)
            );
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }*/
    }

}
