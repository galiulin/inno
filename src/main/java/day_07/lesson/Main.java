package day_07.lesson;

import java.util.Scanner;

public class Main {
    static int x = 0;

    public static void main(String[] args) {
        Thread threadMain = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    Thread thread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            x = 5;
                        }
                    });

                    Thread thread2 = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            if(x == 5){
                                System.out.println(5);
                            } else  {
                                System.out.println(0);
                            }
                        }
                    });

                    try {
                        //x = 0;
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    thread.start();
                    thread2.start();
                }
            }
        });
        threadMain.start();

        Scanner scanner = new Scanner(System.in);
        scanner.next();
    }
}
