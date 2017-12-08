package day_05;

import java.io.*;
import java.util.List;

public class EmployeManager {

    private final String fileName = "Employers.dat";

    public void save(Employe emp) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
            out.writeObject(emp);
        }
    }

    public void delete(Employe emp) {
    }

    public Employe getByName(String name) throws IOException {
        try (ObjectInputStream oin = new ObjectInputStream(new FileInputStream(fileName))){
            getLonelyEmploye(name, oin);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        throw new NotFoundEmploye();
    }

    private Employe getLonelyEmploye(String employeName, ObjectInputStream oin) throws IOException, ClassNotFoundException {
        Employe readEl = null;
        do {
            readEl = (Employe) oin.readObject();
            if (readEl!= null && employeName.equals(readEl.getName())){
                return readEl;
            }
        } while (readEl != null);

        throw new NotFoundEmploye();
    }

    private List<Employe> getAllEmployers(ObjectInputStream oin) throws IOException, ClassNotFoundException {
        List<Employe> readList = (List<Employe>) oin.readObject();
        return readList;
    }

    public List<Employe> getByJob(String job) {
        return null;
    }
}

class NotFoundEmploye extends RuntimeException {

}
