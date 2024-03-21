import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Producer extends Thread {
    private final Depozit depozit;



    public Producer(Depozit depozit, String name) {
        super(name);
        this.depozit = depozit;
    }



    @Override
    public void run() {

            Random rand = new Random();

            int impar = rand.nextInt(5) * 2 + 1;
            depozit.numereImpare.add(impar);
            depozit.log(impar + " ");
            impar = rand.nextInt(5) * 2 + 1;
            depozit.numereImpare.add(impar);
           depozit.log(impar + " ");


        }
    }
