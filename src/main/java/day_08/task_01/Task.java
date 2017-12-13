package day_08.task_01;

public class Task {

    /*    1) Напишите программу, которая каждую секунду отображает на экране данные о времени, прошедшем от начала сессии,
     а другой ее поток выводит сообщение каждые 5 секунд. Предусмотрите возможность ежесекундного оповещения потока,
      воспроизводящего сообщение, потоком, отсчитывающим время.
    2) Не внося изменений в код потока-"хронометра" , добавьте еще один поток,
     который выводит на экран другое сообщение каждые 7 секунд. Предполагается
      использование методов wait(), notifyAll().
      */

    static volatile int localTime;
    static final Object monitor = new Object();

    public static void main(String[] args) {
        localTime = 0;

        Thread thread = new Thread(() -> {
            synchronized (monitor) {
                while (true) {
                    try {
                        monitor.wait(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    localTime += 1000;
                    monitor.notifyAll();
                }
            }
        });
        thread.start();

        Thread thread1 = new Thread(() -> {
            while (true) {
                synchronized (monitor) {
                    try {
                        monitor.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (localTime % 5000 == 0)
                    System.out.println(localTime / 1000);
                }
            }
        });
        thread1.start();

        Thread thread2 = new Thread(() -> {
            while (true) {
                synchronized (monitor) {
                    try {
                        monitor.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (localTime % 7000 == 0)
                    System.out.println(localTime / 1000);
                }
            }
        });
        thread2.start();
    }
}