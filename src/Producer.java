import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class Producer extends Thread {

    private final BlockingQueue<Integer> mainQueue;
    private final Integer quantityProducedPerSecond;

    public Producer(Integer quantityProducedPerSecond,
                    BlockingQueue<Integer> mainQueue) {
        this.mainQueue = mainQueue;
        this.quantityProducedPerSecond = quantityProducedPerSecond;

        this.setName("Producer");
        start();
    }

    @Override
    public void run() {

        while (true) {
            try {
                Thread.sleep(1000 / quantityProducedPerSecond);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Random random = new Random(System.currentTimeMillis());
            Integer newNumber = random.nextInt(11);

            mainQueue.add(newNumber);
        }
    }
}
