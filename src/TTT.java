import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by 71579 on 2018/7/4.
 */
public class TTT {

    public static void main(String[] args) {
        D a = new D();
        System.out.println(Double.valueOf(a.getD()) == null ? 0 : 1);
    }

    @Test
    public void mapTest() {
        List<Double> cost = Arrays.asList(10.0, 20.0, 30.0);
        (cost.stream().map(x -> x + x * 0.05)).forEach(x -> System.out.println(x));
    }

    static class D {

        public D() {
        }

        public double getD() {
            return Double.parseDouble(null);
        }
    }

    @Test
    public void dateTest() throws ParseException {
        String strDate = "2018-05-28 19:28:00";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(simpleDateFormat.parse(strDate));
    }

    @Test
    public void testCollection() {
        List<String> stringList = new ArrayList<String>() {
            {
                add("111");
                add("222");
                add("333");
                add("444");
            }
        };

        stringList.remove(0);
        stringList.remove(0);
        stringList.add("111");
        System.out.println(stringList.get(2));
    }


    List<AA> aaList = new ArrayList<AA>() {{
        add(new AA());
    }};

    public void aa() {

        List<Integer> datas = new ArrayList<>();
        for (AA aa : aaList) {
            if (aa.getParentId().equals(6)) {
                datas.add(aa.getId());
            }
        }

        getData(datas);
    }

    public void getData(List<Integer> datas) {
        if (datas.size() == 0) {
            return;
        }

        for (Integer data : datas) {
            System.out.println(data);
            List<Integer> datas2 = new ArrayList<>();
            for (AA aa : aaList) {
                if (aa.getParentId().equals(data)) {
                    datas2.add(aa.getId());
                }
            }
            getData(datas2);
        }
    }

    class AA {
        private Integer id;
        private Integer parentId;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getParentId() {
            return parentId;
        }

        public void setParentId(Integer parentId) {
            this.parentId = parentId;
        }
    }
}
