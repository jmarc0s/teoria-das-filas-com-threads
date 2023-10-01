package consumers;

import java.util.concurrent.BlockingQueue;

public class Consumer extends Thread {

    private Thread previusConsumer;
    private BlockingQueue<Integer> queue;
    private Integer timeToSleep;
    private int queueThroughput = 0;

    public Consumer(String consumerName, BlockingQueue<Integer> queue, Thread previusConsumer) {
        this.queue = queue;
        this.previusConsumer = previusConsumer;
        this.timeToSleep = 0;
        this.setName(consumerName);

        start();
    }

    @Override
    public void run() {
        // System.out.println(this.getName() + " iniciado ");

        while (true) {
            try {

                // synchronized (previusConsumer) {
                try {

                    timeToSleep = ((Consumer) previusConsumer).getTimeToSleep() * 2;
                } catch (ClassCastException e) {
                    timeToSleep = ((MainConsumer) previusConsumer).getTimeToSleep() * 2;
                }
                // }

                Thread.sleep(timeToSleep);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            // synchronized (queue) {
            queue.poll();
            queueThroughput++;
            // System.out.println("numero retirado: " + queue.poll());
            // System.out.println(this.getName() + " | Tamanho da fila: " + queue.size());

            // }

        }
    }

    public Integer getTimeToSleep() {
        return this.timeToSleep;
    }

    public int getQueueThroughput() {
        return this.queueThroughput;
    }

    public void resetQueueThroughput() {
        this.queueThroughput = 0;
    }

}
