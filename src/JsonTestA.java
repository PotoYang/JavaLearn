/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @since 2018/11/23 09:58
 * Modified:
 * Description:
 */

import java.io.Serializable;
import java.util.List;

public class JsonTestA implements Serializable {
    private String name;
    private List<Object> jobs;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Object> getJobs() {
        return jobs;
    }

    public void setJobs(List<Object> jobs) {
        this.jobs = jobs;
    }
}
