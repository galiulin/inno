package day_12.lesson;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Reseiver {
    public static void main(String[] args) throws IOException {
        DatagramSocket datagramSocket =
                new DatagramSocket(4999);

        byte[] buffer = new byte[1024];
        DatagramPacket packet = new DatagramPacket(buffer, 1024);
        datagramSocket.receive(packet);

        String message = new String(packet.getData(), 0, packet.getData().length);
        System.out.println(message);
        datagramSocket.close();
    }
}
