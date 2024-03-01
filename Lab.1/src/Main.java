import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.Calendar;
import java.util.Date;
import java.util.TimerTask;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Timers App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        JLabel timerLabel = new JLabel("00");

        TimerButton timerButton = new TimerButton("Cronometru", timerLabel);
        HourglassButton hourglassButton = new HourglassButton("Clepsidră (5 sec)",timerLabel);
        TimedColorChangeButton timedColorChangeButton = new TimedColorChangeButton("Schimbă culoarea la ora stabilita", 18, 26);
        frame.add(timerLabel);
        frame.add(timerButton.getButton());
        frame.add(hourglassButton.getButton());
        frame.add(timedColorChangeButton.getButton());

        frame.pack();
        frame.setVisible(true);
    }
}

class TimerButton {
    private final JButton button;
    private Timer timer;
    private TimerTask timerTask;
    private final JLabel timerLabel;

    public TimerButton(String buttonText, JLabel timerLabel) {
        this.button = new JButton(buttonText);
        this.timerLabel = timerLabel;


        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (timerTask == null) {
                    startTimer();
                } else {
                    stopTimer();
                }
            }
        });
    }

    public JButton getButton() {
        return button;
    }

    private void startTimer() {
        timer = new Timer();
        timerTask = new TimerTask() {
            int secondsPassed = 0;

            @Override
            public void run() {
                secondsPassed++;
                timerLabel.setText(Integer.toString(secondsPassed));
            }
        };
        timer.scheduleAtFixedRate(timerTask, 0, 1000);
    }

    private void stopTimer() {
        timerTask.cancel();
        timerTask = null;
        timer.cancel();
        timer.purge();
    }
}

class HourglassButton {
    private final JButton button;
    private final JLabel timerLabel;

    public HourglassButton(String buttonText, JLabel timerLabel) {
        this.button = new JButton(buttonText);
        this.timerLabel=timerLabel;

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    int secondsPassed = 5;

                    public void run() {
                        secondsPassed--;
                        button.setText(String.valueOf("Clepsidră ("+secondsPassed+" sec)"));
                        if(secondsPassed==0) {
                            JOptionPane.showMessageDialog(button.getRootPane(), "Timpul a expirat!");
                            timer.cancel();
                            button.setText(String.valueOf(buttonText));
                        }
                    }
                }, 0,1000);
            }
        });
    }

    public JButton getButton() {
        return button;
    }
}

class TimedColorChangeButton {
    private final JButton button;
    private final Color[] colors = {Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.ORANGE, Color.LIGHT_GRAY};
    private int currentIndex = 0;
    private Timer timer;

    public TimedColorChangeButton(String buttonText, int hour, int minute) {
        this.button = new JButton(buttonText);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startColorChangingAtTime(hour, minute);
            }
        });
    }

    public JButton getButton() {
        return button;
    }

    private void startColorChangingAtTime(int hour, int minute) {
        Calendar startTime = Calendar.getInstance();
        startTime.set(Calendar.HOUR_OF_DAY, hour);
        startTime.set(Calendar.MINUTE, minute);
        startTime.set(Calendar.SECOND, 0);

        Date now = new Date();
        long delay = startTime.getTimeInMillis() - now.getTime();
        if (delay < 0) {
            // Dacă ora specificată este în trecut, adăugăm 24 de ore pentru a programarea să fie pentru ziua următoare
            delay += 24 * 60 * 60 * 1000;
        }

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                button.setBackground(colors[currentIndex]);
                currentIndex = (currentIndex + 1) % colors.length;
            }
        }, delay, 24 * 60 * 60 * 1000); // Se repetă la fiecare 24 de ore
    }

    public void stopColorChanging() {
        if (timer != null) {
            timer.cancel();
        }
    }
}