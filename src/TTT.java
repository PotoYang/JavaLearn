import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Created by 71579 on 2018/7/4.
 */
public class TTT {
    @Test
    public void mapTest() {
        List<Double> cost = Arrays.asList(10.0, 20.0, 30.0);
        (cost.stream().map(x -> x + x * 0.05)).forEach(x -> System.out.println(x));
    }
}
