package day_15.lesson.analys;

public class Client {
    private String name;
    private int salary;
    private boolean isMaried;
    private short childCount;
    private String profession;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public boolean isMaried() {
        return isMaried;
    }

    public void setMaried(boolean maried) {
        isMaried = maried;
    }

    public short getChildCount() {
        return childCount;
    }

    public void setChildCount(short childCount) {
        this.childCount = childCount;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Client(String name, int salary, boolean isMaried, short childCount, String profession, int age) {
        this.name = name;
        this.salary = salary;
        this.isMaried = isMaried;
        this.childCount = childCount;
        this.profession = profession;
        this.age = age;
    }
}
