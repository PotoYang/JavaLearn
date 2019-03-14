package algorithm;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @since 2019/3/14 23:23
 * Modified:
 * Description:
 */
public class Exam {
    public static void main(String[] args) throws InterruptedException {
        int studentNumber = 20;
        CountDownLatch countDownLatch = new CountDownLatch(studentNumber + 1);
        DelayQueue<Student> students = new DelayQueue<>();
        Random random = new Random();
        for (int i = 0; i < studentNumber; i++) {
            students.put(new Student((i + 1), 300 + random.nextInt(1200), countDownLatch));
        }
        Thread teacherThread = new Thread(new Teacher(students));
        students.put(new EndExam(students, 1200, countDownLatch, teacherThread));
        teacherThread.start();
        countDownLatch.await();
        System.out.println("考试时间到，全部交卷!");
    }
}

class Student implements Runnable, Delayed {
    private int number;
    private long workTime;
    private long submitTime;
    private boolean isForce = false;
    private CountDownLatch countDownLatch;

    public Student(int number, long workTime, CountDownLatch countDownLatch) {
        this.number = number;
        this.workTime = workTime;
        this.submitTime = TimeUnit.NANOSECONDS.convert(workTime, TimeUnit.NANOSECONDS) + System.nanoTime();
        this.countDownLatch = countDownLatch;
    }

    @Override
    public int compareTo(Delayed o) {
        if (!(o instanceof Student)) return 1;
        if (o == this) return 0;
        Student s = (Student) o;
        return Long.compare(this.workTime, s.workTime);
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(submitTime - System.nanoTime(), TimeUnit.NANOSECONDS);
    }

    @Override
    public void run() {
        if (isForce) {
            System.out.println(number + " 交卷，希望用时 " + workTime + " 分钟，实际用时 1200 分钟");
        } else {
            System.out.println(number + " 交卷，希望用时 " + workTime + " 分钟，实际用时 " + workTime + " 分钟");
        }
        countDownLatch.countDown();
    }

    public void setForce(boolean isForce) {
        this.isForce = isForce;
    }
}

class EndExam extends Student {
    private DelayQueue<Student> students;
    private CountDownLatch countDownLatch;
    private Thread teacherThread;

    public EndExam(DelayQueue<Student> students, long workTime, CountDownLatch countDownLatch, Thread teacherThread) {
        super(-1, workTime, countDownLatch);
        this.students = students;
        this.countDownLatch = countDownLatch;
        this.teacherThread = teacherThread;
    }

    @Override
    public void run() {
        teacherThread.interrupt();
        Student tmpStudent;
        for (Student student : students) {
            tmpStudent = student;
            tmpStudent.setForce(true);
            tmpStudent.run();
        }
        countDownLatch.countDown();
    }
}

class Teacher implements Runnable {
    private DelayQueue<Student> students;

    public Teacher(DelayQueue<Student> students) {
        this.students = students;
    }

    @Override
    public void run() {
        try {
            System.out.println("test start...");
            while (!Thread.interrupted()) {
                students.take().run();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}