package consumers;

import java.util.ArrayList;
import java.util.List;
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

    public MainConsumer(Integer quantityProducedPerSecond, BlockingQueue<Integer> mainQueue,
            BlockingQueue<Integer> secondQueue, BlockingQueue<Integer> thirdQueue, BlockingQueue<Integer> fourthQueue,
            BlockingQueue<Integer> fifthQueue) {
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
        // System.out.println("Consumidor principal iniciado");

        // cria uma lista de filas
        List<BlockingQueue<Integer>> queueList = List.of(this.secondQueue, this.thirdQueue, this.fourthQueue,
                this.fifthQueue);

        List<Integer> bufferList = new ArrayList<>();
        while (true) {

            // SSystem.out.println("index: " + index);
            try {

                // synchronized (mainQueue) {

                if (mainQueue.size() >= 5) {
                    timeToSleep = (1000 / quantityProducedPerSecond) / 2;
                    Thread.sleep(timeToSleep);
                } else {
                    timeToSleep = (1000 / quantityProducedPerSecond) * 2;
                    Thread.sleep(timeToSleep);
                }
                // }
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            // encontra a fila com menor tamanho
            // BlockingQueue<Integer> smallestQueue;
            // synchronized (queueList) {
            // smallestQueue = Collections.min(queueList,
            // Comparator.comparingInt(BlockingQueue::size));
            // }

            // synchronized (mainQueue) {
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

                // System.out.println("Tentando adicionar na lista: " + index);

                // synchronized (smallestQueue) {
                // System.out.println(" Tamanho da fila menor: " + smallestQueue.size());
                // smallestQueue.add(mainQueue.poll());
                // }

                // if (one == false || two == false || three == false) {
                // secondQueue.add(mainQueue.poll());

                // if (one == false) {
                // one = true;
                // } else if (two == false && one == true) {
                // two = true;
                // } else if (one == true && two == true && three == false) {
                // three = true;
                // }

                // } else {
                // queueList.get(index).add(mainQueue.poll());
                // one = false;
                // two = false;
                // three = false;

                // }

                // System.out.println("Adicionado na lista: " + index);

                // if (index == 3) {
                // index = 0;
                // } else {
                // index++;
                // }

                // BALANCEMANETO DE DISTRIBUIÇÃO PROPORCIONAL
                // bufferList.add(mainQueue.poll());
                // System.out.println("buffer list size: " + bufferList.size());
                // if (bufferList.size() >= 10) {
                // System.out.println("Chegou aqui antes de adicionar na segunda lista");
                // for (int i = 0; i < 5; i++) {

                // while (this.index < 3) {
                // // synchronized (secondQueue) {

                // if (!mainQueue.isEmpty()) {
                // // System.out.println("chegou na segunda lista");
                // this.secondQueue.add(mainQueue.poll());
                // // System.out.println(
                // // "Adicionado na segunda lista | tamanho: " + this.secondQueue.size());
                // // bufferList.remove(i);

                // this.index++;
                // }

                // // }

                // }

                // }
                // for (int i = 0; i < 2; i++) {

                // while (this.index2 < 2) {

                // // synchronized (thirdQueue) {

                // if (!mainQueue.isEmpty()) {
                // // System.out.println("chegou na terceira lista");
                // this.thirdQueue.add(mainQueue.poll());
                // // System.out.println("Adicionado na terceira lista | tamanho: " +
                // // this.thirdQueue.size());
                // // bufferList.remove(i);

                // this.index2++;
                // }

                // // }
                // }

                // }
                // for (int i = 0; i < 2; i++) {

                // while (this.index3 < 1) {

                // // synchronized (fourthQueue) {

                // if (!mainQueue.isEmpty()) {
                // // System.out.println("chegou na quarta lista");
                // this.fourthQueue.add(mainQueue.poll());
                // // System.out.println(
                // // "Adicionado na quarta lista | tamanho: " + this.fourthQueue.size());
                // // bufferList.remove(i);

                // this.index3++;
                // }

                // }
                // }
                // }

                // while (this.index4 < 1) {
                // // for (int i = 0; i < 1; i++) {

                // // synchronized (fifthQueue) {
                // if (!mainQueue.isEmpty()) {
                // // System.out.println("chegou na quinta lista");
                // this.fifthQueue.add(mainQueue.poll());
                // // System.out
                // // .println("Adicionado na quinta lista | tamanho: " +
                // this.fifthQueue.size());
                // // bufferList.remove(i);

                // this.index4++;
                // }

                // // }
                // }
                // }

                // }

            }

            // System.out.println("Proximo na fila: " + mainQueue.peek());
            // }

            // this.index = 0;
            // this.index2 = 0;
            // this.index3 = 0;
            // this.index4 = 0;
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
