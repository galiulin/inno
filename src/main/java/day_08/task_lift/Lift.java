package day_08.task_lift;

import java.util.concurrent.ConcurrentLinkedQueue;

public class Lift {
    ConcurrentLinkedQueue<Integer> queue;

    Lift(ConcurrentLinkedQueue queue) {
        this.queue = queue;
    }

    public synchronized void on() {
        int currentFloor = 1;
        while (true) {

            try {
                while (queue.size() == 0) {
                    wait();
                }
                int fifo = queue.poll();
                if (fifo < currentFloor) {
                    while (currentFloor != fifo) {
                        System.out.println(String.format("lift in %d floor", currentFloor));
                        Thread.sleep(1000);
                        currentFloor--;
                    }

                } else if (fifo > currentFloor) {
                    while (currentFloor != fifo) {
                        System.out.println(String.format("lift in %d floor", currentFloor));
                        Thread.sleep(1000);
                        currentFloor++;
                    }
                }
                if (fifo == currentFloor) {
                    System.out.println("Лифт прибыл на этаж " + currentFloor);
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
