package day_09.home_work;

public class CheckCount implements Runnable {
    @Override
    public void run() {
        System.out.println(Suffering.getInt());
    }
}
