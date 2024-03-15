import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import java.lang.Math;
import java.util.List;
import java.util.ArrayList;

public class Main {
    static int NrP = 2;
    static int NrC = 3;
    static int NrD = 8;
    static int NrConsumari = 49;

    static JTextArea outputArea = new JTextArea();

    public static void main(String[] args) {
        JFrame frame = new JFrame("Lab2");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JScrollPane scrollPane = new JScrollPane(outputArea);
        frame.add(scrollPane, BorderLayout.CENTER);

        outputArea.setEditable(false);

        frame.setSize(600, 400);
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
    int obiect1, obiect2;
    Depozit d1;
    static int totalProdused = 0;

    public Producator(int a, Depozit d1){
        setName("Producator " + a);
        this.d1 = d1;
    }

    @Override
    public void run() {
        while (d1.totalConsumed < Main.NrConsumari) {
            if (totalProdused < 48) {
                obiect1 = (int) (Math.random() * 100) * 2;
                obiect2 = (int) (Math.random() * 100) * 2;
                d1.put(obiect1, obiect2, getName());
                totalProdused = totalProdused+2;
            }else if(totalProdused == 48){
                obiect1 = (int) (Math.random() * 100) * 2;
                d1.put2(obiect1, getName());
                totalProdused++;
            }
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                System.err.println(e);
            }
            if (d1.totalConsumed > 48) {
                Main.printToGui("Total consumat " + d1.totalConsumed);
            }
        }
    }
}

class Consumator extends Thread {
    Depozit d1;
    List<Integer> ListaElem = new ArrayList<>();

    public Consumator(int a, Depozit d1) {
        setName("Consumator " + a);
        this.d1 = d1;
    }

    @Override
    public void run() {
        for (int i = 0; i < Main.NrConsumari / 2 / Main.NrC+1; i++) {
            if(d1.totalConsumed<48) {
                ListaElem = d1.getObj();
                int elem = ListaElem.get(0);
                int elem2 = ListaElem.get(1);
                Main.printToGui(getName() + " a consumat " + elem + " si " + elem2);
            }else if(d1.totalConsumed==48){
                ListaElem = d1.getObj();
                System.out.println(ListaElem);
                int elem = ListaElem.get(0);
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
    List<Integer> stoc = new ArrayList<>();
    int totalConsumed = 0;

    public synchronized void put(int elem1, int elem2, String nume) {
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
            Main.printToGui(elem1 + " se introduce in depozit, " + elem2 + " se pierde");
        } else if (stoc.size() <= Main.NrD - 2) {
            stoc.add(elem1);
            stoc.add(elem2);
            Main.printToGui(elem1 + " , " + elem2 + " se introduc in depozit");
        }
        if (stoc.size() == Main.NrD) {
            Main.printToGui("Depozit full");
        }
        notifyAll();
    }

    public synchronized void put2(int elem1, String nume) {
        while (stoc.size() >= Main.NrD) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.err.println(e);
            }
        }
        Main.printToGui(nume + " a produs" + elem1);
        if (stoc.size() <= Main.NrD - 1) {
            stoc.add(elem1);
            Main.printToGui(elem1 + " se introduce in depozit, ");
        }
        if (stoc.size() == Main.NrD) {
            Main.printToGui("Depozit full");
        }
        System.out.println(stoc);
        notifyAll();
    }

    public synchronized List<Integer> getObj() {
        int elem1;
        int elem2;
        List<Integer> elemReturn = new ArrayList<>();
        while (stoc.size() == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.err.println(e);
            }
        }
        if (totalConsumed < 48) {
            if (stoc.size() > 1) {
                elem1 = stoc.get(0);
                stoc.remove(0);
                elem2 = stoc.get(0);
                stoc.remove(0);
                Main.printToGui(elem1 + " si " + elem2 + " paraseste depozitul");
                totalConsumed = totalConsumed + 2;
                elemReturn.add(elem1);
                elemReturn.add(elem2);
            }
        }else{
            if (stoc.size() > 0) {
                elem1 = stoc.get(0);
                stoc.remove(0);
                Main.printToGui(elem1 + " paraseste depozitul");
                totalConsumed = totalConsumed + 1;
                elemReturn.add(elem1);
            }
        }

        notifyAll();
        return elemReturn;
    }
}
