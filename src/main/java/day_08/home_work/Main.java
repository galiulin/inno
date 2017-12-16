package day_08.home_work;

import Utils.TestFiles;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
    public static void main(String[] args) {
        File[] files = TestFiles.getFilesInFolder(
                "src/main/java/trash/resources_for_tests/");

        ExecutorService service =
                Executors.newFixedThreadPool(20);

        List<Future<FileInfo>> futures = new ArrayList<>();
        for (File file : files){
            Future<FileInfo> future = service.submit(new Suffering<FileInfo>(file, "страдание"));
            futures.add(future);
        }

    }
}
