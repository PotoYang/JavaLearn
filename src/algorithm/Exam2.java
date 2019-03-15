package algorithm;

import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @since 2019/3/15 10:27
 * Modified:
 * Description:
 */
public class Exam2 {
    private static final int STUDENT_SIZE = 20;

    public static void main(String[] args) {
        Random random = new Random();
        DelayQueue<Student2> student2s = new DelayQueue<>();
        ExecutorService executor = Executors.newCachedThreadPool();
        long shouldEndTime = 0;
        for (int i = 0; i < STUDENT_SIZE; i++) {
            long submitTime = 3000 + random.nextInt(12000);
            shouldEndTime = shouldEndTime > submitTime ? shouldEndTime : submitTime;
            shouldEndTime = shouldEndTime > 12000 ? 12000 : shouldEndTime;
            student2s.put(new Student2("student" + (i + 1), submitTime));
        }
        student2s.put(new Student2.EndExam2(shouldEndTime, executor));
        executor.execute(new Teacher2(student2s, executor));
    }
}

class Student2 implements Runnable, Delayed {
    private String name;
    // 考试时间
    private long workTime;
    // 交卷时间
    private long submitTime;

    public Student2(String name, long submitTime) {
        super();
        this.name = name;
        workTime = submitTime;
        this.submitTime = TimeUnit.NANOSECONDS.convert(submitTime, TimeUnit.MILLISECONDS) + System.nanoTime();
    }

    @Override
    public int compareTo(Delayed o) {
        if (!(o instanceof Student2)) {
            return 1;
        }
        if (o == this) {
            return 0;
        }
        return Long.compare(this.submitTime, ((Student2) o).submitTime);
    }

    @Override
    public void run() {
        System.out.println(name + " 交卷，用时 " + workTime / 100 + " 分钟");
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(submitTime - System.nanoTime(), TimeUnit.NANOSECONDS);
    }

    public static class EndExam2 extends Student2 {
        private ExecutorService executor;

        public EndExam2(long submitTime, ExecutorService executor) {
            super(null, submitTime);
            this.executor = executor;
        }

        @Override
        public void run() {
            List<Runnable> forceShutDown = executor.shutdownNow();
            forceShutDown.forEach(runnable -> {
                Thread thread = new Thread(runnable);
                System.out.println(thread.getName());
            });
        }
    }
}

class Teacher2 implements Runnable {
    private DelayQueue<Student2> student2s;
    private ExecutorService executor;

    public Teacher2(DelayQueue<Student2> student2s, ExecutorService executor) {
        super();
        this.student2s = student2s;
        this.executor = executor;
    }

    @Override
    public void run() {
        try {
            System.out.println("考试开始...");
            long start = System.currentTimeMillis();
            while (!Thread.interrupted()) {
                student2s.take().run();
            }
            long end = System.currentTimeMillis();
            System.out.println("考试结束！");
            System.out.println("本场考试用时：" + (end - start));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
