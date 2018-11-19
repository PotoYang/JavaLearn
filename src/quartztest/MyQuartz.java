package quartztest;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.triggers.SimpleTriggerImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.quartz.SimpleScheduleBuilder.simpleSchedule;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @since 2018/11/6 14:15
 * Modified:
 * Description:
 */
public class MyQuartz {
    public static void main(String[] args) {
        List<String> dataList = new ArrayList<>();
        dataList.add("123");
        dataList.add("456");
        dataList.add("789");
        dataList.add("10");
        JobDataMap map = new JobDataMap();
        map.put("dataList", dataList);
        map.put("tel", "12345678910");
        try {
            // Grab the Scheduler instance from Factory
            // Scheduler三个动作 启动(start) 暂停(stand by) 结束(shutdown)
            // shutdown之后需要重新实例化才能启动
            // 只有当scheduler启动后，即使处于暂停状态也不行，trigger才会被触发(job才会被执行)
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

//            Matcher<TriggerKey> matcher = GroupMatcher.groupEquals("triggerGroup");
//            scheduler.getListenerManager().addTriggerListener(new BroadcastTriggerListener(), matcher);

            // start it off
            scheduler.start();

            // define the job and tie it to our HelloJob class
            JobDetail job = JobBuilder.newJob(HelloJob.class)
                    .withIdentity("jobName", "jobGroup")
                    .usingJobData(map)
                    .build();

//            Date beginDate = DateBuilder.evenHourDate(new Date());

            // Trigger the job to run now, and then repeat every 40 seconds
            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity("triggerName", "triggerGroup")
                    .startNow()
                    .withSchedule(simpleSchedule()
                            .withIntervalInSeconds(40)
                            .withRepeatCount(0)
                            .repeatForever())
                    .build();

            Date endDate = new Date(System.currentTimeMillis() + 10000);

            SimpleTriggerImpl simpleTrigger = new SimpleTriggerImpl();
            simpleTrigger.setName("triggerName");
            simpleTrigger.setGroup("triggerGroup");
            simpleTrigger.setStartTime(new Date());
//            simpleTrigger.setRepeatInterval(4000);
            simpleTrigger.setRepeatCount(0);
            simpleTrigger.setEndTime(endDate);

            // Tell quartz to schedule the job using our trigger
            scheduler.scheduleJob(job, simpleTrigger);

//            Thread.sleep(5000);

            // shut it down
//            scheduler.shutdown();

        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
