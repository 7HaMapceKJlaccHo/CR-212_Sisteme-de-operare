import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {
    private Depozit depozit;
    private JTextArea produseTextArea;
    private JTextArea consumateTextArea;

    public Main() {
        setTitle("Depozit - Produse și Consumate");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        depozit = new Depozit();

        JPanel mainPanel = new JPanel(new GridLayout(1, 2));
        produseTextArea = new JTextArea();
        consumateTextArea = new JTextArea();
        mainPanel.add(new JScrollPane(produseTextArea));
        mainPanel.add(new JScrollPane(consumateTextArea));

        add(mainPanel);

        setVisible(true);
    }

    public void updateProduseConsumateP( String thread,char[] produse, char[] consumate) {
        SwingUtilities.invokeLater(() -> {
            produseTextArea.append(thread+  new String(produse) + "\n");

        });
    }

    public void updateProduseConsumateC( String thread,char[] produse, char[] consumate) {
        SwingUtilities.invokeLater(() -> {

            consumateTextArea.append(thread+new String(consumate) + "\n");
        });
    }

    public static void main(String[] args) {
        Main gui = new Main();
        gui.startSimulation();
    }

    public void startSimulation() {
        Producator producator1 = new Producator(depozit, "Producator1", this);
        Producator producator2 = new Producator(depozit, "Producator2", this);
        Producator producator3 = new Producator(depozit, "Producator3", this);
        Consumator consumator1 = new Consumator(depozit, "Consumator1", this);
        Consumator consumator2 = new Consumator(depozit, "Consumator2", this);

        producator1.start();
        producator2.start();
        producator3.start();
        consumator1.start();
        consumator2.start();

        try {
            producator1.join();
            producator2.join();
            producator3.join();
            consumator1.join();
            consumator2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Producatorii au produs in total " + Producator.getTotalObiecteProduse() + " obiecte.");
        System.out.println("Programul s-a incheiat.");
    }
}
