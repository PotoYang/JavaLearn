package quartztest;

import org.quartz.JobExecutionContext;
import org.quartz.Trigger;
import org.quartz.TriggerListener;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @since 2018/11/6 16:51
 * Modified:
 * Description:
 */
public class MyTriggerListener implements TriggerListener {
    @Override
    public String getName() {
        return this.getClass().getName();
    }

    @Override
    public void triggerFired(Trigger trigger, JobExecutionContext context) {
        System.out.println("MyTriggerListener.triggerFired()");
    }

    @Override
    public boolean vetoJobExecution(Trigger trigger, JobExecutionContext context) {
        System.out.println("MyTriggerListener.vetoJobExecution()");
        return false;
    }

    @Override
    public void triggerMisfired(Trigger trigger) {
        System.out.println("MyTriggerListener.triggerMisfired()");
    }

    @Override
    public void triggerComplete(Trigger trigger, JobExecutionContext context, Trigger.CompletedExecutionInstruction triggerInstructionCode) {
        System.out.println("MyTriggerListener.triggerComplete()");
    }
}
