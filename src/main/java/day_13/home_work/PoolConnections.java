package day_13.home_work;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;

public class PoolConnections {
    private int maxSize = 50;
    private Map<URL, HttpURLConnection> map = new LinkedHashMap<>(maxSize, 1.1f, true);

    public PoolConnections (int maxSize){
        this.maxSize = maxSize;
    }

    public PoolConnections(){}

    public HttpURLConnection getConnection(URL url, String requestMethod) {
        HttpURLConnection connection = null;
        connection = map.get(url);
        if (connection == null) {
            try {
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod(requestMethod);
                map.put(url, connection);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
}
