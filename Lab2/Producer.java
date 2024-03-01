class Producer extends Thread {
    private final Depozit depozit;

    public Producer(Depozit depozit, String name) {
        super(name);
        this.depozit = depozit;
    }

    @Override
    public void run() {
        depozit.produce();
    }
}