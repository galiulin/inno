package day_05;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

public class EmployeManagerTest {
    private Employe employe = new Employe("test user", 30, 150000, "tester");
    final File file = new File("Employers.dat");

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void save() throws IOException {
        EmployeManager manager = new EmployeManager();
        manager.save(employe);
        manager.save(new Employe("second", 54, 999999, "javaProgr"));
        manager.save(new Employe("second", 54, 999999, "javaProgr"));
        manager.save(new Employe("second", 54, 999999, "javaProgr"));
        manager.save(new Employe("second", 54, 999999, "javaProgr"));
        manager.save(new Employe("second", 54, 999999, "javaProgr"));
        manager.save(new Employe("second", 54, 999999, "javaProgr"));
        manager.save(new Employe("notHaveName", 78, 123414, "javaProcdcdcdcd"));
        manager.save(new Employe("notHaveName", 78, 123414, "javaProcdcdcdcd"));
        manager.save(new Employe("notHaveName", 78, 123414, "javaProcdcdcdcd"));
        manager.save(new Employe("notHaveName", 78, 123414, "javaProcdcdcdcd"));
        manager.save(new Employe("notHaveName", 78, 123414, "javaProcdcdcdcd"));
        manager.save(new Employe("notHaveName", 78, 123414, "javaProcdcdcdcd"));
        manager.save(new Employe("notHaveName", 78, 123414, "javaProcdcdcdcd"));
//        manager.save(new Employe("Exclusive Name", 78, 123414, "Exclusive Job"));
    }

    @Test
    public void delete() throws IOException {
        EmployeManager em = new EmployeManager();
        em.delete(em.getByName("second"));
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
        Employe employe1 = em.getByName("Exclusive Name");
        assertEquals(employe1.getName(), "Exclusive Name");
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
        for (Employe employe : list){
            System.out.println(employe);
        }
    }
}