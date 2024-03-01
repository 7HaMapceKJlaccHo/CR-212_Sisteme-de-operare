import javax.swing.*;
import java.awt.*;
import java.util .*;
import java.util.Timer;


public class Cristi {
    JPanel panel;
    Random random = new Random();

    public Cristi(JPanel panel) {
        this.panel = panel;

        JPanel square1 = createSquare();
        panel.add(square1, BorderLayout.NORTH);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 17);
        calendar.set(Calendar.MINUTE, 50);
        calendar.set(Calendar.SECOND, 0);
        Date ora = calendar.getTime();

        Timer t1 = new Timer();
        t1.schedule(new TimerTask() {
            @Override
            public void run() {
                square1.setBackground(getRandomColor());
            }
        }, ora);
    }

    private JPanel createSquare() {
        JPanel square = new JPanel();
        square.setPreferredSize(new Dimension(100, 100));
        square.setBackground(Color.WHITE);
        square.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        return square;
    }

    private Color getRandomColor() {
        return new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
    }
}
