package test;

import sun.misc.Launcher;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @since 2018/11/19 08:59
 * Modified:
 * Description:
 */
public class MyClassLoader extends URLClassLoader {
    public MyClassLoader(URL[] urls) {
        super(urls);
    }

    public static void main(String[] args) {
        URL[] urls = Launcher.getBootstrapClassPath().getURLs();
        Arrays.stream(urls).forEach(System.out::println);
    }
}
