import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import consumers.MainConsumer;
import consumers.Consumer;

public class App {

    private final static int MAXIMUM_QUEUE_CAPACITY = 10;
    public static Integer quantityProducedPerSecond;
    public static BlockingQueue<Integer> mainQueue = new LinkedBlockingQueue<>(MAXIMUM_QUEUE_CAPACITY);
    public static BlockingQueue<Integer> secondQueue = new LinkedBlockingQueue<>(MAXIMUM_QUEUE_CAPACITY);
    public static BlockingQueue<Integer> thirdQueue = new LinkedBlockingQueue<>(MAXIMUM_QUEUE_CAPACITY);
    public static BlockingQueue<Integer> fourthQueue = new LinkedBlockingQueue<>(MAXIMUM_QUEUE_CAPACITY);
    public static BlockingQueue<Integer> fifthQueue = new LinkedBlockingQueue<>(MAXIMUM_QUEUE_CAPACITY);

    public static void main(String[] args) throws Exception {
        Scanner scanner;
        while (true) {
            System.out.println(
                    "Digite a quantidade de elementos produzidos por segundo para o produtor (min: 1, max: 10): ");
            scanner = new Scanner(System.in);

            if (scanner.hasNextInt()) {
                quantityProducedPerSecond = scanner.nextInt();

                if (quantityProducedPerSecond >= 1 && quantityProducedPerSecond <= 10) {
                    break;
                }

            }

            System.out.println("Valor inválido!");
        }
        scanner.close();

        Thread producer = new Producer(quantityProducedPerSecond, mainQueue);

        Thread mainConsumer = new MainConsumer(quantityProducedPerSecond, mainQueue, secondQueue, thirdQueue,
                fourthQueue, fifthQueue);

        Thread secondConsumer = new Consumer("Second Consumer", secondQueue, mainConsumer);
        // secondConsumer.setPriority(1);
        Thread thirdConsumer = new Consumer("Third Consumer", thirdQueue, secondConsumer);
        // thirdConsumer.setPriority(3);
        Thread fourthConsumer = new Consumer("Fourth Consumer", fourthQueue, thirdConsumer);
        // fourthConsumer.setPriority(7);
        Thread fifthConsumer = new Consumer("Fifth Consumer", fifthQueue, fourthConsumer);
        // fifthConsumer.setPriority(10);

        // Thread fifthConsumer = new FifthConsumer(mainQueue);
        // fifthConsumer.start();
        // Thread fourthConsumer = new FourthConsumer();
        // fourthConsumer.start();
        // Thread thirdConsumer = new ThirdConsumer();
        // thirdConsumer.start();

        // Thread minhaThread = new MainConsumer(mainQueue);
        // minhaThread.start();
        System.out.println("Hello, World!");

    }
}
