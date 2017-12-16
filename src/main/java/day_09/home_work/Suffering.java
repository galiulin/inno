package day_09.home_work;

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
}
