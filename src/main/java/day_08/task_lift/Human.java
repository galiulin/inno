package day_08.task_lift;

public class Human {
    private String name;
    private int outFloor;

    public Human(String name, int outFloor) {
        this.name = name;
        this.outFloor = outFloor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOutFloor() {
        return outFloor;
    }

    public void setOutFloor(int outFloor) {
        this.outFloor = outFloor;
    }
}
