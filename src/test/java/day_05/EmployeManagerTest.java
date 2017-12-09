package day_05;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

public class EmployeManagerTest {
    private Employe employe = new Employe("0", 30, 150000, "tester");
    final File file = new File("Employers.dat");

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    public void save() throws IOException {
        EmployeManager manager = new EmployeManager();
        manager.save(employe);
        manager.save(new Employe("1", 54, 999999, "javaProgr"));
        manager.save(new Employe("2", 54, 999999, "javaProgr"));
        manager.save(new Employe("3", 54, 999999, "javaProgr"));
        manager.save(new Employe("4", 54, 999999, "javaProgr"));
        manager.save(new Employe("6", 54, 999999, "javaProgr"));
        manager.save(new Employe("7", 78, 123414, "javaProcdcdcdcd"));
        manager.save(new Employe("8", 78, 123414, "javaProcdcdcdcd"));
        manager.save(new Employe("9", 78, 123414, "javaProcdcdcdcd"));
        manager.save(new Employe("10", 78, 123414, "javaProcdcdcdcd"));
        manager.save(new Employe("11", 78, 123414, "javaProcdcdcdcd"));
        manager.save(new Employe("12", 78, 123414, "javaProcdcdcdcd"));
        manager.save(new Employe("13", 78, 123414, "javaProcdcdcdcd"));
        manager.save(new Employe("14", 78, 123414, "Exclusive Job"));
        manager.save(new Employe("15", 54, 999999, "javaProgr"));
    }

    @Test
    public void delete() throws IOException {
        EmployeManager em = new EmployeManager();
        em.delete(em.getByName("4"));
//        assertFalse(file.exists());
    }

    @Test
    public void getByName() throws IOException {
        EmployeManager em = new EmployeManager();
//        Employe emFromFile = em.getByName("test user");
//        assertEquals(employe.getName(), emFromFile.getName());
//        assertEquals(employe.getAge(), emFromFile.getAge());
//        assertEquals(employe.getJob(), emFromFile.getJob());
//        assertEquals(employe.getSalary(), emFromFile.getSalary());
//
        Employe employe1 = em.getByName("14");
        assertEquals(employe1.getName(), "14");
        assertEquals(employe1.getSalary(), 123414);
    }

    @Test
    public void getByJob() throws IOException {
        EmployeManager em = new EmployeManager();

        List<Employe> list = em.getByJob("Exclusive Job");


//        Employe employe = (Employe) em.getByJob("Exclusive Name");
        assertEquals(list.get(0).getName(), "Exclusive Name");
    }

    @Test
    public void getAllEmployers() throws IOException {
        EmployeManager em = new EmployeManager();
        List<Employe> list = em.getAllEmployers();
        System.out.println(list.size());
        for (Employe employe : list) {
            System.out.println(employe);
        }
    }

    @Test
    public void getSalary() throws IOException {
        EmployeManager manager = new EmployeManager();
        int sum = 0;
        System.out.println(sum = manager.getSalary());
        assertEquals(13274613, sum);
    }

    @Test
    public void writeSalary() throws IOException {
        EmployeManager manager = new EmployeManager();
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
}