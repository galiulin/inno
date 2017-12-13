package day_08.task_01;

import static java.lang.Thread.sleep;

public class Task {

    /*    1) Напишите программу, которая каждую секунду отображает на экране данные о времени, прошедшем от начала сессии,
     а другой ее поток выводит сообщение каждые 5 секунд. Предусмотрите возможность ежесекундного оповещения потока,
      воспроизводящего сообщение, потоком, отсчитывающим время.
    2) Не внося изменений в код потока-"хронометра" , добавьте еще один поток,
     который выводит на экран другое сообщение каждые 7 секунд. Предполагается
      использование методов wait(), notifyAll().
      */
    public static void main(String[] args) {
        Timer timer = new Timer();

        Thread thread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                timer.uppendTime(1000);
            }
        });
        thread.start();
        Thread thread1 = new Thread(() -> {
            System.out.println(timer.getTime());
        });
        thread1.start();
    }
}


class Timer {
    int time = 0;
    volatile int localTime = 0;

    Timer() {
    }

    synchronized void uppendTime(int time) {
        localTime = localTime + time;
    }

    synchronized int getTime() {
        return localTime;
    }
}

class MyTiming extends Thread {

}
