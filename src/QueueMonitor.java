import java.util.concurrent.BlockingQueue;

import consumers.Consumer;
import consumers.MainConsumer;

public class QueueMonitor extends Thread {

    private final BlockingQueue<Integer> mainQueue;
    private final BlockingQueue<Integer> secondQueue;
    private final BlockingQueue<Integer> thirdQueue;
    private final BlockingQueue<Integer> fourthQueue;
    private final BlockingQueue<Integer> fifthQueue;
    private final Integer quantityProducedPerSecond;
    private final MainConsumer mainConsumer;
    private final Consumer secondConsumer;
    private final Consumer thirdConsumer;
    private final Consumer fourthConsumer;
    private final Consumer fifthConsumer;

    public QueueMonitor(Integer quantityProducedPerSecond, BlockingQueue<Integer> mainQueue,
            BlockingQueue<Integer> secondQueue,
            BlockingQueue<Integer> thirdQueue,
            BlockingQueue<Integer> fourthQueue,
            BlockingQueue<Integer> fifthQueue,
            Thread mainConsumer,
            Thread secondConsumer,
            Thread thirdConsumer,
            Thread fourthConsumer,
            Thread fifthConsumer) {

        this.mainConsumer = (MainConsumer) mainConsumer;
        this.secondConsumer = (Consumer) secondConsumer;
        this.thirdConsumer = (Consumer) thirdConsumer;
        this.fourthConsumer = (Consumer) fourthConsumer;
        this.fifthConsumer = (Consumer) fifthConsumer;

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
                e.printStackTrace();
            }

            System.out.println(
                    "\n\n\n\n----------------------------------------------------------------------------------------------------\n");
            System.out.println("Tamanho da fila principal: " + mainQueue.size()
                    + " | Taxa de chegada (por segundo): "
                    + quantityProducedPerSecond + " | Taxa de vazão (por segundo): "
                    + this.getMainQueueThroughputInformation());

            System.out.println("Tamanho da segunda fila: " + secondQueue.size()
                    + " | Taxa de chegada (por segundo): "
                    + this.getSecondArrivalRateInformation() + " | Taxa de vazão (por segundo): "
                    + this.getSecondQueueThroughputInformation());

            System.out.println("Tamanho da terceira fila: "
                    + thirdQueue.size() + " | Taxa de chegada (por segundo): "
                    + this.getThirdQueueArrivalRateInformation() + " | Taxa de vazão (por segundo): "
                    + this.getThirdQueueThroughputInformation());

            System.out.println("Tamanho da quarta fila: "
                    + fourthQueue.size() + " | Taxa de chegada (por segundo): "
                    + this.getFourthQueueArrivalRateInformation() + " | Taxa de vazão (por segundo): "
                    + this.getFourthQueueThroughputInformation());

            System.out.println("Tamanho da quinta fila: " + fifthQueue.size()
                    + " | Taxa de chegada (por segundo): "
                    + this.getFifthQueueArrivalRateInformation() + " | Taxa de vazão (por segundo): "
                    + this.getFifthQueueThroughputInformation());
        }

    }

    private int getMainQueueThroughputInformation() {
        int throughput = this.mainConsumer.getMainQueueThroughput();
        this.mainConsumer.resetMainQueueThroughput();
        return throughput;
    }

    private int getSecondQueueThroughputInformation() {
        int throughput = this.secondConsumer.getQueueThroughput();
        this.secondConsumer.resetQueueThroughput();
        return throughput;
    }

    private int getThirdQueueThroughputInformation() {
        int throughput = this.thirdConsumer.getQueueThroughput();
        this.thirdConsumer.resetQueueThroughput();
        return throughput;
    }

    private int getFourthQueueThroughputInformation() {
        int throughput = this.fourthConsumer.getQueueThroughput();
        this.fourthConsumer.resetQueueThroughput();
        return throughput;
    }

    private int getFifthQueueThroughputInformation() {
        int throughput = this.fifthConsumer.getQueueThroughput();
        this.fifthConsumer.resetQueueThroughput();
        return throughput;
    }

    private int getSecondArrivalRateInformation() {
        int arrivalRate = mainConsumer.getSecondQueueArrivalRate();
        mainConsumer.resetSecondQueueArrivalRate();
        return arrivalRate;
    }

    private int getThirdQueueArrivalRateInformation() {
        int arrivalRate = mainConsumer.getThirdQueueArrivalRate();
        mainConsumer.resetThirdQueueArrivalRate();
        return arrivalRate;
    }

    private int getFourthQueueArrivalRateInformation() {
        int arrivalRate = mainConsumer.getFourthQueueArrivalRate();
        mainConsumer.resetFourthQueueArrivalRate();
        return arrivalRate;
    }

    private int getFifthQueueArrivalRateInformation() {
        int arrivalRate = mainConsumer.getFifthQueueArrivalRate();
        mainConsumer.resetFifthQueueArrivalRate();
        return arrivalRate;
    }

}
