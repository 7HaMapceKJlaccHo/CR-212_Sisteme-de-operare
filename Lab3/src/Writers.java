import java.util.ArrayList;
import java.util.Arrays;

public class Writers extends Thread{
    String name;
    ArrayList<String> bookList = new ArrayList<>(Arrays.asList("Colea's life ", "Ne vedem si la anu", "Jizni studenta", "ION", "Enigma Grupei CR-212", "Cei trei studenti"));

    ArrayList<String> library = Library.list;
    ArrayList<String> writtenBooks = new ArrayList<>();
    static int count = 0;

    public Writers (String name) {
        this.name = name;
    }

    @Override
    public void run() {

        while(library.size() < Library.books){
            try {
                if (!Library.READ_WRITE_LOCK.isWriteLocked()){
                    Main.writerTextArea.append(name + " este in biblioteca.\n");
                }
                Library.READ_WRITE_LOCK.writeLock().lock();
                if (library.size() < Library.books){
                    String randomBook = bookList.get(count);
                    if (!library.contains(randomBook)){
                        sleep(1000);
                        library.add(randomBook);
                        writtenBooks.add(randomBook);

                        Main.writerTextArea.append(name + " a scris " + randomBook+"\n");
                        count++;
                        sleep(100);
                    }
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }
            finally {
                Library.READ_WRITE_LOCK.writeLock().unlock();
            }
            if (library.size() == Library.books){
                Main.writerTextArea.append(name + " lista de carti: \n" + writtenBooks + "\n");


            }
        }

    }

}
