package day_15.lesson.taxcalc;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please, enter your salary");
        Integer salary = scanner.nextInt();

        scanner.nextLine();

        System.out.println("Please, enter your country");
        String country = scanner.nextLine();

        TaxCalculator taxCalculator = null;

        switch (country) {
            case "Russia":
                taxCalculator =
                        (int summ) ->
                                summ * 0.13 + salary;
                break;
            case "USA":
                taxCalculator = (int summ) -> {
                    return summ * 0.25;
                };
                break;
            case "Angola":
                taxCalculator = (int summ) -> {
                    return summ * 0.90;
                };
                break;
            default:
                taxCalculator = (int summ) -> {
                    return 0;
                };
                break;
        }

        System.out.println("Your taxes is:");
        System.out.println(taxCalculator.calcTax(salary));
        //salary -= (int)taxCalculator.calcTax(salary);
    }
}
