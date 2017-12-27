package day_18.lesson.gc;

/**
 * Created by User on 21.03.2017.
 */
public class Child extends Parent {

    static int c = 20;

    static {
        System.out.println("i static Child " + c);
    }

    static int d = 25;

    Child() {
        System.out.println("i Child constuctor " + a);
    }
}
