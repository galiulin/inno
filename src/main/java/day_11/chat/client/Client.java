package day_11.chat.client;

import day_11.chat.shared.message.Message;

import java.io.*;
import java.net.Socket;

public class Client {
    private Socket socket;
    private InputStream inStream;
    private OutputStream outStream;

    public Client(Socket socket) {
        this.socket = socket;
        try {
            this.inStream = socket.getInputStream();
            this.outStream = socket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        new Thread(this::readInputLines).start();
        authorization();
        writeMessage();
    }

    private void writeMessage() {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outStream));
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            try {
                String line = reader.readLine();
                writer.write(line + '\n');
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private void socketClose() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void authorization() {
        Message message = new Message();
        printMessage("Введите имя");
        String name = readConsoleInput();
        message.setFromUserName(name);
        printMessage("введите пароль");
        String pass = readConsoleInput();
        printMessage("повторите введенный пароль");
        String passDubl = readConsoleInput();
        if (pass.equals(passDubl)){
            message.setPrivateInfo(passDubl.hashCode() + "");
        }

        sendMessage(message);

    }

    private void printMessage(String message) {
        System.out.println(message);
    }

    private String readConsoleInput() {
        String message = null;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            message = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return message;
    }

    private synchronized void readInputLines() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inStream));
        String line = null;
        while (!socket.isClosed()) {
            try {
                line = reader.readLine();
                Message message = ParseMessage.stringToMessage(line);
                System.out.println(message);

                if (line == null) throw new IOException();
            } catch (IOException e) {
                socketClose();
            }
        }
    }

    private void sendMessage(Message message) {
        String json = ParseMessage.messageToString(message);
        try {
            outStream.write(json.getBytes());
            outStream.write('\n');
            outStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}