package day_09.home_work;

import Utils.TestFiles;

import java.io.File;
import java.sql.Time;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;

public class Main {
    public static final ExecutorService service = Executors.newFixedThreadPool(20);

    public static void main(String[] args) throws Exception {

        List<File> files = TestFiles.getFilesInFolder(
                "src/main/java/trash/resources_for_tests/");

//        List<File> files = TestFiles.getFilesInFolder("e:/Chtivo/Itar/_Utf8/");

        long before = System.currentTimeMillis();

        Thread printCounter = new Thread(() -> {
            while (!service.isTerminated()) {
                int i = Suffering.getInt();
                if (i % 5 == 0)
                    System.out.println(i);
            }
        });
        printCounter.setDaemon(true);
        printCounter.start();


        List<Future<FileInfo>> futures = new ArrayList<>();
        for (File file : files) {
            Future<FileInfo> future = service.submit(new Suffering<FileInfo>(file, "страдание"));
            futures.add(future);
        }
        service.shutdown();

        List<FileInfo> soutList = new ArrayList<>();

        while (true) {
            List<Future<FileInfo>> tempFutures =
                    new ArrayList<>();
            tempFutures.addAll(0, futures);

            for (Future<FileInfo> future :
                    tempFutures) {
                if (future.isDone()) {
                    try {
                        soutList.add(future.get());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                    futures.remove(future);
                }
            }

            if (futures.size() == 0)
                break;
        }


        long after = System.currentTimeMillis();

        Collections.sort(soutList, (o1, o2) -> {
            if (o1.getCountWords() == o2.getCountWords()) {
                return 0;
            } else if (o1.getCountWords() > o2.getCountWords()) {
                return 1;
            } else {
                return -1;
            }
        });

        soutList.stream().forEach(System.out::println);

        System.out.printf("количество файлов %d\r\n", soutList.size());
        System.out.printf("количество страданий %d\r\n", Suffering.getInt());
        System.out.println("Потраченное время в мс " + (after - before));
    }
}
