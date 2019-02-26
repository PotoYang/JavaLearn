package patterns;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @since 2018/11/28 18:05
 * Modified:
 * Description:
 */
public class ProxySubject implements Subject {
    private RealSubject realSubject = new RealSubject();

    @Override
    public int getMoney() {
        return realSubject.getMoney();
    }
}
