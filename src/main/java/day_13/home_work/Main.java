package day_13.home_work;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        ConsoleBrowser consoleBrowser = new ConsoleBrowser();
        System.out.println(consoleBrowser.getPage("https://yandex.ru/"));
        consoleBrowser.saveImg("http://vodabereg.ru/wp-content/uploads/2017/05/66.jpg", "66.jpg");
    }
}
