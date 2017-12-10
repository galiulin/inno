package day_05;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

public class EmployeeManagerTest {
    private Employee employee = new Employee("0", 30, 150000, "tester");
    final File file = new File("Employers.dat");

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void save() throws IOException {
        EmployeeManager manager = new EmployeeManager();
        manager.save(employee);
        manager.save(new Employee("1", 54, 999999, "javaProgr"));
        manager.save(new Employee("2", 54, 999999, "javaProgr"));
        manager.save(new Employee("3", 54, 999999, "javaProgr"));
        manager.save(new Employee("4", 54, 999999, "javaProgr"));
        manager.save(new Employee("6", 54, 999999, "javaProgr"));
        manager.save(new Employee("7", 78, 123414, "javaProcdcdcdcd"));
        manager.save(new Employee("8", 78, 123414, "javaProcdcdcdcd"));
        manager.save(new Employee("9", 78, 123414, "javaProcdcdcdcd"));
        manager.save(new Employee("10", 78, 123414, "javaProcdcdcdcd"));
        manager.save(new Employee("11", 78, 123414, "javaProcdcdcdcd"));
        manager.save(new Employee("12", 78, 123414, "javaProcdcdcdcd"));
        manager.save(new Employee("13", 78, 123414, "javaProcdcdcdcd"));
        manager.save(new Employee("14", 78, 123414, "Exclusive Job"));
        manager.save(new Employee("15", 54, 999999, "javaProgr"));
    }

    @Test
    public void delete() throws IOException {
        EmployeeManager em = new EmployeeManager();
        em.delete(em.getByName("4"));
//        assertFalse(file.exists());
    }

    @Test
    public void getByName() throws IOException {
        EmployeeManager em = new EmployeeManager();
//        Employee emFromFile = em.getByName("test user");
//        assertEquals(employee.getName(), emFromFile.getName());
//        assertEquals(employee.getAge(), emFromFile.getAge());
//        assertEquals(employee.getJob(), emFromFile.getJob());
//        assertEquals(employee.getSalary(), emFromFile.getSalary());
//
        Employee employee1 = em.getByName("14");
        assertEquals(employee1.getName(), "14");
        assertEquals(employee1.getSalary(), 123414);
    }

    @Test
    public void getByJob() throws IOException {
        EmployeeManager em = new EmployeeManager();

        List<Employee> list = em.getByJob("Exclusive Job");


//        Employee employee = (Employee) em.getByJob("Exclusive Name");
        assertEquals(list.get(0).getName(), "14");
    }

    @Test
    public void getAllEmployers() throws IOException {
        EmployeeManager em = new EmployeeManager();
        List<Employee> list = em.getAllEmployers();
        System.out.println(list.size());
        for (Employee employee : list) {
            System.out.println(employee);
        }
    }

    @Test
    public void getSalary() throws IOException {
        EmployeeManager manager = new EmployeeManager();
        int sum = 0;
        System.out.println(sum = manager.getSalary());
        assertEquals(13274613, sum);
    }

    @Test
    public void writeSalary() throws IOException {
        EmployeeManager manager = new EmployeeManager();
        int salary = 0;

        manager.writeSalary(4545);
        System.out.println(salary = manager.readSalaryAmount());
        assertEquals(new Integer(4545),(Integer) salary);
    }

    @Test
    public void rewriteSalary() throws IOException {
        file.delete();
        save();
        getAllEmployers();
        writeSalary();
        getAllEmployers();

    }

    @Test
    public void writeOrUpdate() throws IOException {
        EmployeeManager em = new EmployeeManager();
        em.writeOrUpdate(new Employee("10", 78, 123414, "Updated"));
    }
}