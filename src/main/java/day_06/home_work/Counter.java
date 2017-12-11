package day_06.home_work;

import java.io.*;

public class Counter {
    private int count;
    private BufferedWriter writer;

    public synchronized void sumWithInt(int append) {
        count += append;
    }

    public synchronized void increment() {
        count++;
    }

    public synchronized int getCount() {
        return count;
    }

    public synchronized void initWriter() {
        if (writer == null) {
            try {
                writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("output_file.txt")));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void writeIntoFile() {
        initWriter();
        try {
            writer.write(count + "\r\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized void closeWriter() {
        try {
            if (writer != null) {
                writer.close();
                writer = null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {

        String word = "страдание";

        Counter counter = new Counter();
        counter.initWriter();

        File[] files = TestFiles.getFilesInFolder("src/main/java/trash/resources_for_tests/");

        for (int i = 0; i < files.length; i++) {
            Thread thread = new Thread(new MyCounterWords(counter, files[i], word));
            thread.run();
        }

        counter.writeIntoFile();
        counter.closeWriter();

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
                substringAndCheck(line.toLowerCase(), word.toLowerCase());
            } while (reader.ready());
        }
    }

    private void substringAndCheck(String review, String word) {
        String[] s = review.split(" ");
        for (int i = 0; i < s.length; i++) {
            if (s[i].length() < word.length()) continue;
            else if (checkLine(s[i], word) > 0) {
                incrementCounterAndCheck();
            }
        }
    }

    private void incrementCounterAndCheck() {
        synchronized (counter) {
            counter.increment();
            if (counter.getCount() % 5 == 0){
                counter.writeIntoFile();
            }
        }
    }

    public static int checkLine(String review, String word) {
        char[] arrReview = review.toCharArray();
        char[] arrVerify = word.toCharArray();
        int localCount = 0;
        int j = 0;
        if (arrReview.length < arrVerify.length) return 0;
        for (int i = 0; i < arrReview.length; i++) {
            if (arrReview[i] == arrVerify[j]) {
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

