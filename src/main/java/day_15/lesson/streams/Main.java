package com.pervushow.streams;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        Gamer puddyPay = new Gamer(75l,
                true,
                (short) 100,
                "PuddyPay",
                false,
                GameType.GTA);
        Gamer kolento = new Gamer(55l,
                true,
                (short) 60,
                "KolentoHS",
                true,
                GameType.HEARTSTONE);
        Gamer dendy = new Gamer(60l,
                true,
                (short) 100,
                "NavyDendi",
                false,
                GameType.DOTA2);
        Gamer dread = new Gamer(90l,
                false,
                (short) 100,
                "Dread",
                false,
                GameType.OTHER);
        Gamer silverName = new Gamer(105l,
                true,
                (short) 100,
                "SilverName",
                true,
                GameType.HEARTSTONE);
        Gamer xPeke = new Gamer(70l,
                true,
                (short) 100,
                "xPekke",
                false,
                GameType.STARCRAFT);

        ArrayList<Gamer> gamerList = new ArrayList<>();
        gamerList.add(puddyPay);
        gamerList.add(kolento);
        gamerList.add(dendy);
        gamerList.add(dread);
        gamerList.add(silverName);
        gamerList.add(xPeke);

        System.out.println("All gamers");
        printAll(gamerList);

        System.out.println("Virgins");
        printAll(
                collect(gamerList
                        .stream()
                        .filter(
                                (g) -> g.isVirgin()
                        )
                )
        );

        System.out.println("Sorted by weight");
        Stream<Gamer> sorted = gamerList
                .stream()
                .sorted(
                        (g1, g2) ->
                                (int) (g1.getWeight() - g2.getWeight())
                );
        printAll(collect(sorted));

        System.out.println(gamerList.stream().anyMatch((g) -> !g.isVirgin()));
        System.out.println(gamerList.stream().allMatch((g) -> g.isProf()));
        System.out.println("Find HS");
        gamerList
                .parallelStream()
                .filter((g) -> g.getGameType() == GameType.HEARTSTONE)
                .findAny().ifPresent(
                (g) -> System.out.println(g.toString()
                )
        );

        System.out.println("Map to Worker");
        gamerList
                .stream()
                .filter((g) -> !g.isProf())
                .map((g) -> {
                    return new Worker(g.getWeight() - 10,
                            g.getNickName(),
                            "Lumpen",
                            5000
                    );
                }).forEach((w) -> System.out.println(w.toString()));

        final int[] result = {0};
        gamerList
                .stream()
                .filter((g) -> g.isProf())
                .map((g) -> {
                    return new Worker(g.getWeight() - 10,
                            g.getNickName(),
                            "Commentor",
                            50000
                    );
                }).map((w) -> w.getSalary()).forEach((s) -> result[0] += s);
        System.out.println("Sum salary " + result[0]);

        Optional<String> count = gamerList
                .parallelStream()
                .map((g) -> g.getNickName())
                .reduce(
                        (g1, g2) -> {
                            return g1 + g2;
                        }
                );
        System.out.println(
                countOfCharInStr(
                        count.get().toCharArray(), 'd'));
    }

    private static int countOfCharInStr(char[] strMas, char symbol) {
        int result = 0;

        for (char temp :
                strMas) {
            if (temp == symbol) {
                result++;
            }
        }

        return result;
    }

    private static ArrayList<Gamer> collect(Stream<Gamer> stream) {
        ArrayList<Gamer> gamers = new ArrayList<>();
        stream.forEach((g) -> gamers.add(g));

        return gamers;
    }

    private static void printAll(ArrayList<Gamer> list) {
        list.forEach(
                (gamer) ->
                        System.out.println(gamer.toString())
        );//terminal
    }
}
