package quartztest;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.List;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @since 2018/11/6 14:36
 * Modified:
 * Description:
 */
public class HelloJob implements Job {
    private String tel;
    private List<String> dataList;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("Hello Job Execute");
        System.out.println(tel);
        dataList.forEach(System.out::println);
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setDataList(List<String> dataList) {
        this.dataList = dataList;
    }
}
