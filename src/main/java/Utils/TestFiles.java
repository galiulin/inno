package Utils;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestFiles {
    public static List<File> getFilesInFolder(String folderPath) {
        File directory = new File(folderPath);
        if (!directory.exists() || !directory.isDirectory()) throw new IllegalArgumentException();
        ArrayList<File> list = new ArrayList<>();
        File[] files = directory.listFiles(pathname -> pathname.getName().endsWith(".txt"));
        Collections.addAll(list, files);

        File[] subDirrectories = directory.listFiles(pathname -> pathname.isDirectory());
        for (File folder: subDirrectories){
           addFiles(list, folder);
        }

        return list;
    }

    private static void addFiles(List<File> collection, File folder){
        File[] subFiles = folder.listFiles(pathname -> pathname.getName().endsWith(".txt"));
        Collections.addAll(collection, subFiles);
        File[] subDirrectories = folder.listFiles(pathname -> pathname.isDirectory());
        for (File dir : subDirrectories){
            addFiles(collection, dir);
        }
    }
}
