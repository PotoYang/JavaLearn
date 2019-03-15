package algorithm;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @since 2019/3/15 11:39
 * Modified:
 * Description:
 */
public class Exam3 {

    public static void main(String[] args) throws InterruptedException {
        Random random = new Random();
        DelayQueue<Student3> queue = new DelayQueue<>();
        int STUDENT_SIZE = 20;
        for (int i = 0; i < STUDENT_SIZE; i++) {
            queue.put(new Student3("S" + (i + 1), 30000 + random.nextInt(120000)));
        }
        queue.put(new Student3("submit", 120000));
        System.out.println("考试开始，总时间 120 秒，开考后 30 秒，才可提前交卷。");
        System.out.println("开始时间：" + DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now()));
        while (true) {
            Student3 s = queue.take();
            if (s.getName().equals("submit")) {
                System.out.println("考试结束，全体起立，离开考场。");
                queue.parallelStream()
                        .filter(v -> v.getSubmitTime() >= s.getSubmitTime())
                        .map(Student3::submit)
                        .forEach(System.out::println);
                System.out.println("结束时间：" + DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now()));
                System.exit(0);
            }
            System.out.println(s);
        }
    }
}

class Student3 implements Delayed {
    private String name;
    private long workTime;
    private long submitTime;

    Student3(String name, long submitTime) {
        this.name = name;
        this.workTime = submitTime;
        this.submitTime = TimeUnit.MILLISECONDS.convert(submitTime, TimeUnit.MILLISECONDS) + System.currentTimeMillis();
    }

    Student3 submit() {
        setWorkTime();
        setSubmitTime(System.currentTimeMillis());
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private void setWorkTime() {
        this.workTime = (long) 120000;
    }

    long getSubmitTime() {
        return submitTime;
    }

    private void setSubmitTime(long submitTime) {
        this.submitTime = TimeUnit.MILLISECONDS.convert(submitTime, TimeUnit.MILLISECONDS);
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(this.submitTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        if (!(o instanceof Student3)) {
            return 1;
        }
        if (o == this) {
            return 0;
        }
        return Long.compare(this.submitTime, ((Student3) o).submitTime);
    }

    @Override
    public String toString() {
        return "学生姓名：" + this.name +
                "，考试用时：" + this.workTime / 1000 + " 秒" +
                "，交卷时间：" + DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                .format(LocalDateTime.ofEpochSecond(this.submitTime / 1000, 0, ZoneOffset.ofHours(8)));
    }
}