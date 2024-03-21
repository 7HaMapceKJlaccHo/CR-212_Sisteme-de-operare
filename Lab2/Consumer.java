import static java.lang.Math.random;
class Consumer extends Thread {
    private final Depozit depozit;

    public Consumer(Depozit depozit, String name) {
        super(name);
        this.depozit = depozit;
    }

    @Override
    public synchronized void run(){

        int numbers_eat = 19;
        int number_1, number_2;

        try{
            sleep((int)(Math.random()*100));
        }catch(InterruptedException e){
            System.out.println(e);
        }

        for ( int i=0; i < numbers_eat ; i +=2 ){
            number_1 =  depozit.get(name);
            number_2 = depozit.get(name);


             if(i == numbers_eat -2) {
                 System.out.println("\\n" + depozit.name + "a consumat " + numbers_eat + "elemente" + number_1 + "  " + number_2);
             }

        }



        ;
    }
}