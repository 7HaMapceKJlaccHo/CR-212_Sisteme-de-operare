import javax.swing.*;
import java.util.Random;


import java.util.TimerTask;

public class Atentie extends TimerTask {
    int a = 4, b = 2;
    JFrame frame;

    public Atentie(JFrame frame){
        this.frame = frame;
    }

    int adunare(int a, int b){
        return a+b;
    }

    int scadere(int a, int b){
        return a-b;
    }

    int inmultire(int a, int b){
        return a*b;
    }

    int impartire(int a, int b){
        return a/b;
    }


    public void run () {
        Random rand = new Random();
        int c = rand.nextInt(4);
        String message = "";
        switch (c){
            case 0:
                message = "Suma = " + adunare(a, b);
                break;
            case 1:
                message = "Diferenta = " + scadere(a, b);
                break;
            case 2:
                message = "Produs = " + inmultire(a, b);
                break;
            case 3:
                message = "Impartirea = " + impartire(a, b);
                break;
        }
        JOptionPane.showMessageDialog(frame, message);
    }
}