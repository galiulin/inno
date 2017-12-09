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

    public HashFile(String fileName, ALGORITHM algorithm){
        this.fileName = fileName;
        this.algorithm = algorithm;
        this.file = new File(fileName);
    }

    public String getHash(){
        if (file.exists()){
            return getHashValue();
        }
        return "file does not exist";
    }

    private String getHashValue() {
        StringBuilder sb = null;
        try {
            // Получаем контрольную сумму для файла в виде массива байт
            final MessageDigest md = MessageDigest.getInstance(algorithm.getType());
            final FileInputStream fis = new FileInputStream(fileName);
            byte[] dataBytes = new byte[1024];
            int bytesRead;
            while((bytesRead = fis.read(dataBytes)) > 0) {
                md.update(dataBytes, 0, bytesRead);
            }
            byte[] mdBytes = md.digest();

            // Переводим контрольную сумму в виде массива байт в
            // шестнадцатеричное представление
            sb = new StringBuilder();
            for(int i = 0; i < mdBytes.length; i++) {
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

    enum ALGORITHM {
        SHA_1("SHA-1"),
        SHA_256("SHA-256"),
        MD5("MD5");

        private String str;
        ALGORITHM(String alg){
            this.str = alg;
        }

        public String getType() {
            return str;
        }

        @Override
        public String toString() {
            return str;
        }
    }

    public static void main(String[] args) {
        System.out.println(new HashFile("file.txt", ALGORITHM.MD5).getHash());
    }
}
