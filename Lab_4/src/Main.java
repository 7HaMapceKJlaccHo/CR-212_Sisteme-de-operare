public class Main {
    public static void main(String[] args) {
        try {
            FileHandler file = new FileHandler("LOG.txt");
            Logger.getLogger("").addHandler(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        int number = 5;
        Sticks sticks = new Sticks(number);
        JLabel[] labels = new JLabel[number];
        JLabel[] stickLabels = new JLabel[number];
        JFrame frame = new JFrame("Dining Philosophers");

        JPanel panel = new JPanel(new GridLayout(number + 1, 2));

        for (int i = 0; i < number; i++) {
            labels[i] = new JLabel("Philosopher #" + i + ": ");
            stickLabels[i] = new JLabel("Stick #" + i + ": Free");
            panel.add(labels[i]);
            panel.add(stickLabels[i]);
        }

        frame.add(panel);
        frame.setSize(500, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        Philosopher[] philosophers = new Philosopher[number];
        for (int i = 0; i < number; i++) {
            philosophers[i] = new Philosopher(sticks, labels[i]);
        }

        for (int i = 0; i < number; i++) {
            philosophers[i].start();
        }

        Thread updateStickStates = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                SwingUtilities.invokeLater(() -> {
                    Sticks.State[] stickStates = sticks.getStickStates();
                    for (int i = 0; i < number; i++) {
                        stickLabels[i].setText("Stick #" + i + ": " + stickStates[i]);
                    }
                });
            }
        });
        updateStickStates.start();
    }
}