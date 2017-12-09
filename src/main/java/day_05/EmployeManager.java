package day_05;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeManager {

    private final File file;
    private final String fileName;
//    private List<Employe> list;
//    private HashFile hashFile;
//    private String hash;

    public EmployeManager() throws IOException {
        fileName = "Employrs.dat";
        file = new File(fileName);
//        initHash();
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
//                out.flush();
            } catch (ClassNotFoundException ex) {
                /*do nothing*/
            }
        } else {
            try (ObjectOutputStream out = new AppendingObjectOutputStream(new FileOutputStream(fileName, true))) {
                functionIO.apply(out);
//                out.flush();
            } catch (ClassNotFoundException ex) {
                /*do nothing*/
            }
        }
    }

    public void delete(Employe emp) throws IOException {
        List<Employe> list = getAllEmployers();
        list.remove(emp);
        file.delete();
//        initHash();
        saveAll(list);
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

//        if (list != null && hashSumEquals()) return list;

        return getObjectsFromFile((oin) -> {
            List<Employe> arList = new ArrayList<>();
            Employe em = (Employe) oin.readObject();
            while (em != null) {
                arList.add(em);
                try {
                    em = (Employe) oin.readObject();
                } catch (EOFException ex) {
                    em = null;
                }
            }

//            list = arList;
            return arList;
        });
    }

    public List<Employe> getByJob(String job) throws IOException {
        List<Employe> list;
//        if (this.list == null || !hashSumEquals()) {
        list = getByJobFromFile(job);
//        } else {
//            list = new ArrayList<>();
//            for (Employe em : list) {
//                if (job.equals(em.getJob())) {
//                    list.add(em);
//                }
//            }
//        }
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
        return getSalary(list);
    }

    private int getSalary(List<Employe> list) {
        int salary = 0;
        for (Employe employe : list) {
            salary += employe.getSalary();
        }
        return salary;
    }

    private Integer readSalaryAmount() throws IOException {
        Integer salary = getObjectsFromFile(oin -> {
            Object obj = oin.readObject();
            while (!(obj instanceof Integer)) {
                oin.readObject();
            }
            return (Integer) obj;
        });
        return salary;
    }

//    private boolean hashSumEquals() throws FileNotFoundException {
//        return this.hash.equals(hashFile.getHash());
//    }
//
//    private boolean checkAndUpdateHashSum() throws IOException {
//        initHash();
//        String newHash = getHashFile().getHash();
//        boolean changesWereMade = hash.equals(newHash);
//        if (!changesWereMade) {
//            hash = newHash;
//        }
//        return changesWereMade;
//    }
//
//    private void initHash() throws IOException {
//        try {
//            hashFile = new HashFile(fileName, ALGORITHM.MD5);
//        } catch (FileNotFoundException ex) {
//            save(new Employe(null, 0, 0, null));
//            hashFile = new HashFile(fileName, ALGORITHM.MD5);
//        }
//            hash = hashFile.getHash();
//    }
//
//    private HashFile getHashFile() {
//        return hashFile;
//    }
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
