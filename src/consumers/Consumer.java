package consumers;

import java.util.concurrent.BlockingQueue;

public class Consumer extends Thread {

    Thread previusConsumer;
    BlockingQueue<Integer> queue;
    Integer timeToSleep;

    public Consumer(String consumerName, BlockingQueue<Integer> queue, Thread previusConsumer) {
        this.queue = queue;
        this.previusConsumer = previusConsumer;
        this.timeToSleep = 0;
        this.setName(consumerName);

        start();
    }

    @Override
    public void run() {

        while (true) {
            try {

                try {
                    timeToSleep = ((Consumer) previusConsumer).getTimeToSleep() * 2;
                } catch (ClassCastException e) {
                    timeToSleep = ((MainConsumer) previusConsumer).getTimeToSleep() * 2;
                }

                Thread.sleep(timeToSleep);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            queue.poll();
            System.out.println(this.getName() + " iniciado |  Tamanho da fila: " + queue.size());
        }
    }

    public Integer getTimeToSleep() {
        return this.timeToSleep;
    }

}
