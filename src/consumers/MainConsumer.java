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
        boolean one = false;
        boolean two = false;
        boolean three = false;
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
            BlockingQueue<Integer> smallestQueue;
            synchronized (queueList) {
                smallestQueue = Collections.min(queueList,
                        Comparator.comparingInt(BlockingQueue::size));
            }

            synchronized (mainQueue) {
                if (!mainQueue.isEmpty()) {
                    // System.out.println("Tentando adicionar na lista: " + index);

                    synchronized (smallestQueue) {
                        smallestQueue.add(mainQueue.poll());
                    }

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

                    // System.out.println("buffer list size: " + bufferList.size());
                    // // bufferList.add(mainQueue.poll());

                    // // if (bufferList.size() >= 10) {
                    // System.out.println("Chegou aqui antes de adicionar na segunda lista");
                    // for (int i = 0; i < 5; i++) {

                    // if (mainQueue.peek() != null) {
                    // synchronized (secondQueue) {
                    // this.secondQueue.add(mainQueue.poll());
                    // System.out.println("Adicionado na segunda lista");
                    // }

                    // }

                    // // bufferList.remove(i);
                    // }
                    // for (int i = 0; i < 2; i++) {

                    // if (mainQueue.peek() != null) {
                    // synchronized (thirdQueue) {
                    // this.thirdQueue.add(mainQueue.poll());
                    // System.out.println("Adicionado na terceira lista");
                    // // bufferList.remove(i);
                    // }
                    // }

                    // }
                    // for (int i = 0; i < 1; i++) {

                    // if (mainQueue.peek() != null) {
                    // synchronized (fourthQueue) {
                    // this.fourthQueue.add(mainQueue.poll());
                    // System.out.println("Adicionado na quarta lista");
                    // // bufferList.remove(i);
                    // }
                    // }

                    // }

                    // for (int i = 0; i < 1; i++) {

                    // if (mainQueue.peek() != null) {
                    // synchronized (fifthQueue) {
                    // this.fifthQueue.add(mainQueue.poll());
                    // System.out.println("Adicionado na quinta lista");
                    // // bufferList.remove(i);
                    // }
                    // }

                    // }

                    // }

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

    }

    public Integer getTimeToSleep() {
        return this.timeToSleep;
    }
}
