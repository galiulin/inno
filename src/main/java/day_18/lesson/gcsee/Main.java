package day_18.lesson.gcsee;

import java.util.*;

public class Main {

    private static Map<String, List<String>>
            cache = new HashMap<>();

    private static final int ITER = 3_000;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        boolean exit = false;

        while (!exit) {
            System.out.println("Enter command");
            switch (scanner.nextLine()) {
                case "l":
                    System.out.println("Leakable started");
                    createLeakable();
                    break;
                case "c":
                    System.out.println("Collectable started");
                    createCollectable();
                    break;
                default:
                    exit = true;
                    break;
            }
        }
    }

    private static void createLeakable() {
        for (int i = 0; i < ITER; i++) {
            cache.put(i + "", createBigList());
            if (i % 100 == 0)
                System.out.println("leakable" + i);
        }
    }

    private static void createCollectable() {
        Map<String, List<String>> local =
                new HashMap<>();

        for (int i = 0; i < ITER; i++) {
            local.put(i + "", createBigList());
            if (i % 100 == 0)
                System.out.println("collectable" + i);
        }
    }

    private static List<String> createBigList() {
        List<String> bigList = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < ITER; i++) {
            bigList.add(random.nextInt() + "");
        }

        return bigList;
    }
}
