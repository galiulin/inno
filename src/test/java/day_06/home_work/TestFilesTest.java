package day_06.home_work;

import Utils.TestFiles;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

public class TestFilesTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getFilesInFolder() {
        File[] files = TestFiles.getFilesInFolder("src/main/java/trash/resources_for_tests/");
        for (File file: files){
            System.out.println(file.getName());
        }
    }
}