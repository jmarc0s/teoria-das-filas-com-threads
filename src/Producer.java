import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class Producer extends Thread {

    BlockingQueue<Integer> mainQueue;
    Integer quantityProducedPerSecond;

    public Producer(Integer quantityProducedPerSecond, BlockingQueue<Integer> mainQueue) {
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

            System.out.println("Produtor produziu o numero: " + newNumber);

            synchronized (mainQueue) {
                mainQueue.add(newNumber);
            }

        }

        // System.out.println("Produtor consumidor iniciado");
    }
}
