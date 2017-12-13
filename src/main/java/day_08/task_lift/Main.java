package day_08.task_lift;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class Main {
    static BlockingQueue<Integer> blockingQueue = new PriorityBlockingQueue<>();
    static ConcurrentLinkedQueue<Integer> integers = new ConcurrentLinkedQueue<>();

    public static void main(String[] args) {
        blockingQueue.add(3);
        blockingQueue.add(6);
        blockingQueue.add(9);
//        blockingQueue.add(1);
        blockingQueue.add(7);

        integers.add(4);
        integers.add(8);
        integers.add(1);
        integers.add(6);

        Lift lift = new Lift(blockingQueue);

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
                while (true){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
