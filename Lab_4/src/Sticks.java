class Sticks {
    private int number;
    private State sticks[];
    public enum State { Free, inUse }
    private Lock lock = new ReentrantLock();
    private Logger logger;

    public Sticks(int number) {
        this.number = number;
        this.sticks = new State[number];
        for (int i = 0; i < number; i++) {
            sticks[i] = State.Free;
        }
        logger = Logger.getLogger("Sticks");
        logger.addHandler(new ConsoleHandler());
        logger.setLevel(Level.INFO);
        logger.info("Sticks are created!");
    }

    public synchronized boolean takeSticks (int position) {
        boolean success = false;
        State leftState;
        State rightState;
        if (position == 0) {
            leftState = sticks[number - 1];
        } else {
            leftState = sticks[position - 1];
        }
        rightState = sticks[position];
        if (leftState == State.Free && rightState == State.Free) {
            if (position == 0) {
                sticks[number - 1] = State.inUse;
            } else {
                sticks[position - 1] = State.inUse;
            }
            sticks[position] = State.inUse;
            logger.info("Philosopher #" + position + " has taken the sticks.");
            printState();
            success = true;
        }
        return success;
    }

    public synchronized void leaveSticks (int position) {
        if (position == 0) {
            sticks[number - 1] = State.Free;
        } else {
            sticks[position - 1] = State.Free;
        }
        sticks[position] = State.Free;
        logger.info("Philosopher #" + position + " has left the sticks.");
        printState();
    }

    private void printState() {
        logger.info("Current state: ");
        for (int i = 0; i < number; i++) {
            logger.info(i + ":" + sticks[i] + "  ");
        }
    }

    public State[] getStickStates() {
        return sticks;
    }
}
