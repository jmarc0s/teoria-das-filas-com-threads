import java.util.concurrent.BlockingQueue;;

public class MainConsumer extends Thread {

    BlockingQueue<Integer> mainQueue;
    BlockingQueue<Integer> secondQueue;
    BlockingQueue<Integer> thirdQueue;
    BlockingQueue<Integer> fourthQueue;
    BlockingQueue<Integer> fifthQueue;
    private int mainQueueThroughput = 0;
    private int secondQueueArrivalRate = 0;
    private int thirdQueueArrivalRate = 0;
    private int fourthQueueArrivalRate = 0;
    private int fifthQueueArrivalRate = 0;

    Integer quantityProducedPerSecond;

    Integer timeToSleep;

    public MainConsumer(Integer quantityProducedPerSecond,
                        BlockingQueue<Integer> mainQueue,
                        BlockingQueue<Integer> secondQueue,
                        BlockingQueue<Integer> thirdQueue,
                        BlockingQueue<Integer> fourthQueue,
                        BlockingQueue<Integer> fifthQueue
    ) {
        this.mainQueue = mainQueue;
        this.secondQueue = secondQueue;
        this.thirdQueue = thirdQueue;
        this.fourthQueue = fourthQueue;
        this.fifthQueue = fifthQueue;
        this.timeToSleep = (1000 / quantityProducedPerSecond);
        this.quantityProducedPerSecond = quantityProducedPerSecond;

        this.setName(" Main Consumer");
        start();
    }

    @Override
    public void run() {
        while (true) {

            try {
                if (mainQueue.size() >= 5) {
                    timeToSleep = (1000 / quantityProducedPerSecond) / 2;
                    Thread.sleep(timeToSleep);
                } else {
                    timeToSleep = (1000 / quantityProducedPerSecond) * 2;
                    Thread.sleep(timeToSleep);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (!mainQueue.isEmpty()) {

                if (this.secondQueue.size() < 10) {
                    this.secondQueue.add(mainQueue.poll());

                    secondQueueArrivalRate++;
                    mainQueueThroughput++;
                } else if (this.thirdQueue.size() < 10) {
                    this.thirdQueue.add(mainQueue.poll());

                    thirdQueueArrivalRate++;
                    mainQueueThroughput++;
                } else if (this.fourthQueue.size() < 10) {
                    this.fourthQueue.add(mainQueue.poll());

                    fourthQueueArrivalRate++;
                    mainQueueThroughput++;
                } else if (this.fifthQueue.size() < 10) {
                    this.fifthQueue.add(mainQueue.poll());

                    fifthQueueArrivalRate++;
                    mainQueueThroughput++;
                }
            }
        }
    }

    public Integer getTimeToSleep() {
        return this.timeToSleep;
    }

    public int getMainQueueThroughput() {
        return this.mainQueueThroughput;
    }

    public void resetMainQueueThroughput() {
        this.mainQueueThroughput = 0;
    }

    public int getSecondQueueArrivalRate() {
        return this.secondQueueArrivalRate;
    }

    public void resetSecondQueueArrivalRate() {
        this.secondQueueArrivalRate = 0;
    }

    public int getThirdQueueArrivalRate() {
        return this.thirdQueueArrivalRate;
    }

    public void resetThirdQueueArrivalRate() {
        this.thirdQueueArrivalRate = 0;
    }

    public int getFourthQueueArrivalRate() {
        return this.fourthQueueArrivalRate;
    }

    public void resetFourthQueueArrivalRate() {
        this.fourthQueueArrivalRate = 0;
    }

    public int getFifthQueueArrivalRate() {
        return this.fifthQueueArrivalRate;
    }

    public void resetFifthQueueArrivalRate() {
        this.fifthQueueArrivalRate = 0;
    }
}
