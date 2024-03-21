package Lab_4.src;

import javax.swing.*;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

class Philosopher extends Thread {

    private int name;
    private State currentState;
    private enum State { Hungry, Eating, Thinking };
    private Sticks sticks;
    private boolean wasHungry = false;
    private static int currentName = 0;
    private JLabel stateLabel;
    private Logger logger;
    private boolean hasEaten = false;

    public Philosopher(Sticks sticks, JLabel stateLabel) {
        this.sticks = sticks;
        this.currentState = State.Hungry;
        this.name = currentName++;
        this.stateLabel = stateLabel;
        logger = Logger.getLogger("Philosopher #" + name);
        logger.addHandler(new ConsoleHandler());
        logger.setLevel(Level.INFO);
        logger.info("Philosopher #" + name + " has reached the table!");
    }

    @Override
    public void run() {
        while (!hasEaten) {
            switch (currentState) {
                case Hungry:
                    if (!wasHungry) {
                        logger.info("Philosopher #" + name + " is hungry");
                    }
                    wasHungry = true;
                    if (sticks.takeSticks(name)) {
                        this.currentState = State.Eating;
                    }
                    break;
                case Eating:
                    logger.info("Philosopher #" + name + " is eating");
                    try {
                        Thread.sleep((int) (Math.random() * 1000));
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Philosopher.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    sticks.leaveSticks(name);
                    this.currentState = State.Thinking;
                    wasHungry = false;
                    break;
                case Thinking:
                    logger.info("Philosopher #" + name + " is thinking");
                    try {
                        Thread.sleep((int) (Math.random() * 2000));
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Philosopher.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    wasHungry = false;
                    hasEaten = true;
                    this.currentState = State.Thinking;
                    break;
            }
            stateLabel.setText("Philosopher #" + name + ": " + currentState);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
