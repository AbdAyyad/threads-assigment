/**
 * Data sink
 */
public class DataPrinter implements Runnable {
    private MessageQueuePipe pipe;
    private int threadNumber;
    public DataPrinter(MessageQueuePipe pipe,int threadNumber) {
        this.pipe = pipe;
        this.threadNumber= threadNumber;
    }

    @Override
    public void run() {
        int number;

        while ((number = pipe.get()) != -1) {
            System.out.printf("writer #%d print number %d\n", threadNumber, number);
        }
    }
}
