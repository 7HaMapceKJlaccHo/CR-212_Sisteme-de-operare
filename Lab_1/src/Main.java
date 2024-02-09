import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;


public class Main {

    static Timer t1 = new Timer ();
    static Timer t2 = new Timer ();
    static Timer t3 = new Timer();

    public static void main(String args []) {

        Frame frame = new Frame ();
        frame.setSize(400, 400);
        Button button1 = new Button("Interval de timp");
        Button button2 = new Button("Anumit timp");
        Button button3 = new Button("Perioada indicata");
        Button buttonCancel = new Button("Button cancel");



        button1.setBounds(100,50,100,30);
        button2.setBounds(100,100,100,30);
        button3.setBounds(100,150,100,30);
        buttonCancel.setBounds(100,200,100,30);



        frame.add(button1);
        frame.add(button2);
        frame.add(button3);
        frame.add(buttonCancel);



        Label label = new Label();
        frame.add(label);
        label.setBounds(300, 200, 20, 20);
        label.setVisible(true);

        frame.setLayout(null);
        frame.setVisible(true);




        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame newframe = new JFrame();
                newframe.setSize(200,200);
                newframe.setLayout(null);
                newframe.setVisible(true);
                newframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                Timer t1 = new Timer();
                Atentie atentieTask = new Atentie(newframe);
                t1.scheduleAtFixedRate(atentieTask, 0, 1000);
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                t1.cancel();
            }
        });



        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame newframe = new JFrame();
                newframe.setSize(200,200);
                newframe.setLayout(null);
                newframe.setVisible(true);

                t1.scheduleAtFixedRate(
                        new Atentie(newframe), 0, 1*1000) ;

                t2. schedule ( new TimerTask() {
                    public void run() {


                        JOptionPane.showMessageDialog(newframe," Peste 10 secunde s-a oprit ");
                        ;
                        t1. cancel();
                    }}, 5*1000) ;
            }
        });



        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame inputFrame = new JFrame("Introducere ora");
                inputFrame.setSize(300, 150);
                inputFrame.setLayout(new FlowLayout());

                JTextField hourField = new JTextField(2);
                JTextField minuteField = new JTextField(2);
                JTextField secondField = new JTextField(2);

                JLabel hourLabel = new JLabel("Ora:");
                JLabel minuteLabel = new JLabel("Minute:");
                JLabel secondLabel = new JLabel("Secunde:");

                JButton setAlarmButton = new JButton("Setează Alarma");

                setAlarmButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int hour = Integer.parseInt(hourField.getText());
                        int minute = Integer.parseInt(minuteField.getText());
                        int second = Integer.parseInt(secondField.getText());

                        Calendar calendar = Calendar.getInstance();
                        calendar.set(Calendar.HOUR_OF_DAY, hour);
                        calendar.set(Calendar.MINUTE, minute);
                        calendar.set(Calendar.SECOND, second);
                        Date alarmTime = calendar.getTime();


                        Timer t3 = new Timer();
                        t3.schedule(new Alarma("Ora mesei!"), alarmTime);

                        inputFrame.dispose();
                    }
                });

                inputFrame.add(hourLabel);
                inputFrame.add(hourField);
                inputFrame.add(minuteLabel);
                inputFrame.add(minuteField);
                inputFrame.add(secondLabel);
                inputFrame.add(secondField);
                inputFrame.add(setAlarmButton);

                inputFrame.setVisible(true);
            }
        });



    }
}
