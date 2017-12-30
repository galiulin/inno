package day_11.chat.shared.message;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class MessageSerializer<T extends Message> implements JsonSerializer<T> {

    @Override
    public JsonElement serialize(T src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject result = new JsonObject();
        result.addProperty("method", src.getMessage());
        result.addProperty("fromUserName", src.getFromUserName());
        result.addProperty("message", src.getMessage());
        result.addProperty("privateInfo", src.getPrivateInfo());
        return result;
    }
}
