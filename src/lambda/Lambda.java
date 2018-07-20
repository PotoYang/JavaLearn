package lambda;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * Create: 2018/7/6 14:45
 * Modified By:
 * Description:
 */
public class Lambda {
    public static void main(String[] args) {
        List<String> languages = Arrays.asList("Java", "Scala", "Python", "C", "C#");
        List<Integer> costBeforeTax = Arrays.asList(100, 200, 300, 400);

        for (int cost : costBeforeTax) {
            double price = cost + 0.12 * cost;
            System.out.println(price);
        }

        costBeforeTax.stream().mapToDouble((cost) -> cost + 0.12 * cost).forEach(
                (cost) -> System.out.println("afterTax:" + cost));

        IntSummaryStatistics statistics = costBeforeTax.stream().mapToInt((x) -> x).summaryStatistics();
        System.out.println(statistics.toString());
//        testFunction();

        final Predicate<String> startsWithLetterJ = name -> name.startsWith("J");
        final Predicate<String> startsWithLetterN = name -> name.startsWith("N");

        final Function<String, Predicate<String>> startsWithLetter = letter -> name -> name.startsWith(letter);

        final long countLanguagesStartWithJ = languages.stream().filter(startsWithLetter.apply("J")).count();
        System.out.println(countLanguagesStartWithJ);

        final long countLanguagesStartWithC = languages.stream().filter(startsWithLetter.apply("C")).count();
        System.out.println(countLanguagesStartWithC);
    }

    private void testThread() {
        new Thread(() -> System.out.println("In Java 8 !")).start();
    }

    private static void testList() {
        List<String> features = Arrays.asList("lambda.Lambda", "Default Method", "Stream API");
        for (String feature : features) {
            System.out.println(feature);
        }

        features.forEach(System.out::println);
    }

    private static void testFunction() {
        List<String> languages = Arrays.asList("Java", "Scala", "Python", "C", "C#");
//        filter(languages, (str) -> ((String) str).startsWith("J"));

//        filter(languages, (str) -> ((String) str).endsWith("a"));

//        filter(languages, (str) -> true);

//        filter(languages, (str) -> ((String) str).length() > 4);


        Predicate<String> startsWithJ = (n) -> n.startsWith("J");
        Predicate<String> fourLetterLong = (n) -> n.length() == 4;

        languages.stream().filter(startsWithJ.and(fourLetterLong)).forEach(System.out::println);

        List<String> up = languages.stream().map(String::toUpperCase).collect(Collectors.toList());

        up.forEach(System.out::println);
    }

    private static void filter(List languages, Predicate condition) {
        languages.stream().filter((name) -> condition.test(name)).forEach(System.out::println);
    }

    private static void pickName(final List<String> names, final String startingLetter) {
        final Optional<String> foundName = names.stream().filter(name -> name.startsWith(startingLetter)).findFirst();
        System.out.println(String.format("A name starting with %s: %s", startingLetter, foundName.orElse("No name found")));

        String namesStr = String.join(",", names);


    }
}
