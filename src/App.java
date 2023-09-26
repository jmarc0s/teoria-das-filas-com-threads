import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import consumers.FifthConsumer;
import consumers.FourthConsumer;
import consumers.MainConsumer;
import consumers.SecondConsumer;
import consumers.ThirdConsumer;

public class App {

    private final static int MAXIMUM_QUEUE_CAPACITY = 10;
    public static BlockingQueue<Integer> mainQueue = new ArrayBlockingQueue<>(MAXIMUM_QUEUE_CAPACITY);
    public static Integer quantityProducedPerSecond;

    public static void main(String[] args) throws Exception {
        Scanner scanner;
        while (true) {
            System.out.println("Digite a quantidade de elementos produzidos por segundo para o produtor: ");
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
        Thread mainConsumer = new MainConsumer(quantityProducedPerSecond, mainQueue);

        // Thread fifthConsumer = new FifthConsumer(mainQueue);
        // fifthConsumer.start();
        // Thread fourthConsumer = new FourthConsumer();
        // fourthConsumer.start();
        // Thread thirdConsumer = new ThirdConsumer();
        // thirdConsumer.start();
        // Thread secondConsumer = new SecondConsumer();
        // secondConsumer.start();
        // Thread minhaThread = new MainConsumer(mainQueue);
        // minhaThread.start();
        System.out.println("Hello, World!");

    }
}
