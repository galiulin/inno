package day_18.lesson.gc;

/**
 * Created by User on 21.03.2017.
 */
public class Parent {

    static int a = 10;

    static {
        System.out.println("i static Parent " + a);
        //System.out.println("i static Parent " + b);
    }

    static int b = 15;

    Parent() {
        System.out.println("I parent constructor");
    }
}
