package day_05;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class EmployeManagerTest {
    private Employe employe = new Employe("test user", 30, 150000, "tester");

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
    }

    @Test
    public void delete() {
    }

    @Test
    public void getByName() throws IOException {
        EmployeManager em = new EmployeManager();
        Employe emFromFile = em.getByName("test user");
        assertEquals(employe.getName(), emFromFile.getName());
        assertEquals(employe.getAge(), emFromFile.getAge());
        assertEquals(employe.getJob(), emFromFile.getJob());
        assertEquals(employe.getSalary(), emFromFile.getSalary());
    }

    @Test
    public void getByJob() {
    }
}