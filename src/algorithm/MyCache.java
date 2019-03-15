package algorithm;

import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @since 2019/3/15 14:14
 * Modified:
 * Description:
 */
public class MyCache<K, V> {
    private ConcurrentHashMap<K, V> map = new ConcurrentHashMap<>();
    private DelayQueue<DelayedItem<K>> queue = new DelayQueue<>();

    private void put(K k, V v, long liveTime) {
        V v2 = map.put(k, v);
        DelayedItem<K> tmpItem = new DelayedItem<>(k, liveTime);
        if (v2 != null) {
            queue.remove(tmpItem);
        }
        queue.put(tmpItem);
    }

    private MyCache() {
        Thread t = new Thread(this::daemonCheckOverdueKey);
        t.setDaemon(true);
        t.start();
    }

    private void daemonCheckOverdueKey() {
        while (true) {
            DelayedItem<K> delayedItem = queue.poll();
            if (delayedItem != null) {
                map.remove(delayedItem.getT());
                System.out.println(System.currentTimeMillis() + " remove " + delayedItem.getT() + " from cache");
            }
            try {
                Thread.sleep(3000);
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Random random = new Random();
        int cacheNumber = 10;
        int liveTime;
        MyCache<String, Integer> cache = new MyCache<>();

        for (int i = 0; i < cacheNumber; i++) {
            liveTime = random.nextInt(3000);
            System.out.println(i + "  " + liveTime);
            cache.put(i + "", i, random.nextInt(liveTime));
            if (random.nextInt(cacheNumber) > 7) {
                liveTime = random.nextInt(3000);
                System.out.println(i + "  " + liveTime);
                cache.put(i + "", i, random.nextInt(liveTime));
            }
        }

        Thread.sleep(3000);
        System.out.println();
    }

}

class DelayedItem<T> implements Delayed {
    private T t;
    private long liveTime;
    private long removeTime;

    DelayedItem(T t, long liveTime) {
        this.setT(t);
        this.liveTime = liveTime;
        this.removeTime = TimeUnit.MILLISECONDS.convert(liveTime, TimeUnit.MILLISECONDS) + System.currentTimeMillis();
    }

    @Override
    public int compareTo(Delayed o) {
        if (o == null) return 1;
        if (o == this) return 0;
        if (o instanceof DelayedItem) {
            DelayedItem<T> tmpDelayedItem = (DelayedItem<T>) o;
            return Long.compare(liveTime, tmpDelayedItem.liveTime);
        }
        long diff = getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS);
        return diff > 0 ? 1 : diff == 0 ? 0 : -1;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(removeTime - System.currentTimeMillis(), unit);
    }

    T getT() {
        return t;
    }

    private void setT(T t) {
        this.t = t;
    }

    @Override
    public int hashCode() {
        return t.hashCode();
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof DelayedItem) {
            return object.hashCode() == hashCode();
        }
        return false;
    }
}
