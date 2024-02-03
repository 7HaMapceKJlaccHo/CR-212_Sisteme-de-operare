
import java.util.Random;
class Calc{
    int t_time;
    float rez;
    
    int adunare(int a, int b){
        return a+b;
    }
    
    int scadere(int a, int b){
        return a-b;
    }
    
    int inmultire(int a, int b){
        return a*b;
    }
    
    int impartire(int a, int b){
        return a/b;
    }
    
    public Calc(){
        
    }
    
}

// Clasa anonima este o subclasa care permite executaia direct in mainul la obiectu; 
public class  Main

{
    public static void main (String[] args){
    Random rand = new Random();
    
    int a = rand.nextInt(1000);
    int b = rand.nextInt(1000);


      
    // Crearea obiectelor clasei 
    Calc calc =  new Calc();
    int suma = calc.adunare(a, b);
    float imp = calc.impartire(a, b);
    System.out.println("Suma este: " + suma);
}

  
 
}