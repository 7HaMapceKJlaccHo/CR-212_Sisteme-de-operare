import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Library {
    static int books;
    static Writers writers[];
    static Readers readers[];
    static ArrayList<String> list = new ArrayList<>();
    static final ReentrantReadWriteLock READ_WRITE_LOCK = new ReentrantReadWriteLock(true);
    public Library(int writers, int readers, int books) {
        this.writers = new Writers[writers];
        this.readers = new Readers[readers];
        Library.books = books;

        for (int i = 0; i < writers; i++) {
            this.writers[i] = new Writers("Scriitorul " + (i + 1));
        }
        for (int i = 0; i < readers; i++) {
            this.readers[i] = new Readers("Cititorul " + (i + 1));
        }
    }
    public void start() {
        for (Writers writer: this.writers){
            writer.start();
        }
        for (Readers reader: this.readers){
            reader.start();
        }
    }
}

