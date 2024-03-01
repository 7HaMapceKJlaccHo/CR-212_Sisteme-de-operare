import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Depozit {
    private final int volum_depozit = 5;
    private int obiect = 0;
    private static int totalProduced = 0;
    private static int totalConsumed = 0;
    private final List<Integer> numereImpare = new ArrayList<>();
    private JTextArea textArea;

    public void setTextArea(JTextArea textArea) {
        this.textArea = textArea;
    }

    public synchronized void produce() {
        while (totalProduced < 19) {
            while (obiect + 2 >= volum_depozit) {
                log("Depozitul este plin");
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            Random rand = new Random();
            int objectsToProduce = (totalProduced == 18) ? 1 : 2;
            if (totalProduced + objectsToProduce > 19) {
                objectsToProduce = 19 - totalProduced;
            }
            for (int i = 0; i < objectsToProduce; i++) {
                int impar = rand.nextInt(5) * 2 + 1;
                numereImpare.add(impar);
                log(impar + " ");
            }

            obiect += objectsToProduce;
            totalProduced += objectsToProduce;

            log(Thread.currentThread().getName() + " a produs " + objectsToProduce + " obiect(e) (volum_depozit: " + obiect + ") " + totalProduced);
            notifyAll();
        }
    }

        public synchronized void consume() {
            while (totalConsumed < 19) {
                while (obiect < 2 || totalConsumed + 2 > 19) {
                    log("Depozitul este gol");
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                synchronized (numereImpare) {
                    log(String.valueOf(numereImpare.get(0)));
                    numereImpare.remove(0);
                    log(String.valueOf(numereImpare.get(0)));
                    numereImpare.remove(0);
                }

                obiect -= 2;
                totalConsumed += 2;

                log(Thread.currentThread().getName() + " a consumat 2 obiecte (volum_depozit: " + obiect + ")");

                if (totalConsumed == 18) {
                    totalConsumed++;
                    obiect--;
                    log(Thread.currentThread().getName() + " a consumat 1 obiect (volum_depozit: " + obiect + ") " + totalConsumed);
                }

                notifyAll();
            }
        }


    private void log(String message) {
        SwingUtilities.invokeLater(() -> textArea.append(message + "\n"));
    }
}

