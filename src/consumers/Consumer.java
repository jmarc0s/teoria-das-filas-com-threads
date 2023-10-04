package consumers;

import java.util.concurrent.BlockingQueue;

public class Consumer extends Thread {

    private final Thread previousConsumer;
    private final BlockingQueue<Integer> queue;
    private Integer timeToSleep;
    private int queueThroughput = 0;

    public Consumer(String consumerName, BlockingQueue<Integer> queue, Thread previousConsumer) {
        this.queue = queue;
        this.previousConsumer = previousConsumer;
        this.timeToSleep = 0;
        this.setName(consumerName);

        start();
    }

    @Override
    public void run() {
        while (true) {
            try {
                try {

                    timeToSleep = ((Consumer) previousConsumer).getTimeToSleep() * 2;
                } catch (ClassCastException e) {
                    timeToSleep = ((MainConsumer) previousConsumer).getTimeToSleep() * 2;
                }

                Thread.sleep(timeToSleep);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            queue.poll();
            queueThroughput++;
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
