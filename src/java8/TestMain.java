package java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
    }
}
