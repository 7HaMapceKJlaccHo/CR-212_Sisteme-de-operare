public class Depozit {
    private static final int CAPACITATE = 11;
    private char[] obiecte = new char[CAPACITATE];
    private int count = 0;

    public synchronized void produce(char[] obiecteProduse) {
        while (count + obiecteProduse.length > CAPACITATE) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (char obiect : obiecteProduse) {
            obiecte[count++] = obiect;
        }


    }

    public synchronized char[] consume() {
        while (count < 2) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        char[] consumate = new char[2];
        for (int i = 0; i < 2; i++) {
            consumate[i] = obiecte[--count];
        }

        System.out.println("Consumat: 2 obiecte.");
        return consumate;
    }

    public synchronized boolean isEmpty() {
        return count == 0;
    }

    public synchronized boolean isFull() {
        return count == CAPACITATE;
    }
}
