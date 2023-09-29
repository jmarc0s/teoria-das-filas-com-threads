import java.util.concurrent.BlockingQueue;

import consumers.MainConsumer;

public class QueueMonitor extends Thread {

    BlockingQueue<Integer> mainQueue;
    BlockingQueue<Integer> secondQueue;
    BlockingQueue<Integer> thirdQueue;
    BlockingQueue<Integer> fourthQueue;
    BlockingQueue<Integer> fifthQueue;
    Integer quantityProducedPerSecond;
    MainConsumer mainConsumer;

    public QueueMonitor(Integer quantityProducedPerSecond, BlockingQueue<Integer> mainQueue,
            BlockingQueue<Integer> secondQueue,
            BlockingQueue<Integer> thirdQueue,
            BlockingQueue<Integer> fourthQueue, BlockingQueue<Integer> fifthQueue, Thread mainConsumer) {

        this.mainConsumer = (MainConsumer) mainConsumer;
        this.quantityProducedPerSecond = quantityProducedPerSecond;
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

            System.out.println("\n\n\n\n------------------------------------------\n\n");
            System.out.println("Tamanho da fila principal: " + mainQueue.size() + " | Taxa de chegada (por segundo): "
                    + quantityProducedPerSecond + " | Taxa vazão (por segundo): "
                    + this.getMainQueueThroughputInformation());

            System.out.println("Tamanho da segunda fila: " + secondQueue.size() + " | Taxa de chegada (por segundo): "
                    + this.getSecondArrivalRateInformation());

            System.out.println("Tamanho da terceira fila: " + thirdQueue.size() + " | Taxa de chegada (por segundo): "
                    + getThirdQueueArrivalRateInformation());

            System.out.println("Tamanho da quarta fila: " + fourthQueue.size() + " | Taxa de chegada (por segundo): "
                    + this.getFourthQueueArrivalRateInformation());
            System.out.println("Tamanho da quinta fila: " + fifthQueue.size() + " | Taxa de chegada (por segundo): "
                    + getFifthQueueArrivalRateInformation());
        }

    }

    private int getMainQueueThroughputInformation() {
        int throughput = mainConsumer.getMainQueueThroughput();
        mainConsumer.resetMainQueueThroughput();
        return throughput;
    }

    private int getSecondArrivalRateInformation() {
        int arrivaleRate = mainConsumer.getSecondQueueArrivalRate();
        mainConsumer.resetSecondQueueArrivalRate();
        return arrivaleRate;
    }

    private int getThirdQueueArrivalRateInformation() {
        int arrivaleRate = mainConsumer.getThirdQueueArrivalRate();
        mainConsumer.resetThirdQueueArrivalRate();
        return arrivaleRate;
    }

    private int getFourthQueueArrivalRateInformation() {
        int arrivaleRate = mainConsumer.getFourthQueueArrivalRate();
        mainConsumer.resetFourthQueueArrivalRate();
        return arrivaleRate;
    }

    private int getFifthQueueArrivalRateInformation() {
        int arrivaleRate = mainConsumer.getFifthQueueArrivalRate();
        mainConsumer.resetFifthQueueArrivalRate();
        return arrivaleRate;
    }

}
