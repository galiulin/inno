package day_06.race;

import java.util.Random;

/**
 * Created by a.pervushov on 30.11.2017.
 */
public class Race {
    public static Integer x = 0;

    public static void main(String[] args) {

        while (true) {
            Thread thread1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    x++;
                }
            });

            Thread thread2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    if(x == 0){
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        x = 0;
                        System.out.println(x);
                    } else {
                        System.out.println(1);
                    }
                }
            });

            thread2.start();
            thread1.start();
        }
    }
}
