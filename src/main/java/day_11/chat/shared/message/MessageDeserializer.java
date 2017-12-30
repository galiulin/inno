package day_11.chat.shared.message;

import com.google.gson.*;

import java.lang.reflect.Type;

public class MessageDeserializer<T extends Message> implements JsonDeserializer<T>{
    @Override
    public T deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Message message = new Message();

        JsonObject jsonObject = json.getAsJsonObject();

        message.setMessage(jsonObject.get("message").getAsString());
        message.setMethod(jsonObject.get("method").getAsString());
        message.setFromUserName(jsonObject.get("fromUserName").getAsString());
        message.setFromUserName(jsonObject.get("privateInfo").getAsString());

        return (T) message;
    }
}
