package day_05;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeManager {

    private final File file = new File("Employers.dat");

    public void save(Employe emp) throws IOException {
        save((out) -> {
            out.writeObject(emp);
            return true;
        });
    }

    private void saveAll(List<Employe> list) throws IOException {
        save(out -> {
            for (int i = 0; i < list.size(); i++) {
                out.writeObject(list.get(i));
            }
            return true;
        });
    }

    private <T> void save(FunctionIO<ObjectOutputStream, T> functionIO) throws IOException {
        if (!file.exists()) {
            try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file, true))) {
                functionIO.apply(out);
                out.flush();
            } catch (ClassNotFoundException ex) {
                /*do nothing*/
            }
        } else {
            try (ObjectOutputStream out = new AppendingObjectOutputStream(new FileOutputStream(file, true))) {
                functionIO.apply(out);
                out.flush();
            } catch (ClassNotFoundException ex) {
                /*do nothing*/
            }
        }
    }

    public void delete(Employe emp) throws IOException {
        List<Employe> list = getAllEmployers();
        list.remove(emp);
        file.delete();
        saveAll(list);
    }

    public Employe getByName(String name) throws IOException {
        Employe readEl = getObjectsFromFile((objInStr) -> {
            Employe empl = null;
            do {
                try{
                empl = (Employe) objInStr.readObject();
                if (empl != null && name.equals(empl.getName())) {
                    return empl;
                }} catch (EOFException ex){
                    empl = null;
                }
            } while (empl != null);
            return empl;
        });
        if (readEl == null) throw new NotFoundEmploye();

        return readEl;
    }

    private <O> O getObjectsFromFile(FunctionIO<ObjectInputStream, O> function) throws IOException {
        Employe em = null;
        try (ObjectInputStream oin = new ObjectInputStream(new FileInputStream(file))) {
            return function.apply(oin);
        } catch (ClassNotFoundException ex) {
            /*do nothing*/
        }
        return (O) em;
    }


    public List<Employe> getAllEmployers() throws IOException {
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
            return arList;
        });
    }

    public List<Employe> getByJob(String job) throws IOException {
        return getObjectsFromFile((oin) -> {
            List<Employe> arList = new ArrayList<>();
            Employe em = (Employe) oin.readObject();
            while (em != null) {
                if (job != null && job.equals(em.getJob())) {
                    arList.add(em);
                }
                try {
                    em = (Employe) oin.readObject();
                } catch (EOFException ex) {
                    em = null;
                }
            }
            return arList;
        });
    }
}

class NotFoundEmploye extends RuntimeException {

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
