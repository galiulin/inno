package day_16.annotproxy;

public class StringCounter implements Counter {
    @Override
    public void count() {
        System.out.println("One-two-three");
    }
}
