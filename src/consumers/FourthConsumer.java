package consumers;

public class FourthConsumer extends Thread {

    @Override
    public void run() {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println("Quarto consumidor iniciado");
    }
}
