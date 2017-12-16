package Utils;

import java.io.File;

public class TestFiles {
    public static File[] getFilesInFolder(String folderPath) {
        File directory = new File(folderPath);
        if (!directory.exists() || !directory.isDirectory()) throw new IllegalArgumentException();
        return directory.listFiles(pathname -> pathname.getName().endsWith(".txt"));
    }
}
