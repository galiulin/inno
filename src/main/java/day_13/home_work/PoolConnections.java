package day_13.home_work;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PoolConnections {
    private Map<URL, HttpURLConnection> map = new ConcurrentHashMap<>();
    private int maxSize = 50;

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
