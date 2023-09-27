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
        // System.out.println("Consumidor principal iniciado");

        // cria uma lista de filas
        List<BlockingQueue<Integer>> queueList = List.of(this.secondQueue, this.thirdQueue, this.fourthQueue,
                this.fifthQueue);
        int index = 0;
        List<Integer> bufferList = new ArrayList<>();
        while (true) {
            try {

                synchronized (mainQueue) {

                    if (mainQueue.size() >= 5) {
                        timeToSleep = (1000 / quantityProducedPerSecond) / 2;
                        Thread.sleep(timeToSleep);
                    } else {
                        timeToSleep = (1000 / quantityProducedPerSecond) * 2;
                        Thread.sleep(timeToSleep);
                    }
                }
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

            synchronized (mainQueue) {
                if (!mainQueue.isEmpty()) {
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
                    bufferList.add(mainQueue.poll());
                    System.out.println("buffer list size: " + bufferList.size());
                    if (bufferList.size() >= 10) {
                        // System.out.println("Chegou aqui antes de adicionar na segunda lista");
                        for (int i = 0; i < 6; i++) {

                            synchronized (secondQueue) {

                                if (bufferList.size() > i) {
                                    this.secondQueue.add(bufferList.get(i));
                                    System.out.println(
                                            "Adicionado na segunda lista | tamanho: " + this.secondQueue.size());
                                    bufferList.remove(i);
                                }

                            }

                        }
                        for (int i = 0; i < 2; i++) {

                            synchronized (thirdQueue) {
                                if (bufferList.size() > i) {
                                    this.thirdQueue.add(bufferList.get(i));
                                    System.out.println("Adicionado na terceira lista | tamanho: " +
                                            this.thirdQueue.size());
                                    bufferList.remove(i);
                                }
                            }

                        }
                        for (int i = 0; i < 1; i++) {

                            synchronized (fourthQueue) {
                                if (bufferList.size() > i) {
                                    this.fourthQueue.add(bufferList.get(i));
                                    System.out.println(
                                            "Adicionado na quarta lista | tamanho: " + this.fourthQueue.size());
                                    bufferList.remove(i);
                                }

                            }

                        }

                        for (int i = 0; i < 1; i++) {
                            synchronized (fifthQueue) {
                                if (bufferList.size() > i) {
                                    this.fifthQueue.add(bufferList.get(i));
                                    System.out
                                            .println("Adicionado na quinta lista | tamanho: " + this.fifthQueue.size());
                                    bufferList.remove(i);
                                }
                            }

                        }

                    }

                }

                // System.out.println("Proximo na fila: " + mainQueue.peek());
            }
        }

    }

    public Integer getTimeToSleep() {
        return this.timeToSleep;
    }
}
