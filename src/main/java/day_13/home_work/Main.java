package day_13.home_work;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        PoolConnections poolConnections = new PoolConnections();
        ConsoleBrowser consoleBrowser = new ConsoleBrowser(poolConnections);
//        System.out.println(consoleBrowser.getPage("https://yandex.ru/"));
//        consoleBrowser.saveImg("http://vodabereg.ru/wp-content/uploads/2017/05/66.jpg", "66.jpg");

        while (true){
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String line = reader.readLine();
            System.out.println( consoleBrowser.getPage(line));
        }
    }
}
