package day_08.lesson;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

public class Main {

    static Map<String, String> myMap = new HashMap<>();

    public static void main(String[] args) {
        final Random random = new Random();

        for (int i = 0; i < 10_000; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(random.nextInt(5000));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() +
                            " started");

                    for (int j = 0; j < 10_000; j++) {
                        try {
                            Thread.sleep(random.nextInt(50));
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        myMap.put(random.nextInt(1000) + "",
                                random.nextInt() + "");

                        if (j % 1000 == 0) {
                            Iterator<String> iterator =
                                    myMap.keySet().iterator();

                            while (iterator.hasNext()) {
                                System.out.println(
                                        myMap.get(iterator.next())
                                );
                            }
                        }
                    }
                    System.out.println(Thread.currentThread().getName() +
                            " ended");
                }
            });

            thread.start();
        }
    }
}
