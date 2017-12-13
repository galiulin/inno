package day_08.task_lift;

import java.util.concurrent.ConcurrentLinkedQueue;

public class Home {
    private final ConcurrentLinkedQueue<Pair> isWanted;
    private final int countFloors;
    private final Lift lift;

    public Home(int countFloors) {
        this.isWanted = new ConcurrentLinkedQueue<>();
        this.countFloors = countFloors;
        this.lift = new Lift(isWanted);

        Thread threadWithLift = new Thread(() -> lift.on());
        threadWithLift.start();
    }

    public void addHuman(Human human, int userChoise) {
        Pair pair = new Pair(human, userChoise);
        isWanted.add(pair);
        synchronized (lift) {
            lift.notifyAll();
        }
    }

    class Pair {
        Human human;
        int enterFloor;

        Pair(Human human, int userChoise) {
            this.human = human;
            this.enterFloor = userChoise;
        }

        public Human getHuman() {
            return human;
        }

        public int getEnterFloor() {
            return enterFloor;
        }
    }
}
