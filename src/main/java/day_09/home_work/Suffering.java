package day_09.home_work;

import java.io.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Suffering<T extends FileInfo> implements Callable {
    private final File file;
    private final char[] arrWord;
    private final String word;

    private int localCount;

    private static Object monitor;

    public final static AtomicInteger atomicInteger = new AtomicInteger();

    public static void inc() {
        atomicInteger.incrementAndGet();
    }

    public static int getInt() {
        return atomicInteger.get();
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
               localCount += containString(line, word);
//               substringAndCheck(line.toLowerCase());
            }
        }
        return localCount;
    }

    private int containString(String line, String word) {
        int poz = -1;
        int localCount = 0;
        while ((poz = line.indexOf(word, poz + 1)) != -1) {
            localCount++;
        }
        return localCount;
    }

    private void substringAndCheck(String review) {
        String[] s = review.split(" ");
        for (int i = 0; i < s.length; i++) {
            if (s[i].length() < arrWord.length) continue;
            else if (Utils.CountingOccurrencesRow.checkLine(s[i], arrWord) > 0) {
                localCount++;
                inc();
            }
        }
    }
}
