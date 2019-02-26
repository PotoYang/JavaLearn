package java8;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @since 2019/2/26 14:32
 * Modified:
 * Description:
 */
public class Dish {
    private String name;
    private boolean vegetarian;
    private int calories;
    private Type type;

    public Dish(String name, boolean vegetarian, int calories, Type type) {
        this.name = name;
        this.vegetarian = vegetarian;
        this.calories = calories;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public int getCalories() {
        return calories;
    }

    public Type getType() {
        return type;
    }

    @Override
    public String toString() {
        return name;
    }

    enum Type {MEAT, FISH, OTHER}
}
