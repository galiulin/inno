package day_16.annotproxy;

@Logged
public class MathCounter implements Counter {
    @Override
    public void count() {
        System.out.println("1-2-3-4");
    }
}
