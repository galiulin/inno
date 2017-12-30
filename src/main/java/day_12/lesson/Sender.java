package day_12.lesson;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Sender {
    public static void main(String[] args) throws IOException {
        DatagramSocket datagramSocket = new DatagramSocket();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter message");
        String message = scanner.nextLine();

        DatagramPacket packet =
                new DatagramPacket(message.getBytes(),
                        message.getBytes().length,
                        InetAddress.getByName("127.0.0.1"),
                        4999);
        datagramSocket.send(packet);
        datagramSocket.close();
    }
}
