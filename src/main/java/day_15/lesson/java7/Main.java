package day_15.lesson.java7;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please, enter your salary");
        Integer salary = scanner.nextInt();

        scanner.nextLine();

        System.out.println("Please, enter your country");
        String country = scanner.nextLine();

        TaxCalcNonFunctional taxCalculator = null;

        switch (country) {
            case "Russia":
                taxCalculator = new TaxCalcNonFunctional() {

                    @Override
                    public double calcTax(int summ) {
                        return summ * 0.13;
                    }

                    @Override
                    public void sayWord() {

                    }
                };
                break;
            case "USA":
                taxCalculator = new TaxCalcNonFunctional() {

                    @Override
                    public double calcTax(int summ) {
                        return summ * 0.13;
                    }

                    @Override
                    public void sayWord() {

                    }
                };
                break;
            case "Angola":
                taxCalculator = new TaxCalcNonFunctional() {

                    @Override
                    public double calcTax(int summ) {
                        return summ * 0.13;
                    }

                    @Override
                    public void sayWord() {

                    }
                };
                break;
            default:
                taxCalculator = new TaxCalcNonFunctional() {

                    @Override
                    public double calcTax(int summ) {
                        return summ * 0.13;
                    }

                    @Override
                    public void sayWord() {

                    }
                };
                break;
        }

        System.out.println("Your taxes is:");
        System.out.println(taxCalculator.calcTax(salary));
    }
}
