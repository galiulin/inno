package day_11.chat.server;

import day_11.chat.shared.message.Message;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;

public class SocketConnection implements Runnable {
    private final BlockingQueue<SocketConnection> socketHolder;
    private final Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String userName;

    public SocketConnection(Socket socket, BlockingQueue<SocketConnection> socketHolder) {
        this.socketHolder = socketHolder;
        this.socket = socket;
        try {
            InputStream inStream = socket.getInputStream();
            OutputStream outStream = socket.getOutputStream();
            this.bufferedReader = new BufferedReader(new InputStreamReader(inStream));
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(outStream));
            logging("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        String messageLine = readMessage();
        System.out.println(messageLine);
        Message message = ParseMessage.stringToMessage(messageLine);
        logging(message);

        while (!socket.isClosed()) {
            System.out.println(readMessage());
        }

        readPrintAndSendMessages();
    }


    public void sendMessage(String message) {
        if (!socket.isClosed()) {
            try {
                bufferedWriter.write(message + '\n');
                bufferedWriter.flush();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public String readMessage() {
        String message = null;
        try {
            message = bufferedReader.readLine();
            return message;
        } catch (IOException e) {
//            e.printStackTrace();
            this.connectionClose();
        }
        return message;
    }

    public void readPrintAndSendMessages() {
        while (!socket.isClosed()) {
            try {
                logging(bufferedReader.readLine());
            } catch (IOException e) {
                e.printStackTrace();
                this.connectionClose();
            }
        }
    }

    public void connectionClose() {
        try {
            socket.close();
            bufferedReader.close();
            bufferedWriter.close();
            socketHolder.remove(this);
            Thread.currentThread().interrupt();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public<T> void logging(T message) {
        System.out.println(message);
    }
}
