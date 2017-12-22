package com.pervushow.analys;

import java.util.ArrayList;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        Client johnConnor =
                new Client("John",
                        10000,
                        false,
                        (short) 0,
                        "chief",
                        42);

        StoryPoint point1 = new
                StoryPoint(johnConnor, 5000, 5, false);
        StoryPoint point2 = new
                StoryPoint(johnConnor, 10000, 10, true);

        Client josephDread =
                new Client("Joseph",
                        50000,
                        false,
                        (short) 0,
                        "soldier",
                        50);

        StoryPoint point3 = new
                StoryPoint(josephDread, 5000, 3, false);

        Client saharConor =
                new Client("Sarah",
                        5000,
                        false,
                        (short) 1,
                        "weitress",
                        28);

        StoryPoint point4 = new
                StoryPoint(saharConor, 6000, 3, false);
        StoryPoint point5 = new
                StoryPoint(saharConor, 7000, 4, false);
        StoryPoint point6 = new
                StoryPoint(saharConor, 8000, 5, true);

        Client rembo =
                new Client("John",
                        70000,
                        true,
                        (short) 0,
                        "soldier",
                        30);
        StoryPoint point7 = new
                StoryPoint(rembo, 8000, 5, true);

        Client donCarlione =
                new Client("Wito",
                        9999999,
                        true,
                        (short) 5,
                        "mafioso",
                        40);
        StoryPoint point8 = new
                StoryPoint(donCarlione, 800000, 5, false);

        Client yuillSmith =
                new Client("Yuill",
                        700,
                        false,
                        (short) 1,
                        "lumpen",
                        30);
        StoryPoint point9 = new
                StoryPoint(yuillSmith, 5000, 5, true);

        ArrayList<Client> clients = new ArrayList<>();
        clients.add(johnConnor);
        clients.add(josephDread);
        clients.add(saharConor);
        clients.add(rembo);
        clients.add(donCarlione);
        clients.add(yuillSmith);

        ArrayList<StoryPoint> storyPoints = new ArrayList<>();
        storyPoints.add(point1);
        storyPoints.add(point2);
        storyPoints.add(point3);
        storyPoints.add(point4);
        storyPoints.add(point5);
        storyPoints.add(point6);
        storyPoints.add(point7);
        storyPoints.add(point8);
        storyPoints.add(point9);

        int resultBall = 0;
        //rembo, 5000, 5
        final int[] ball = {0};
        storyPoints
                .stream()
                .filter((point) -> point.getClient().equals(rembo))
                .forEach((point) -> {
                    if (point.isFault()) {
                        ball[0]--;
                        ball[0]--;
                    } else {
                        ball[0]++;
                    }
                });
        final int[] mariageBall = {0};
        storyPoints
                .stream()
                .filter((point) -> point.getClient().isMaried() == rembo.isMaried())
                .forEach((point) -> mariageBall[0] += point.isFault() ? -1 : 1);

        boolean isAnyProfFault =
                storyPoints
                        .stream()
                        .filter(point ->
                                point.getClient()
                                        .getProfession()
                                        .equals(rembo.getProfession()))
                        .anyMatch(point ->
                                point.isFault());

        boolean isAllProfFault =
                storyPoints
                        .stream()
                        .filter(point ->
                                point.getClient()
                                        .getProfession()
                                        .equals(rembo.getProfession()))
                        .allMatch(point ->
                                point.isFault());

        Optional<Client> clientSumSalary =
                clients
                        .stream()
                        .reduce((client1, client2) -> {
                            Client temp = new Client("",
                                    client1.getSalary()
                                            + client2.getSalary(),
                                    false, (short) 0, "", 0);
                            return temp;
                        });

        resultBall += ball[0];
        resultBall += mariageBall[0];
        resultBall += isAnyProfFault ? 0 : 5;
        resultBall += isAllProfFault ? -10 : 0;
    }

}
