package day_09.home_work;

import Utils.TestFiles;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws Exception {
        File[] files = TestFiles.getFilesInFolder(
                "src/main/java/trash/resources_for_tests/");

        System.out.println(files.length);

        Suffering<FileInfo> suffering = new Suffering<>(files[0], "страдание");
        System.out.println(suffering.call());

        String word = "страдание";
        char[] arrWord = word.toCharArray();
        System.out.println(Arrays.toString(arrWord));
        System.out.println(arrWord.toString());

//        ExecutorService service =
//                Executors.newFixedThreadPool(20);

//        List<Future<FileInfo>> futures = new ArrayList<>();
//        for (File file : files) {
//            Future<FileInfo> future = service.submit(new Suffering<FileInfo>(file, "страдание"));
//            futures.add(future);
//        }

    }


    private void mainExample() {
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
