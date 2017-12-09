package Utils;

public enum ALGORITHM {
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

