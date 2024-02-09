import javax.swing.*;
import java.awt.*;

public class Virgiliu {

    private static int angle = 0;
    private static Timer timer = null;
    public static void addClock(JPanel panel, int period) {
        JLabel label = getLabel();

        timer = new Timer(period, e -> {
            angle += 6;
            if (angle >= 360) {
                angle = 0;
            }
            label.repaint();
        });

        timer.start();

        panel.add(label);
    }

    public static void stopVirgiliuTimer() {
        timer.setRepeats(false);
        timer.stop();
    }

    private static JLabel getLabel() {
        JLabel label = new JLabel() {
            protected void paintComponent(Graphics g) {
                int centerX = getWidth() / 2;
                int centerY = getHeight() / 2;
                int x = (int) (centerX + Math.sin(Math.toRadians(angle)) * 45);
                int y = (int) (centerY - Math.cos(Math.toRadians(angle)) * 45);
                g.drawLine(centerX, centerY, x, y);
            }
        };
        label.setPreferredSize(new Dimension(200, 200));
        return label;
    }
}
