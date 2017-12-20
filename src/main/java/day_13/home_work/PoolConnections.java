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

    public HttpURLConnection getConnection(URL url){
        HttpURLConnection connection = null;
        if (map.containsKey(url)) connection = map.get(url);
        else {
            try {
                connection = (HttpURLConnection) url.openConnection();
                map.put(url, connection);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
}
