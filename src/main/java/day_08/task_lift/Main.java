package day_08.task_lift;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
        Home home = new Home(20);

        Thread addUsers = new Thread(new Runnable() {
            @Override
            public void run() {
                Random random = new Random();
                int counterAntoh = 1;
                try {
                    while (true) {
                        int nextFloor = random.nextInt(20) + 1;
                        int nFloor = random.nextInt(20) + 1;
                        System.out.println(String.format("Вызвали на этаж %d ", nFloor));

//                        home.addHuman(new Human("Не антоха" + counterAntoh++, nextFloor), nFloor);
                        home.addHuman(new Human("Антоха_" + counterAntoh++, nextFloor), nFloor);
                        Thread.sleep(random.nextInt(20000) + 1);

                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        addUsers.start();
    }
}
