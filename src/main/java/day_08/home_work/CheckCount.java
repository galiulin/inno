package day_08.home_work;

public class CheckCount implements Runnable {
    @Override
    public void run() {
        System.out.println(Suffering.getInt());
    }
}
