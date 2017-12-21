package day_14;

import java.util.Scanner;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class MySolution {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Integer num = enterNumber();
        System.out.println(num);
    }

    private static Integer enterNumber() {

        Supplier<String> supplierString = scanner::nextLine;

        Integer num = 0;
        String s = supplierString.get();
        Predicate<String> testLine = line -> line.matches("[0-9]+");

        Function<String, Integer> toInteger = Integer::parseInt;
        num = toInteger.apply(s);


        toInteger.apply(s);

        check(scanner::nextLine, s1 -> "y".equals(s1), s1 -> "n".equals(s1));

        return (testLine.test(s)) ? toInteger.apply(s) : 0;


//        return num;
    }


    private static Integer check(Supplier<String> getLine, Predicate<String> testLineYes, Predicate<String> testLineNo) {
        System.out.println("Error! Proceed? " +
                "(y-try again, " +
                "n- exit, " +
                "p- continue with 0)");
        String answer = getLine.get();
//        return testLineYes.test(answer) ? getLine.get() : 0;
        return 0;
    }

    private int getNumber() {
        return sum(getLine(), getLine());
    }

    private int getLine() {
        String s = scanner.nextLine();
        return  (checkNumber(s)) ? stringToInt(s) : sout((line)-> "y".equals(line), (line)->"n".equals(line));
    }

    private int stringToInt(String s) {
        return Integer.parseInt(s);
    }

    private boolean checkNumber(String line) {
        return line.matches("[0-9]+");
    }

    private int sout(Predicate<String> askYes, Predicate<String> askNo) {
        System.out.println("Error! Proceed? " +
                "(y -try again, " +
                "n- exit, " +
                "p- continue with 0)");
        String s = scanner.nextLine();

        if (askYes.test(s)) return getLine();
        if (askNo.test(s)) System.exit(0);
        return 0;

    }

    private int sum(int i, int b) {
        return i + b;
    }
}
