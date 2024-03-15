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
    public final List<Integer> numereImpare = new ArrayList<>();
    private JTextArea textArea;

    public void setTextArea(JTextArea textArea) {
        this.textArea = textArea;
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


    public void log(String message) {
        SwingUtilities.invokeLater(() -> textArea.append(message + "\n"));
    }
}

