package day_16.mysimple;

public class SmallHouse implements House {
    private final int monthlyPayment = 10_000;

    @Override
    public void payAccomodation(int sum) {

        System.out.println("Оплачено за проживание " + sum + " осталось " + (monthlyPayment - sum));
    }

    @Override
    public int getHousingArea() {
        int value = 50;
        System.out.println("house area is " + value);
        return value;
    }
}
