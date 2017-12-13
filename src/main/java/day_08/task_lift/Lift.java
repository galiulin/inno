package day_08.task_lift;

import java.util.concurrent.BlockingQueue;

public class Lift {
    BlockingQueue<Integer> queue;

    Lift(BlockingQueue queue){
        this.queue = queue;
    }

    public synchronized void on() {
        int currentFloor = 1;
        while (true) {

            try {
                int fifo = queue.take();
                if(fifo < currentFloor){
                    while (currentFloor != fifo){
                        System.out.println(String.format("lift in %d floor", currentFloor));
                        Thread.sleep(1000);
                        currentFloor--;
                    }

                } else if (fifo > currentFloor){
                    while (currentFloor != fifo){
                        System.out.println(String.format("lift in %d floor", currentFloor));
                        Thread.sleep(1000);
                        currentFloor++;
                    }
                }
                if (fifo == currentFloor){
                    System.out.println("Лифт прибыл");
                }
                if (queue.size() == 0) {
                    wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
