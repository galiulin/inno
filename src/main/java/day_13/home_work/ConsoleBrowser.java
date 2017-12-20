package day_13.home_work;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ConsoleBrowser {

    private PoolConnections poolConnections;

    public ConsoleBrowser(PoolConnections poolConnections) {
        this.poolConnections = poolConnections;
    }

    public String getPage(String link) {
        StringBuilder strng = new StringBuilder("");

        if (!link.startsWith("https://"))
            link = String.format("https://%s", link);
        try {
            URL url = new URL(link);
            HttpURLConnection connection = poolConnections.getConnection(url, "GET");
//            System.out.println(connection.getContent());
//            connection.setRequestMethod("GET");
//            connection.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
//            connection.setRequestProperty("Accept-Language", "ru-RU,ru;q=0.9,en-US;q=0.8,en;q=0.7");
//            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.84 Safari/537.36");
            InputStream is = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            for (String tmp = reader.readLine(); tmp != null; tmp = reader.readLine()) {
                strng.append(tmp);
            }
//            connection.disconnect();
//            is.reset();
//            reader.reset();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return strng.toString();
    }

    public void saveImg(String imageUrl, String destinationFile) throws IOException {
        URL url = new URL(imageUrl);
        InputStream is = url.openStream();
        OutputStream os = new FileOutputStream(destinationFile);

        byte[] b = new byte[2048];
        int length;

        while ((length = is.read(b)) != -1) {
            os.write(b, 0, length);
        }

        is.close();
        os.close();
    }
}
