public class Consumator extends Thread {
    private Depozit depozit;
    private String nume;

    public Consumator(Depozit depozit, String nume) {
        this.depozit = depozit;
        this.nume = nume;
    }

    @Override
    public void run() {
        int consumedCount = 0;

        while (consumedCount < 38) {
            synchronized (depozit) {
                while (depozit.isEmpty()) {
                    try {
                        System.out.println("Depozitul este gol. " + nume + " asteapta producatori...");
                        depozit.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                char[] obiecte = depozit.consume();
                consumedCount += 2;
                System.out.println(nume + " a consumat: " + new String(obiecte));
                depozit.notifyAll();
            }

            try {
                Thread.sleep(500); // Wait a bit before consuming again
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(nume + " a terminat consumul.");
    }
}
