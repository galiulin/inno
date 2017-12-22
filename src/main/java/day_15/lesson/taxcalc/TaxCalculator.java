package day_15.lesson.taxcalc;

@FunctionalInterface
public interface TaxCalculator {
    double calcTax(int summ);

    default void sayHello() {
        System.out.println("Hi from hell");
    }
}
