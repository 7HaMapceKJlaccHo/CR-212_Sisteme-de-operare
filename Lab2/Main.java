public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Depozit Example");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(600, 400);

            JTextArea textArea = new JTextArea();
            JScrollPane scrollPane = new JScrollPane(textArea);
            textArea.setEditable(false);
            frame.add(scrollPane, BorderLayout.CENTER);

            Depozit depozit = new Depozit();
            depozit.setTextArea(textArea);

            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(2, 2));

            JButton startButton = new JButton("Start");

            startButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Producer producerThread1 = new Producer(depozit, "Producator 1");
                    Producer producerThread2 = new Producer(depozit, "Producator 2");
                    Producer producerThread3 = new Producer(depozit, "Producator 3");

                    Consumer consumerThread1 = new Consumer(depozit, "Consumator 1");
                    Consumer consumerThread2 = new Consumer(depozit, "Consumator 2");
                    Consumer consumerThread3 = new Consumer(depozit, "Consumator 3");
                    Consumer consumerThread4 = new Consumer(depozit, "Consumator 4");

                    producerThread1.start();
                    producerThread2.start();
                    producerThread3.start();

                    consumerThread1.start();
                    consumerThread2.start();
                    consumerThread3.start();
                    consumerThread4.start();
                }
            });

            panel.add(startButton);
            frame.add(panel, BorderLayout.SOUTH);

            frame.setVisible(true);
        });
    }
}