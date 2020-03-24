import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {

        //1. Structure system elements
        int numberOfWriters = 5;

        MessageQueuePipe pipe = new MessageQueuePipe();
        ExecutorService executorService = Executors.newCachedThreadPool();
        //2. Start all threads

        executorService.submit(new NumGenerator(pipe));

        IntStream.range(1, numberOfWriters + 1).forEach((threadNumber -> executorService.submit(new DataPrinter(pipe, threadNumber))));

        executorService.shutdown();
    }
}