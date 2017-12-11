package day_06.home_work;

import java.io.*;

public class Counter {
    private int count;

    public synchronized void sumWithInt(int append) {
        count += append;
    }

    public synchronized void increment(){
        count++;
    }

    public synchronized int getCount() {
        return count;
    }


    public static void main(String[] args) {

        String word = "страдание";

        Counter counter = new Counter();

        File[] files = TestFiles.getFilesInFolder("src/main/java/trash/resources_for_tests/");

        for (int i = 0; i < files.length; i++) {
            Thread thread = new Thread(new MyCounterWords(counter, files[i], word));
            thread.run();
        }

        System.out.println(counter.getCount());
    }
}

class TestFiles {
    public static File[] getFilesInFolder(String folderPath) {
        File directory = new File(folderPath);
        if (!directory.isDirectory()) throw new IllegalArgumentException();
        return directory.listFiles(pathname -> pathname.getName().endsWith(".txt"));
    }
}

class MyCounterWords implements Runnable {

    private final File file;
    private final Counter counter;
    private final String word;

    @Override
    public void run() {
        try {
            containString(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public MyCounterWords(Counter counter, File file, String word) {
        this.file = file;
        this.counter = counter;
        this.word = word;
    }

    public void containString(File file) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"))) {
            do {
                String line = reader.readLine();
                counter.sumWithInt(checkLine(line.toLowerCase(), word.toLowerCase()));
            } while (reader.ready());
        }
    }

    public static int checkLine(String review, String word) {
        char[] arrReviw = review.toCharArray();
        char[] arrVerify = word.toCharArray();
        int localCount = 0;
        int j = 0;
        if (arrReviw.length < arrVerify.length) return 0;
        for (int i = 0; i < arrReviw.length; i++) {
            if (arrReviw[i] == arrVerify[j]) {
                j++;
                if (j == arrVerify.length) {
                    localCount++;
                    j = 0;
                }
            } else {
                j = 0;
            }
        }
        return localCount;
    }
}

