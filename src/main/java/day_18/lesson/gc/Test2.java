package day_18.lesson.gc;

import java.util.HashMap;

/**
 * Created by User on 22.03.2017.
 */
public class Test2 {
    public static void main(String[] args) {
        HashMap<Object, String> map = new HashMap<>();
        map.put(null, "first");
        Object str = "jfkdls";
        map.put(str, "second");
        str = null;
    }
}
