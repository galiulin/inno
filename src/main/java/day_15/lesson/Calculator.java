package day_15.lesson;

import java.util.Scanner;
import java.util.function.BiFunction;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Calculator {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Integer num = enterNumber();
    }

    private static Integer enterNumber() {
        BiFunction<Supplier<String>,
                BiFunction<String, Predicate<String>, Integer>,
                Integer>
                startFunc = (scan, func) -> {
            return func.apply(scan.get(),
                    (s) -> s.matches("[0-9]+"));
        };

        return startFunc.apply(
                scanner::nextLine,
                (string, digitPredicate) -> {
                    if (digitPredicate.test(string)) {
                        return Integer.parseInt(string);
                    } else {
                        Supplier<Integer> supplier =
                                () -> {
                                    System.out.println("Error! Proceed? " +
                                            "(y-try again, " +
                                            "n- exit, " +
                                            "p- continue with 0)");
                                    final String dess = scanner.nextLine();

                                    BiFunction<Predicate<String>, Supplier<Integer>, Integer> function =
                                            (predicate, supplier1) -> {
                                                Integer res = 0;

                                                if (predicate.test(dess)) {
                                                    res = supplier1.get();
                                                }

                                                return res;
                                            };

                                    return function.apply((s) -> "y".equals(s), Calculator::enterNumber);
                                };
                        return supplier.get();
                    }
                });
    }
}
