package day_12.chat_example;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * Created by admin on 19.12.2017.
 */
public class Receiver implements Runnable {
    static DatagramSocket datagramSocket;
    static boolean isWork = true;
    Integer port = 0;

    public Receiver(Integer port) {
        this.port = port;
    }


    @Override
    public void run() {

        while (isWork) {
            try {
                datagramSocket = new DatagramSocket(port);
                byte[] buffer = new byte[1024];
                DatagramPacket receiverPacket = new DatagramPacket(buffer, 1024);
                if (!datagramSocket.isClosed())
                    datagramSocket.receive(receiverPacket);
                String message =
                        new String(receiverPacket.getData(), 0,
                                receiverPacket.getData().length);
                System.out.println(message);
                datagramSocket.close();

            } catch (SocketException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void close() {
        isWork = false;
        datagramSocket.close();
    }
}