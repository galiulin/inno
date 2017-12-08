package day_05;

import java.io.Serializable;
import java.util.Objects;

/*Итерация 1 сериализация по умолчанию
 * Итерация 2 дописать в конец файла сумму всех зарплат
 * Итерация 3 пользовательская сериализация
 * Итерация 4 saveOrUpdate(name)*/
/*Итерация 5 change all work using BufferingStreams
 *
 * решение должно быть грамотно разбито на классы, структурировано, работа с ошибками
 * подумать над тем что возможно стоит держать стрим открытым*/
public class Employe implements Serializable {
    private String name;
    private int age;
    private int salary;
    private String job;

    public Employe(String name, int age, int salary, String job) {
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.job = job;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employe employe = (Employe) o;
        return getAge() == employe.getAge() &&
                getSalary() == employe.getSalary() &&
                Objects.equals(getName(), employe.getName()) &&
                Objects.equals(getJob(), employe.getJob());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getName(), getAge(), getSalary(), getJob());
    }

    @Override
    public String toString() {
        return "Employe{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                ", job='" + job + '\'' +
                '}';
    }
}
