package day_16.monkeys;

public abstract class Monkey {
    public String name;
    public Integer age;

    protected String country;

    private String subType;

    private final int numLegs = 2;

    public String getName(){
        return name;
    }

    protected void setCountry(String country){
        this.country = country;
    }

    private void saySome(){
        System.out.println("YYAAAA!");
    }
}
