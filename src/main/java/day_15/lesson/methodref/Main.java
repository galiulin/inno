package day_15.lesson.methodref;

public class Main {
    public static void main(String[] args) {
        HiInterface hello = Priveter::sayHi;
        hello.sayHi();

        Priveter priveter = new Priveter();

        HiInterface hello2 = priveter::sayHiBrightly;
        hello2.sayHi();

        HiInterface hello3 = () -> System.out.println("Salam");
        hello3.sayHi();
    }
}
