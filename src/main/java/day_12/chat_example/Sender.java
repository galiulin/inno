package day_12.chat_example;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

/**
 * Created by admin on 19.12.2017.
 */
public class Sender implements Runnable {
    DatagramSocket datagramSocket;
    String nickname = null;

    public Sender() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter yor nickname");
        nickname = scanner.nextLine();
    }

    @Override
    public void run() {
        boolean isWorked = true;
        while (isWorked) {
            try {
                datagramSocket = new DatagramSocket();
                // DatagramSocket datagramSocket = new DatagramSocket();
                Scanner scanner = new Scanner(System.in);
                System.out.println("Enter message");
                String message = scanner.nextLine();
                if (message.equals("exit")) {
                    sendMessage(datagramSocket, nickname + ": Я вышел");
                    close();
                    isWorked = false;

                    return;
                }

                sendMessage(datagramSocket, nickname + ": " + message);
                datagramSocket.close();
            } catch (SocketException e) {
                e.printStackTrace();
            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void sendMessage(DatagramSocket datagramSocket, String message) throws IOException {
        for (int i = 4999; i < 5010; i++) {
            DatagramPacket packet = new DatagramPacket(message.getBytes(), message.getBytes().length,
                    InetAddress.getByName("127.0.0.1"), i);
            datagramSocket.send(packet);
        }
    }

    private void close() {
        Receiver.close();
        datagramSocket.close();
    }
}