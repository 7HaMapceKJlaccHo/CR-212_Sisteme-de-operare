import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

public class Alarma extends TimerTask {
    public String mesaj ;
    JFrame frame;
    public Alarma(String mesaj) {
        this.mesaj = mesaj ;
    }

    public void run() {



        System.out.println( mesaj );
        JOptionPane.showMessageDialog(frame, mesaj);
    }
}