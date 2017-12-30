package day_11.chat.server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import day_11.chat.server.db.User;
import day_11.chat.shared.message.Message;
import day_11.chat.shared.message.MessageDeserializer;
import day_11.chat.shared.message.MessageSerializer;

public class ParseMessage {
    private static final Gson gsonSerializer;
    private static final Gson gsonDeserializer;

    static {
        gsonSerializer = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(Message.class, new MessageSerializer<>())
                .create();

        gsonDeserializer = new GsonBuilder()
                .registerTypeAdapter(Message.class, new MessageDeserializer<>())
                .create();
    }

    public static String messageToString(Message message) {
        String json = gsonSerializer.toJson(message);

        return json;
    }

    public static Message stringToMessage(String json) {
        Message resultMessage = gsonDeserializer.fromJson(json, Message.class);
        return resultMessage;
    }


    public static User authorizationLine(String message) {
        return null;
    }
}
