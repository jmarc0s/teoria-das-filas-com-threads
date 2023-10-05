import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

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

        Thread mainConsumer = new MainConsumer(
                quantityProducedPerSecond,
                mainQueue,
                secondQueue,
                thirdQueue,
                fourthQueue,
                fifthQueue);

        Thread secondConsumer = new Consumer("Second Consumer", secondQueue, mainConsumer);
        Thread thirdConsumer = new Consumer("Third Consumer", thirdQueue, secondConsumer);
        Thread fourthConsumer = new Consumer("Fourth Consumer", fourthQueue, thirdConsumer);
        Thread fifthConsumer = new Consumer("Fifth Consumer", fifthQueue, fourthConsumer);

        Thread queueMonitor = new QueueMonitor(
                quantityProducedPerSecond,
                mainQueue,
                secondQueue,
                thirdQueue,
                fourthQueue,
                fifthQueue,
                mainConsumer,
                secondConsumer,
                thirdConsumer,
                fourthConsumer,
                fifthConsumer);
    }
}
