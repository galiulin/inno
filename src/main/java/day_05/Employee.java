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

public class Employee implements Serializable {
    private String name;
    private int age;
    private int salary;
    private String job;

    public Employee(String name, int age, int salary, String job) {
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
        Employee employee = (Employee) o;
        return getAge() == employee.getAge() &&
                getSalary() == employee.getSalary() &&
                Objects.equals(getName(), employee.getName()) &&
                Objects.equals(getJob(), employee.getJob());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getName(), getAge(), getSalary(), getJob());
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                ", job='" + job + '\'' +
                '}';
    }
}
