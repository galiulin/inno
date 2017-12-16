package day_08.home_work;

public class FileInfo {
    String fileName;
    int countWords;

    public FileInfo(String fileName, int countWords) {
        this.fileName = fileName;
        this.countWords = countWords;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getCountWords() {
        return countWords;
    }

    public void setCountWords(int countWords) {
        this.countWords = countWords;
    }
}
