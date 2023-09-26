package consumers;

import java.util.List;
import java.util.concurrent.BlockingQueue;

public class MainConsumer extends Thread {

    BlockingQueue<Integer> mainQueue;
    Integer quantityProducedPerSecond;

    public MainConsumer(Integer quantityProducedPerSecond, BlockingQueue<Integer> mainQueue) {
        this.mainQueue = mainQueue;
        this.quantityProducedPerSecond = quantityProducedPerSecond;

        start();
    }

    @Override
    public void run() {

        // try {
        // Thread.sleep((1000 / quantityProducedPerSecond) / 2);
        // } catch (InterruptedException e) {
        // // TODO Auto-generated catch block
        // e.printStackTrace();
        // }

        while (true) {
            System.out.println("Primeiro consumidor iniciado");
            System.out.println("Proximo na fila: " + mainQueue.poll());
        }

    }
}
