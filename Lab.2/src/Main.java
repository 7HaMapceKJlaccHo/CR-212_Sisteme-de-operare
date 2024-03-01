public class Main {
    public static void main(String[] args) {
        Depozit depozit = new Depozit();
        Producator producator1 = new Producator(depozit, "Producator1");
        Producator producator2 = new Producator(depozit, "Producator2");
        Producator producator3 = new Producator(depozit, "Producator3");
        Consumator consumator1 = new Consumator(depozit, "Consumator1");
        Consumator consumator2 = new Consumator(depozit, "Consumator2");

        producator1.start();
        producator2.start();
        producator3.start();
        consumator1.start();
        consumator2.start();

        try {
            producator1.join();
            producator2.join();
            producator3.join();
            consumator1.join();
            consumator2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Producatorii au produs in total " + Producator.getTotalObiecteProduse() + " obiecte.");
        System.out.println("Programul s-a incheiat.");
    }
}
