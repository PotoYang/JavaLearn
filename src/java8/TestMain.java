package java8;

import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.IntSupplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @since 2019/2/25 17:56
 * Modified:
 * Description:
 */
public class TestMain {
    public static void main(String[] args) {
        List<Apple> appleList = new ArrayList<>();
        System.out.println(System.currentTimeMillis());
//        appleColorResult.forEach(System.out::println);
//        appleColorHeavyResult.forEach(System.out::println);
        System.out.println(System.currentTimeMillis());

        appleList.sort(Comparator
                .comparing(Apple::getColor).reversed()
                .thenComparing(Apple::getWeight)
        );
        appleList.forEach(System.out::println);
        System.out.println(System.currentTimeMillis());

        List<Dish> dishList = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH)
        );

//        List<String> menuName = dishList.stream()
//             .filter(d -> {
//                    System.out.println("filter : " + d.getName());
//                    return d.getCalories() > 400;
//                })
//                .map(d -> {
//                    System.out.println("map : " + d.getName());
//                    return d.getName();
//                })
//                .limit(3)
//                .collect(Collectors.toList());

        List<Dish> notVegeList = dishList.stream()
                .filter(Dish::isVegetarian)
                .limit(2)
                .collect(Collectors.toList());
        System.out.println(notVegeList);

        List<String> words = new ArrayList<>(Arrays.asList("hello", "world"));
        List<String> singleList = words.stream()
                .map(word -> word.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());
        singleList.forEach(System.out::println);

        List<Integer> number1 = Arrays.asList(1, 2, 3);
        List<Integer> number2 = Arrays.asList(3, 4);
        List<int[]> pairs = number1.stream().flatMap(i -> number2.stream().map(j -> new int[]{i, j})).collect(Collectors.toList());

        System.out.println(dishList.stream().findAny());

        System.out.println(dishList.stream()
                .map(Dish::getCalories)
                .reduce(0, Integer::sum));
//                .mapToInt(Dish::getCalories)
//                .sum();
        // IntStream    DoubleStream    LongStream
        // 使用 IntStream 原因是装箱造成的复杂性
        OptionalDouble averageCalories = dishList.stream().mapToInt(Dish::getCalories).average();
        averageCalories.ifPresent(System.out::println);
//        System.out.println();

        // 求和
        dishList.stream().collect(Collectors.summarizingInt(Dish::getCalories));
        // dishList.stream().collect(Collectors.averagingInt(Dish::getCalories));
        System.out.println(dishList.stream().map(Dish::getName).collect(Collectors.joining("|")));
    }

    @Test
    public void test1() {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");
        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        // (1) 找出2011年发生的所有交易，并按交易额排序（从低到高）。
        transactions.stream()
                .filter(t -> t.getYear() == 2011)
                .sorted(Comparator.comparingInt(Transaction::getValue))
                .forEach(System.out::println);
        // (2) 交易员都在哪些不同的城市工作过？
        transactions.stream()
                .map(Transaction::getTrader)
                .map(Trader::getCity)
                .distinct()
                .forEach(System.out::println);
//        transactions.stream()
//                .map(t -> t.getTrader().getCity())
//                .distinct()
//                .collect(Collectors.toList());
//        transactions.stream()
//                .map(t -> t.getTrader().getCity())
//                .collect(Collectors.toSet());
        // (3) 查找所有来自于剑桥的交易员，并按姓名排序。
        transactions.stream()
                .map(Transaction::getTrader)
                .filter(trader -> "Cambridge".equals(trader.getCity()))
                .distinct()
                .sorted(Comparator.comparing(Trader::getName))
                .forEach(System.out::println);
        // (4) 返回所有交易员的姓名字符串，按字母顺序排序。
        String traderStr = transactions.stream()
                .map(Transaction::getTrader)
                .map(Trader::getName)
                .distinct()
                .sorted()
                // 效率不高，字符串反复连接，每次迭代都要新建一个String对象
                // .reduce("", (s1, s2)->s1+s2);
                .collect(Collectors.joining());
        System.out.println(traderStr);
        // (5) 有没有交易员是在米兰工作的？
        boolean hasWorkInMilan = transactions.stream()
                .anyMatch(t -> t.getTrader().getCity().equals("Milan"));
        // .map(Transaction::getTrader)
        // .map(Trader::getCity)
        // .anyMatch("Milan"::equalsIgnoreCase);
        System.out.println(hasWorkInMilan);
        // (6) 打印生活在剑桥的交易员的所有交易额。
        transactions.stream()
                .filter(t -> "Cambridge".equals(t.getTrader().getCity()))
                .map(Transaction::getValue)
                .forEach(System.out::println);

        // (7) 所有交易中，最高的交易额是多少？
        transactions.stream()
                .map(Transaction::getValue)
                .max(Comparator.naturalOrder())
//                .reduce(Integer::max)
                .ifPresent(System.out::println);
        // (8) 找到交易额最小的交易。
        transactions.stream()
//                .min(Comparator.naturalOrder())
                .reduce((t1, t2) -> t1.getValue() < t2.getValue() ? t1 : t2)
                .ifPresent(System.out::println);

    }

    @Test
    public void testNumberStream() {
        IntStream intStream = IntStream.rangeClosed(1, 100); // Stream
        long count = intStream.filter(n -> n % 2 == 0).count();
        System.out.println(count);

        // filter(b -> Math.sqrt(a*a + b*b) % 1 ==0)
//        int a = 10;
//        IntStream.rangeClosed(1, 100)
//                .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
//                .mapToObj(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)});

        Stream<double[]> tt = IntStream.rangeClosed(1, 100).boxed()
                .flatMap(a ->
                        IntStream.rangeClosed(a, 100)
                                .mapToObj(b -> new double[]{a, b, Math.sqrt(a * a + b * b)})
                                .filter(t -> t[2] % 1 == 0)
                );

        tt.forEach(
                t -> System.out.println("(" + t[0] + "," + t[1] + "," + t[2] + ")")
        );

        Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1], t[0] + t[1]})
                .limit(20)
                .map(t -> t[0])
//                .forEach(t -> System.out.println("(" + t[0] + "," + t[1] + ")"));
                .forEach(System.out::println);
    }

    @Test
    public void testStringFormat() {
        String test = "123|456";
        test = test.replace("123", "");
        test = test.replace("123" + "|", "");
        test = test.replace("|" + "123", "");

        System.out.println(test);
        String orgin = "123/%s/%s/%s";
        String baseUrl = String.format(orgin, "456", "789", "101112.mp4");
        System.out.println(baseUrl);

        String type = "jpg";
        System.out.println(type.matches("(jpg|png|JPG|PNG)"));

        System.out.println(LocalDateTime.now());

        System.out.println(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now()));
        System.out.println(DateTimeFormatter.BASIC_ISO_DATE.format(LocalDateTime.now()));
        System.out.println(DateTimeFormatter.ISO_LOCAL_DATE.format(LocalDate.now()));
        System.out.println("320x400_1.jpg|240x300_1.jpg|160x200_1.jpg|500x280_1.jpg|375x210_1.jpg|246x138_1.jpg".replace("_1", ""));
        System.out.println("351x196.jpg|180x225.jpg|151x84.jpg|80x100.jpg|625x350.jpg|320x400.jpg|375x210_1.jpg|246x138_1.jpg|149x149.jpeg|240x300_1.jpg|160x200_1.jpg|151x151.jpeg|500x280_1.jpg|64x64.jpg|500x500.jpg|100x100.jpeg".replace("_1", ""));


        List<String> testStrList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) {
                testStrList.add("123");
            } else {
                testStrList.add(null);
            }
        }

        testStrList.removeIf(Objects::isNull);
        System.out.println(testStrList.size());
    }

    @Test
    public void testSupplier() {
        IntSupplier fib = new IntSupplier() {
            private int previous = 0;
            private int current = 1;

            @Override
            public int getAsInt() {
                int oldPrevious = this.previous;
                int nextValue = this.previous + this.current;
                this.previous = this.current;
                this.current = nextValue;
                return oldPrevious;
            }
        };

        IntStream.generate(fib).limit(10).forEach(System.out::println);
    }
}
