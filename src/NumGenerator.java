import java.util.stream.IntStream;

/***
 *
 * Data source
 *
 */
public class NumGenerator implements Runnable {
    private MessageQueuePipe outputQueue;

    public NumGenerator(MessageQueuePipe queue) {
        outputQueue = queue;
    }

    @Override
    public void run() {

        IntStream.range(1, 101)
                .filter(this::isEven)
                .forEach(outputQueue::put);
        // send -1, which means last number
        outputQueue.put(-1);

        System.out.println("NumGenerator is done");
    }

    private boolean isEven(int number) {
        return number % 2 == 0;
    }
}
