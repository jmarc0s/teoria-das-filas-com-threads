package consumers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class MainConsumer extends Thread {

    BlockingQueue<Integer> mainQueue;
    BlockingQueue<Integer> secondQueue;
    BlockingQueue<Integer> thirdQueue;
    BlockingQueue<Integer> fourthQueue;
    BlockingQueue<Integer> fifthQueue;

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
        System.out.println("Consumidor principal iniciado");

        // cria uma lista de filas
        List<BlockingQueue<Integer>> queueList = List.of(this.secondQueue, thirdQueue, fourthQueue, fifthQueue);
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
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            // encontra a fila com menor tamanho
            BlockingQueue<Integer> smallestQueue = Collections.min(queueList,
                    Comparator.comparingInt(BlockingQueue::size));

            if (!mainQueue.isEmpty()) {
                smallestQueue.add(mainQueue.poll());
            }

            // int minValue = Collections.min(queueList
            // .stream()
            // .map(queue -> queue.size())
            // .collect(Collectors.toList()));

            // if (secondQueue.size() == minValue) {
            // // faça o que tem que ser feito
            // } else if (thirdQueue.size() == minValue) {
            // // faça o que tem que ser feito
            // } else if (fourthQueue.size() == minValue) {
            // // faça o que tem que ser feito
            // } else {
            // // faça o que tem que ser feito
            // }

            System.out.println("Proximo na fila: " + mainQueue.peek());
        }

    }

    public Integer getTimeToSleep() {
        return this.timeToSleep;
    }
}
