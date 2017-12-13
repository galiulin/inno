package day_08.task_lift;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class Main {
    static BlockingQueue<Integer> blockingQueue = new PriorityBlockingQueue<>();
    static ConcurrentLinkedQueue<Integer> isWanted = new ConcurrentLinkedQueue<>();

    public static void main(String[] args) {

        isWanted.add(4);
//        Lift lift = new Lift(blockingQueue);
        Lift lift = new Lift(isWanted);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                lift.on();
            }
        });
        thread.start();

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                Random random = new Random();
                synchronized (lift) {
                    while (true) {
                        try {
                            lift.wait(5000);
                            int nextFloor = random.nextInt(20) + 1;
                            isWanted.add(nextFloor);
                            System.out.println(String.format("Добавлен этаж %d ", nextFloor));
                            lift.notifyAll();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
        thread1.start();
    }
}
