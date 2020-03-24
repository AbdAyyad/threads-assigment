import java.util.Optional;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class MessageQueuePipe {
    private BlockingQueue<Integer> queue;

    public MessageQueuePipe() {
        /*
         * according to oracle document https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/ArrayBlockingQueue.html
         * once you set the capacity it won't be changed
         * so when you add an element and this size equal capacity the thread will w8
         * */
        queue = new ArrayBlockingQueue<>(3);
    }

    public int get() {
        int value = -1;
        try {
            value = Optional.ofNullable(queue.poll(5, TimeUnit.SECONDS)).orElse(-1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return value;
    }

    public void put(int val) {
        try {
            queue.put(val);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
