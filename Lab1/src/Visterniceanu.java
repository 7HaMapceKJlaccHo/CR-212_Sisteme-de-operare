import javax.swing.*;

public class Visterniceanu {

    public static void start(JPanel section) {
        Timer stopTimer = new Timer(16000, e -> {
            SwingUtilities.invokeLater(() -> {
                    JLabel message = new JLabel("Cronometrul a fost stopat");
                    Virgiliu.stopVirgiliuTimer();
                    section.add(message);
                    section.revalidate();
                    section.repaint();
            });
        });
        stopTimer.setRepeats(false);
        stopTimer.start();
    }
}
