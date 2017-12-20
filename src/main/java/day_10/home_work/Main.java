package day_10.home_work;

import java.io.File;
import java.util.List;

public class Main {


    public static void main(String[] args) {

        List<File> list = Utils.TestFiles.getFilesInFolder("src/main/java/trash/resources_for_tests_02");
        Summator.doJob(list);
    }
}
