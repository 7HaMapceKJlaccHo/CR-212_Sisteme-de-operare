import javax.swing.*;
import java.awt.*;

public class Virgiliu {

    private static int angle = 0; // Unghiul de rotație pentru simularea mișcării acului de ceasornic

    public static void addClock(JPanel panel) {
        // Definește un label personalizat care suprascrie paintComponent pentru a desena acul de ceasornic
        JLabel label = new JLabel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                int centerX = getWidth() / 2;
                int centerY = getHeight() / 2;
                int x = (int) (centerX + Math.sin(Math.toRadians(angle)) * 45); // Calculează x folosind unghiul
                int y = (int) (centerY - Math.cos(Math.toRadians(angle)) * 45); // Calculează y folosind unghiul
                g2.drawLine(centerX, centerY, x, y); // Desenează linia reprezentând acul
            }
        };
        label.setPreferredSize(new Dimension(100, 100)); // Setează dimensiunea label-ului

        // Crează un timer care se declanșează o dată pe secundă
        Timer timer = new Timer(1000, e -> {
            angle += 6; // 6 grade pentru fiecare secundă, simulează mișcarea unui ac de ceasornic
            if (angle >= 360) {
                angle = 0; // Resetăm unghiul după o rotație completă
            }
            label.repaint(); // Solicită redesenarea label-ului
        });

        timer.start(); // Pornim timer-ul

        panel.add(label); // Adaugă label-ul personalizat la panel
    }
}
