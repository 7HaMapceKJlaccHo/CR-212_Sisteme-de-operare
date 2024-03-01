class Consumer extends Thread {
    private final Depozit depozit;

    public Consumer(Depozit depozit, String name) {
        super(name);
        this.depozit = depozit;
    }

    @Override
    public void run() {
        depozit.consume();
    }
}