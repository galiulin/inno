package day_05;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeManager {

    private final File file;
    private final String fileName;

    public EmployeManager() {
        fileName = "Employers.dat";
        file = new File(fileName);
    }

    public void save(Employe emp) throws IOException {
        writeIntoFile((out) -> {
            out.writeObject(emp);
            return true;
        });
    }

    private void saveAll(List<Employe> list) throws IOException {
        writeIntoFile(out -> {
            for (int i = 0; i < list.size(); i++) {
                out.writeObject(list.get(i));
            }
            return true;
        });
    }

    private <T> void writeIntoFile(FunctionIO<ObjectOutputStream, T> functionIO) throws IOException {
        if (!file.exists()) {
            try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName, true))) {
                functionIO.apply(out);
            } catch (ClassNotFoundException ex) {
                /*do nothing*/
            }
        } else {
            try (ObjectOutputStream out = new AppendingObjectOutputStream(new FileOutputStream(fileName, true))) {
                functionIO.apply(out);
            } catch (ClassNotFoundException ex) {
                /*do nothing*/
            }
        }
    }

    public void delete(Employe emp) throws IOException {
        List<Employe> list = getAllEmployers();
        list.remove(emp);
        overwriteFile(list);
    }

    public Employe getByName(String name) throws IOException {
        Employe readEl = getObjectsFromFile((objInStr) -> {
            Employe empl = null;
            do {
                try {
                    empl = (Employe) objInStr.readObject();
                    if (empl != null && name.equals(empl.getName())) {
                        return empl;
                    }
                } catch (EOFException ex) {
                    empl = null;
                }
            } while (empl != null);
            return empl;
        });
        if (readEl == null) throw new NotFoundEmploye();

        return readEl;
    }

    private <O> O getObjectsFromFile(FunctionIO<ObjectInputStream, O> function) throws IOException {
        O object = null;
        try (ObjectInputStream oin = new ObjectInputStream(new FileInputStream(fileName))) {
            object = function.apply(oin);
        } catch (ClassNotFoundException ex) {
            /*do nothing*/
        }
        return object;
    }


    public List<Employe> getAllEmployers() throws IOException {
        return getObjectsFromFile((oin) -> {
            List<Employe> arList = new ArrayList<>();

            Object obj;
            do {
                try {
                    obj = oin.readObject();
                    if (obj instanceof Employe) {
                        arList.add((Employe) obj);
                    }
                } catch (EOFException ex) {
                    obj = null;
                }
            } while (obj != null);
            return arList;
        });
    }

    public List<Employe> getByJob(String job) throws IOException {
        List<Employe> list;
        list = getByJobFromFile(job);
        if (list.size() == 0) throw new NotFoundEmploye("by job: " + job);
        return list;
    }

    private List<Employe> getByJobFromFile(String job) throws IOException {
        return getObjectsFromFile((oin) -> {
            List<Employe> arList = new ArrayList<>();
            Employe employe = (Employe) oin.readObject();
            while (employe != null) {
                if (job != null && job.equals(employe.getJob())) {
                    arList.add(employe);
                }
                try {
                    employe = (Employe) oin.readObject();
                } catch (EOFException ex) {
                    employe = null;
                }
            }
            return arList;
        });
    }

    public int getSalary() throws IOException {
        List<Employe> list = getAllEmployers();
        return calculateSalary(list);
    }

    private int calculateSalary(List<Employe> list) {
        int salary = 0;
        for (Employe employe : list) {
            salary += employe.getSalary();
        }
        return salary;
    }

    public Integer readSalaryAmount() throws IOException {
        Integer salary = getObjectsFromFile(oin -> {
            Integer lastNumber = null;
            Object obj = null;
            boolean check = false;
            do {
                try {
                    obj = oin.readObject();
                    if (obj instanceof Integer){
                        lastNumber = (Integer) obj;
                    }
                } catch (EOFException ex){
                    check = true;
                    return lastNumber;
                }
            } while (!check);
            return lastNumber;
        });
        return salary;
    }

    private void overwriteFile(List<Employe> list) throws IOException {
        Integer salary = calculateSalary(list);
        file.delete();
        saveAll(list);
        writeSalary(salary);
    }

    public void writeSalary(int salary) throws IOException {
        Integer salar = salary;
        writeIntoFile((out) -> {
            out.writeObject(salar);
            return true;
        });
    }
}

class NotFoundEmploye extends RuntimeException {
    public NotFoundEmploye() {
        super();
    }

    public NotFoundEmploye(String message) {
        super(message);
    }
}

class AppendingObjectOutputStream extends ObjectOutputStream {

    public AppendingObjectOutputStream(OutputStream out) throws IOException {
        super(out);
    }

    @Override
    protected void writeStreamHeader() throws IOException {
        // do not write a header, but reset:
        // this line added after another question
        // showed a problem with the original
        reset();
    }
}

@FunctionalInterface
interface FunctionIO<T, R> {
    R apply(T obj) throws IOException, ClassNotFoundException;
}
