package day_16.monkeys;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class Changer {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Capucin mrJenkins =
                new Capucin(10, 100,30);

        final Field footSizeField =
                mrJenkins.getClass().getDeclaredField("footSize");
        footSizeField.setAccessible(true);

        System.out.println(footSizeField.get(mrJenkins));

        footSizeField.set(mrJenkins, 40);
        System.out.println("new value=" + mrJenkins.getFootSize());

        Field tailLenghtField =
                mrJenkins.getClass().getDeclaredField("tailLenght");
        tailLenghtField.setAccessible(true);

        Field modifierField = Field.class.getDeclaredField("modifiers");
        modifierField.setAccessible(true);
        modifierField.setInt(tailLenghtField,
                tailLenghtField.getModifiers() & ~Modifier.FINAL);
        tailLenghtField.set(mrJenkins, 10);

        System.out.println(mrJenkins.getTailLenght());
    }
}
