import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import java.lang.Math;
import java.util.List;
import java.util.ArrayList;

public class Main {
    static int NrP = 4;
    static int NrC = 3;
    static int NrD = 10;
    static int NrConsumari = 31;

    static JTextArea outputArea = new JTextArea();

    public static void main(String[] args) {
        JFrame frame = new JFrame("Laborator 2");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JScrollPane scrollPane = new JScrollPane(outputArea);
        frame.add(scrollPane, BorderLayout.CENTER);

        outputArea.setEditable(false);

        frame.setSize(600, 600);
        frame.setVisible(true);

        Depozit d1 = new Depozit();
        for(int i = 0; i < NrP; i++){
            new Producator(i, d1).start();
        }
        for(int i = 0; i < NrC; i++){
            new Consumator(i, d1).start();
        }

    }

    public static void printToGui(String text) {
        SwingUtilities.invokeLater(() -> outputArea.append(text + "\n"));
    }
}

class Producator extends Thread {
    char obiect1, obiect2;
    char[] vocale = {'A','E','I','O','U'};
    Depozit d1;
    static int totalProdused = 0;

    public Producator(int a, Depozit d1){
        setName("Producator " + a);
        this.d1 = d1;
    }

    @Override
    public void run() {
        while (d1.totalConsumed < Main.NrConsumari) {
            if (totalProdused < Main.NrConsumari-1) {
                int index = (int) (Math.random() * vocale.length);
                obiect1 = vocale[index];
                index = (int) (Math.random() * vocale.length);
                obiect2 = vocale[index];
                d1.put(obiect1, obiect2, getName());
                totalProdused = totalProdused+2;
            }
            if(totalProdused == Main.NrConsumari-1){
                int index = (int) (Math.random() * vocale.length);
                obiect1 = vocale[index];
                d1.put2(obiect1, getName());
                totalProdused++;
            }
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                System.err.println(e);
            }
            if (d1.totalConsumed > Main.NrConsumari-1) {
                Main.printToGui("Obiecte consumate: " + d1.totalConsumed);
            }
        }
    }
}

class Consumator extends Thread {
    Depozit d1;
    List<Character> ListaElem = new ArrayList<>();

    public Consumator(int a, Depozit d1) {
        setName("Consumator " + a);
        this.d1 = d1;
    }

    @Override
    public void run() {
        for (int i = 0; i < Main.NrConsumari / 2 / Main.NrC+1; i++) {
            if(d1.totalConsumed<Main.NrConsumari-1) {
                ListaElem = d1.getObj();
                char elem = ListaElem.get(0);
                char elem2 = ListaElem.get(1);
                Main.printToGui(getName() + " a consumat " + elem + " si " + elem2);
            }else if(d1.totalConsumed==Main.NrConsumari-1){
                ListaElem = d1.getObj();
                char elem = ListaElem.get(0);
                Main.printToGui(getName() + " a consumat " + elem);
            }
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                System.err.println(e);
            }
        }
    }
}

class Depozit {
    List<Character> stoc = new ArrayList<>();
    int totalConsumed = 0;
    public synchronized void put(char elem1, char elem2, String nume) {
        while (stoc.size() >= Main.NrD) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.err.println(e);
            }
        }
        Main.printToGui(nume + " a produs " + elem1 + " , " + elem2);
        if (stoc.size() == Main.NrD - 1) {
            stoc.add(elem1);
            Main.printToGui(elem1 + " in depozit, " + elem2 + " se pierde");
        } else if (stoc.size() <= Main.NrD - 2) {
            stoc.add(elem1);
            stoc.add(elem2);
            Main.printToGui(elem1 + " , " + elem2 + " inta in depozit");
        }
        if (stoc.size() == Main.NrD) {
            Main.printToGui("Depozit plin");
        }
        notifyAll();
    }

    public synchronized void put2(char elem1, String nume) {
        while (stoc.size() >= Main.NrD) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.err.println(e);
            }
        }
        Main.printToGui(nume + " a produs " + elem1);
        if (stoc.size() <= Main.NrD - 1) {
            stoc.add(elem1);
            Main.printToGui(elem1 + " intra in depozit, ");
        }
        if (stoc.size() == Main.NrD) {
            Main.printToGui("Depozit plin");
        }
        notifyAll();
    }

    public synchronized List<Character> getObj() {
        char elem1;
        char elem2;
        List<Character> elemReturn = new ArrayList<>();
        while (stoc.size() == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.err.println(e);
            }
        }
        if (totalConsumed < Main.NrConsumari-1) {
            if (stoc.size() > 1) {
                elem1 = stoc.get(0);
                stoc.remove(0);
                elem2 = stoc.get(0);
                stoc.remove(0);
                Main.printToGui(elem1 + " si " + elem2 + " ies din depozit");
                totalConsumed = totalConsumed + 2;
                elemReturn.add(elem1);
                elemReturn.add(elem2);
            }
        }else{
            if (stoc.size() > 0) {
                elem1 = stoc.get(0);
                stoc.remove(0);
                Main.printToGui(elem1 + " iese din depozit");
                totalConsumed = totalConsumed + 1;
                elemReturn.add(elem1);
            }
        }

        notifyAll();
        return elemReturn;
    }
}
