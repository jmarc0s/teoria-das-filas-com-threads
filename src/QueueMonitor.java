import java.util.concurrent.BlockingQueue;

import consumers.MainConsumer;

public class QueueMonitor extends Thread {

    BlockingQueue<Integer> mainQueue;
    BlockingQueue<Integer> secondQueue;
    BlockingQueue<Integer> thirdQueue;
    BlockingQueue<Integer> fourthQueue;
    BlockingQueue<Integer> fifthQueue;

    public QueueMonitor(BlockingQueue<Integer> mainQueue, BlockingQueue<Integer> secondQueue,
            BlockingQueue<Integer> thirdQueue,
            BlockingQueue<Integer> fourthQueue, BlockingQueue<Integer> fifthQueue) {

        this.mainQueue = mainQueue;
        this.secondQueue = secondQueue;
        this.thirdQueue = thirdQueue;
        this.fourthQueue = fourthQueue;
        this.fifthQueue = fifthQueue;

        start();
    }

    @Override
    public void run() {

        while (true) {

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            System.out.println("------------------------------------------\n\n\n\n");
            System.out.println("Tamanho da fila principal: " + mainQueue.size());
            System.out.println("Tamanho da segunda fila: " + secondQueue.size());
            System.out.println("Tamanho da terceira fila: " + thirdQueue.size());
            System.out.println("Tamanho da quarta fila: " + fourthQueue.size());
            System.out.println("Tamanho da quinta fila: " + fifthQueue.size());
        }

    }

}
