package day_05;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeManager {

    private final File file;
    private final String fileName;

    public EmployeeManager() {
        fileName = "Employers.dat";
        file = new File(fileName);
    }

    public void save(Employee emp) throws IOException {
        writeIntoFile((out) -> {
            out.writeObject(emp);
            return true;
        });
    }

    private void saveAll(List<Employee> list) throws IOException {
        writeIntoFile(out -> {
            for (int i = 0; i < list.size(); i++) {
                out.writeObject(list.get(i));
            }
            return true;
        });
    }

    public void writeOrUpdate(Employee obj) throws IOException {
        List<Employee> list = getAllEmployers();
        for (int i = 0; i < list.size(); i++) {
            if (obj.getName().equals(list.get(i).getName())){
                list.set(i, obj);
                saveAll(list);
                return;
            }
        }
        save(obj);

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

    public void delete(Employee emp) throws IOException {
        List<Employee> list = getAllEmployers();
        list.remove(emp);
        overwriteFile(list);
    }

    public Employee getByName(String name) throws IOException {
        Employee readEl = getObjectsFromFile((objInStr) -> {
            Employee empl = null;
            do {
                try {
                    empl = (Employee) objInStr.readObject();
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


    public List<Employee> getAllEmployers() throws IOException {
        return getObjectsFromFile((oin) -> {
            List<Employee> arList = new ArrayList<>();

            Object obj;
            do {
                try {
                    obj = oin.readObject();
                    if (obj instanceof Employee) {
                        arList.add((Employee) obj);
                    }
                } catch (EOFException ex) {
                    obj = null;
                }
            } while (obj != null);
            return arList;
        });
    }

    public List<Employee> getByJob(String job) throws IOException {
        List<Employee> list;
        list = getByJobFromFile(job);
        if (list.size() == 0) throw new NotFoundEmploye("by job: " + job);
        return list;
    }

    private List<Employee> getByJobFromFile(String job) throws IOException {
        return getObjectsFromFile((oin) -> {
            List<Employee> arList = new ArrayList<>();
            Object obj = null;
            Employee employee;
            do {
                try {
                    obj = oin.readObject();
                    if (obj instanceof Employee) {
                        employee = (Employee) obj;
                        if (job.equals(((Employee) obj).getJob())) {
                            arList.add(employee);
                        }
                    }
                } catch (EOFException ex) {
                    return arList;
                }
            } while (obj != null);
            return arList;
        });
    }

    public int getSalary() throws IOException {
        List<Employee> list = getAllEmployers();
        return calculateSalary(list);
    }

    private int calculateSalary(List<Employee> list) {
        int salary = 0;
        for (Employee employee : list) {
            salary += employee.getSalary();
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
                    if (obj instanceof Integer) {
                        lastNumber = (Integer) obj;
                    }
                } catch (EOFException ex) {
                    check = true;
                    return lastNumber;
                }
            } while (!check);
            return lastNumber;
        });
        return salary;
    }

    private void overwriteFile(List<Employee> list) throws IOException {
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
