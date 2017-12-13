package day_08.task_01;

public class Task {

    /*    1) Напишите программу, которая каждую секунду отображает на экране данные о времени, прошедшем от начала сессии,
     а другой ее поток выводит сообщение каждые 5 секунд. Предусмотрите возможность ежесекундного оповещения потока,
      воспроизводящего сообщение, потоком, отсчитывающим время.
    2) Не внося изменений в код потока-"хронометра" , добавьте еще один поток,
     который выводит на экран другое сообщение каждые 7 секунд. Предполагается
      использование методов wait(), notifyAll().
      */
    public static void main(String[] args) {
//        Object monitor = new Object();
//        Timer timer = new Timer(1000);
//        Thread thread = new Thread(timer);
//        thread.start();
//        MyTiming timing = new MyTiming(timer);
//        timing.start();
//        synchronized (timer){

//        }

        Thread thread = new Thread(() -> {
            int i = 5000;
            while (true) {
                System.out.println(i += i);
            }
        });
        thread.start();

        Object monitor = new Object();
        synchronized (monitor) {

        }

    }
}


class Timer implements Runnable {
    int time = 0;
    int localTime = 0;

    Timer(int time) {
        this.time = time;
    }

    @Override
    public void run() {
        startTimer();
    }

    void startTimer() {
        while (true) {
            try {
                wait(time);
                System.out.println((localTime += time) / 1000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (localTime % 5 == 0) {
                notifyAll();
            }
        }
    }

    int getTime() {
        return localTime;
    }
}

class MyTiming extends Thread {

    Timer timer;

    MyTiming(Timer timer) {
        this.timer = timer;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println(timer.getTime());
        }
    }
}
