package com.pervushow.streams;

public class Worker {
    private long weight;
    private String nickName;

    @Override
    public String toString() {
        return "Worker{" +
                "weight=" + weight +
                ", nickName='" + nickName + '\'' +
                ", profession='" + profession + '\'' +
                ", salary=" + salary +
                '}';
    }

    private String profession;
    private int salary;

    public Worker(long weight, String nickName, String profession, int salary) {
        this.weight = weight;
        this.nickName = nickName;
        this.profession = profession;
        this.salary = salary;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public long getWeight() {
        return weight;
    }

    public void setWeight(long weight) {
        this.weight = weight;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }
}
