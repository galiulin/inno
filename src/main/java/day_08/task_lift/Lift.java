package day_08.task_lift;

import java.util.concurrent.ConcurrentLinkedQueue;

public class Lift {
    ConcurrentLinkedQueue<Home.Pair> queue;
    private int currentFloor;

    Lift(ConcurrentLinkedQueue<Home.Pair> queue) {
        this.queue = queue;
        this.currentFloor = 1;
    }

    public synchronized void on() {
        while (true) {
            try {
                while (queue.size() == 0) {
                    wait();
                }
                Home.Pair pair = queue.poll();
                System.out.println("лифт поехал за человеком");
                moveLift(pair.getEnterFloor());
                System.out.println(String.format("%s зашел в лифт и поехал на этаж №%d", pair.getHuman().getName(), pair.getHuman().getOutFloor()));
                moveLift(pair.getHuman().getOutFloor());
                System.out.println(String.format("%s вышел из лифта", pair.getHuman().getName()));

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void moveLift(int needFloor) throws InterruptedException {
        if (needFloor < currentFloor) {
            moveDown(needFloor);
        } else if (needFloor > currentFloor) {
            moveUp(needFloor);
        }
        if (needFloor == currentFloor) {
            System.out.println("Лифт прибыл на этаж " + currentFloor);
        }
    }

    private void moveDown(int floor) throws InterruptedException {
        while (currentFloor != floor) {
            System.out.println(String.format("lift in %d floor || Ожидают еще %d", currentFloor, queue.size()));
            Thread.sleep(1000);
            currentFloor--;
        }
    }

    private void moveUp(int floor) throws InterruptedException {
        while (currentFloor != floor) {
            System.out.println(String.format("lift in %d floor || Ожидают еще %d", currentFloor, queue.size()));
            Thread.sleep(1000);
            currentFloor++;
        }
    }
}
