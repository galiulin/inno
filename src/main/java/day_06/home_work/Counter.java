package day_06.home_work;

import Utils.TestFiles;

import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//import static Utils.CountingOccurrencesRow.checkLine;

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


    public static void main(String[] args) throws InterruptedException {

        String word = "страдание";

        Counter counter = new Counter();
        counter.initWriter();

        File[] files = TestFiles.getFilesInFolder("src/main/java/trash/resources_for_tests/");

        ExecutorService service = Executors.newFixedThreadPool(20);

//        for (File file : files){
//            service.submit(new MyCounterWords(counter, file, word));
//        }

        Thread[] threads = new Thread[files.length];
        MyCounterWords[] myCounterWords = new MyCounterWords[files.length];
//
        for (int i = 0; i < files.length; i++) {
            myCounterWords[i] = new MyCounterWords(counter, files[i], word);
            threads[i] = new Thread(myCounterWords[i]);
            threads[i].start();
        }
//
        for (int i = 0; i < threads.length; i++) {
            threads[i].join();
            System.out.println(myCounterWords[i].getFile().getName() + " : " + myCounterWords[i].getLocalCount());
            System.out.println(i);
        }

        counter.writeIntoFile();
        counter.closeWriter();

        System.out.println(counter.getCount());
    }
}

class MyCounterWords implements Runnable {

    private final File file;
    private final Counter counter;
    private final char[] word;
    private int localCount;

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
        this.word = word.toLowerCase().toCharArray();
        this.localCount = 0;
    }

    public void containString(File file) throws IOException {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(file), "UTF-8"))) {
            do {
                String line = reader.readLine();
                substringAndCheck(line.toLowerCase(), word);
            } while (reader.ready());
        }
    }

    private void substringAndCheck(String review, char[] word) {
        String[] s = review.split(" ");
        for (int i = 0; i < s.length; i++) {
            if (s[i].length() < word.length) continue;
            else if (Utils.CountingOccurrencesRow.checkLine(s[i], word) > 0) {
                incrementCounterAndCheck();
            }
        }
    }

    private void incrementCounterAndCheck() {
        localCount++;
        synchronized (counter) {
            counter.increment();
            if (counter.getCount() % 5 == 0) {
                counter.writeIntoFile();
            }
        }
    }

//    public static int checkLine(String review, char[] word) {
//        char[] arrReview = review.toCharArray();
//        int localCount = 0;
//        int j = 0;
//        if (arrReview.length < word.length) return 0; /*если длина проверяемого слова меньше длины слова то выходим*/ //временно убрал т.к. есть проверка
//                                                                        в предыдущем уровне стека вызова данной функции и добавил проверку по оставшейся части
//        for (int i = 0; i < arrReview.length; i++) {
//            if (arrReview[i] == word[j]) {
//                j++;
//                if (j == word.length) {
//                    localCount++;
//                    j = 0;
//                }
//            } else {
//                j = 0;
//                if (arrReview.length - i < word.length)
//                    return 0; /*если длина оставшейся части массива меньше чем длина слова то выходим
//                     todo посчитать среднюю длину слова в текстах для того что-бы понять получаем ли мы преимущество с этой проверкой*/
//            }
//        }
//        return localCount;
//    }

    public File getFile() {
        return file;
    }

    public int getLocalCount() {
        return localCount;
    }
}

