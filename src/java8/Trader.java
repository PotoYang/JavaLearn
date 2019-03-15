package java8;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @since 2019/2/27 11:46
 * Modified:
 * Description:
 */
public class Trader {
    private final String name;
    private final String city;

    public Trader(String n, String c) {
        this.name = n;
        this.city = c;
    }

    public String getName() {
        return this.name;
    }

    public String getCity() {
        return this.city;
    }

    public String toString() {
        return "Trader:" + this.name + " in " + this.city;
    }
}