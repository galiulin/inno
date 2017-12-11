package day_06.home_work;

import java.io.*;

public class Counter {
    int count;

    public synchronized void apInt() {
        count++;
    }

    public synchronized int getCount() {
        return count;
    }


    public static void main(String[] args) {

        Counter counter = new Counter();

        File[] files = TestFiles.getFilesInFolder("src/main/java/trash/resources_for_tests/");
        for (int i = 0; i < files.length; i++) {
            Thread thread = new Thread(new MyCounterWords(counter, files[i]));
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

    private File file;
    private Counter counter;

    @Override
    public void run() {
        try {
            containString(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public MyCounterWords(Counter counter, File file) {
        this.file = file;
        this.counter = counter;
    }

    public void containString(File file) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"))) {
            int i = 0;
            do {
                String line = reader.readLine();
                if (line.toLowerCase().contains("страдание")) {
                    i++;
                    counter.apInt();
                }
            } while (reader.ready());
            System.out.println(file.getName() + " " + i);
        }
    }

    private int checkLine(String review, String verify) {
        char[] arrReviw = review.toCharArray();
        char[] arrVerify = verify.toCharArray();
        int localCount = 0;
        int j = 0;
        if (arrReviw.length < arrVerify.length) return 0;
        for (int i = 0; i < arrReviw.length; i++) {
            if (arrReviw[i] == arrVerify[j]) {
                j++;
                if (j == arrVerify.length - 1){
                    localCount++;
                    j = 0;
                }
            }
            else {
                j = 0;
            }
        }
        return localCount;
    }
}

