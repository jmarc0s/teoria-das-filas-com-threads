package consumers;

import java.util.List;

public class FifthConsumer extends Thread {
    List<Integer> mainQueue;

    public FifthConsumer(List<Integer> mainQueue) {
        this.mainQueue = mainQueue;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("Quinto consumidor iniciado");
        System.out.println("Tamanho da fila: " + mainQueue.size());
    }

}
