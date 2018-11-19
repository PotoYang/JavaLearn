package quartztest;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @since 2018/11/9 15:50
 * Modified:
 * Description:
 */
public class MyJobListener implements JobListener {
    @Override
    public String getName() {
        return null;
    }

    @Override
    public void jobToBeExecuted(JobExecutionContext context) {

    }

    @Override
    public void jobExecutionVetoed(JobExecutionContext context) {

    }

    @Override
    public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {

    }
}
