import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LibraryTest {

    @Test
    public void testReadWriteLock() {
        Library library = new Library(3, 4, 10);

        assertTrue(library.READ_WRITE_LOCK.readLock().tryLock());
        assertFalse(library.READ_WRITE_LOCK.writeLock().tryLock());


        library.READ_WRITE_LOCK.readLock().unlock();
        assertTrue(library.READ_WRITE_LOCK.writeLock().tryLock());

    }

}