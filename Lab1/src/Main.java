import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Main extends JFrame {

    public Main() {
        setTitle("Laborator 1");

        setLayout(new GridLayout(1, 3));

        JPanel sectiune3 = createSection("Cristi");
        JPanel sectiune2 = createSection("Roman");
        JPanel sectiune1 = createSection("Virgiliu");

        Virgiliu.addClock(sectiune1, 1000);
        Cristi cristi = new Cristi(sectiune3);
        Visterniceanu.start(sectiune2);

        add(sectiune1);
        add(sectiune2);
        add(sectiune3);

        setSize(600, 400);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setVisible(true);
    }

    private JPanel createSection(String labelText) {
        JPanel sectionPanel = new JPanel(new FlowLayout());

        Border border = BorderFactory.createTitledBorder(labelText);
        sectionPanel.setBorder(border);
        return sectionPanel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::new);
    }
}
