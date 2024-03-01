import java.util.Random;

public class Producator extends Thread {
    private Depozit depozit;
    private static int totalObiecteProduse = 0;
    private static final int TOTAL_OBIECTE = 38;
    private String nume;

    public Producator(Depozit depozit, String nume) {
        this.depozit = depozit;
        this.nume = nume;
    }

    @Override
    public void run() {
        Random random = new Random();

        while (totalObiecteProduse < TOTAL_OBIECTE) {
            synchronized (depozit) {
                while (depozit.isFull()) {
                    try {
                        System.out.println("Depozitul este plin. " + nume + " asteapta consumatori...");
                        depozit.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                char[] obiecte = new char[2];
                for (int i = 0; i < 2; i++) {
                    obiecte[i] = (char) (random.nextInt(26) + 'A');
                }

                depozit.produce(obiecte);
                totalObiecteProduse += 2;
                System.out.println(nume + " a produs: " + new String(obiecte));
                depozit.notifyAll();
            }

            try {
                Thread.sleep(random.nextInt(1000)); // Wait a bit before producing again
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(nume + " a terminat productia.");
    }

    public static int getTotalObiecteProduse() {
        return totalObiecteProduse;
    }

    private char generareConsoana() {
        String consoane = "BCDFGHJKLMNPQRSTVWXYZ"; // Lista de consoane
        return consoane.charAt(random.nextInt(consoane.length()));
    }
}
