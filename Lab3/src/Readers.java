import javax.swing.*;
import java.util.ArrayList;
class Readers extends Thread {
    ArrayList<String> readBooks = new ArrayList<>();
    ArrayList<String> library = Library.list;
    String name;

    public Readers(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        while (readBooks.size() < Library.books) {
            try {
                if (Library.READ_WRITE_LOCK.isWriteLocked()) {

                    Main.textArea.append(name + " is in the library.\n");
                    Main.textArea.setCaretPosition(Main.textArea.getDocument().getLength());
                }
                Library.READ_WRITE_LOCK.readLock().lock();
                int random = (int) (Math.random() * library.size());
                if (random < library.size()) {
                    String randomBook = library.get(random);

                    if (readBooks.size() < Library.books) {
                        if (!readBooks.contains(randomBook)) {
                            sleep(300);
                            readBooks.add(randomBook);
                            // Update the JTextArea with the current status
                            Main.textArea.append(name + " is reading " + randomBook + "\n");
                            Main.textArea.setCaretPosition(Main.textArea.getDocument().getLength());
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                Library.READ_WRITE_LOCK.readLock().unlock();
            }
        }


        Main.textArea.append(name + " has finished reading: " + readBooks.size() + " books!\n" + readBooks + "\n");
        Main.textArea.setCaretPosition(Main.textArea.getDocument().getLength());


    }
}
