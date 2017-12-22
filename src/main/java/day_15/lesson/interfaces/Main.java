package day_15.lesson.interfaces;


import java.util.Comparator;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Main {
    public static void main(String[] args) {
        Consumer<String> greedy = (input) -> {
            System.out.println("I see input " + input);
        };

        greedy.accept("Hello");

        Predicate<Integer> predicateMoreThanTen = (num) -> {
            return num > 10;
        };
        Predicate<Integer> predicateLessThanZero = (num) -> {
            return num < 0;
        };
        Predicate<Integer> predicateOdd = (num) -> {
            return num % 2 == 0;
        };

        Boolean result = predicateLessThanZero
                .or(predicateMoreThanTen
                        .and(predicateOdd)).test(-10);
        System.out.println(result);

        Function<Integer, String> intToString =
                (arg) -> arg.toString();
        Function<String, Integer> stringToInt =
                (arg) -> Integer.parseInt(arg);
        System.out.println(
                intToString.compose(
                        stringToInt.compose(intToString))
                        .apply(89));

        Supplier<Something> somethingSupplier =
                Something::new;
        somethingSupplier.get();

        Supplier<Something> somethingSupplierFact =
                SomethingFactory::createSome;
        somethingSupplier.get();

        Supplier<Something> somethingSupplierCustom =
                () -> new Something();
        somethingSupplier.get();

        Comparator<Integer> comparator =
                (o1, o2) -> o1 - o2;
        System.out.println(comparator.compare(1, 2));

        Optional<String> optionalFull = Optional.of("1223");
        Optional<String> optionalEmpty = Optional.ofNullable(null);

        System.out.println(optionalFull.isPresent());
        System.out.println(optionalEmpty.isPresent());

        //System.out.println(optionalEmpty.get());
        System.out.println(optionalEmpty.orElse("default"));
        optionalFull.ifPresent(
                (s) -> System.out.println(
                        stringToInt.andThen(intToString).apply(s))
        );
        System.out.println("Empty start");
        optionalEmpty.ifPresent(
                (s) -> System.out.println(
                        stringToInt.andThen(intToString).apply(s))
        );
    }
}
