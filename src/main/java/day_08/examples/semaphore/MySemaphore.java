package day_08.examples.semaphore;

import java.util.concurrent.Semaphore;

public class MySemaphore {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(2);
        IncThread incThread = new IncThread(semaphore, "inc");
        DecThread decThread = new DecThread(semaphore, "dec");
        Thread thread = new Thread(incThread);
        Thread thread2 = new Thread(decThread);
        thread.start();
        thread2.start();
    }
}

//общий ресурс
class Shared {
    static int count = 0;
}

class IncThread implements Runnable{
    String name;
    Semaphore semaphore;

    IncThread(Semaphore semaphore, String name){
        this.semaphore = semaphore;
        this.name = name;
    }
    @Override
    public void run() {
        System.out.printf("Запуск потока %s \r\n", name);
        try {
            System.out.printf("Поток %s ожидает разрешения \r\n", name);
            semaphore.acquire();
            System.out.printf("Поток %s получает разрешения \r\n", name);

            for (int i = 0; i < 5; i++) {
                Shared.count++;
                System.out.printf("%s : %d \n", name, Shared.count);
                Thread.sleep(10);
            }
        } catch (InterruptedException ex){
            System.out.println(ex);
        }

        System.out.printf("Поток %s освобождает разрешени\n", name);
        semaphore.release();
    }
}

class DecThread implements Runnable{
    String name;
    Semaphore semaphore;

    DecThread(Semaphore semaphore, String name){
        this.semaphore = semaphore;
        this.name = name;
    }
    @Override
    public void run() {
        System.out.printf("Запуск потока %s \r\n", name);
        try {
            System.out.printf("Поток %s ожидает разрешения \r\n", name);
            semaphore.acquire();
            System.out.printf("Поток %s получает разрешения \r\n", name);

            for (int i = 0; i < 5; i++) {
                Shared.count--;
                System.out.printf("%s : %d\n", name, Shared.count);
                Thread.sleep(10);
            }
        } catch (InterruptedException ex){
            System.out.println(ex);
        }

        System.out.printf("Поток %s освобождает разрешениe\n", name);
        semaphore.release();
    }
}
