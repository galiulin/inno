package Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashFile {
    private final String fileName;
    private final ALGORITHM algorithm;
    private final File file;
    private String hash;

    public HashFile(String fileName, ALGORITHM algorithm) throws FileNotFoundException {
        this(new File(fileName), algorithm);
    }

    public HashFile(File file, ALGORITHM algorithm) throws FileNotFoundException {
        this.file = file;
        this.algorithm = algorithm;
        this.fileName = file.getPath();
        this.hash = getHash();
    }

    public String getHash() throws FileNotFoundException {
        if (file.exists()) {
            String hash = getHashValue();
            if (this.hash == null) {
                this.hash = hash;
            }
            return hash;
        }
        throw new FileNotFoundException();
    }

   /* private boolean checkChanges() throws FileNotFoundException {
        return this.hash.equals(getHash());
    }

    private boolean checkAndUpdateHash() throws FileNotFoundException {
        String newHash = getHash();
        boolean changesWereMade = this.hash.equals(newHash);
        if (changesWereMade) {
            this.hash = newHash;
        }
        return changesWereMade;
    }*/


    private String getHashValue() {
        StringBuilder sb = null;
        try {
            // Получаем контрольную сумму для файла в виде массива байт
            final MessageDigest md = MessageDigest.getInstance(algorithm.getType());
            final FileInputStream fis = new FileInputStream(fileName);
            byte[] dataBytes = new byte[1024];
            int bytesRead;
            while ((bytesRead = fis.read(dataBytes)) > 0) {
                md.update(dataBytes, 0, bytesRead);
            }
            byte[] mdBytes = md.digest();

            // Переводим контрольную сумму в виде массива байт в
            // шестнадцатеричное представление
            sb = new StringBuilder();
            for (int i = 0; i < mdBytes.length; i++) {
                sb.append(Integer.toString((mdBytes[i] & 0xff) + 0x100, 16)
                        .substring(1));
            }
        } catch (FileNotFoundException | NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return sb.toString();
    }


    public static void main(String[] args) throws FileNotFoundException {
//        System.out.println(new HashFile("file.txt", ALGORITHM.MD5).getHash());
    }
}
