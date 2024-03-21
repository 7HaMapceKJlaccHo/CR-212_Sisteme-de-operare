import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

public class ReadersTest {
    @Test
    public void testReaderInitialization() {
        Readers reader = new Readers("TestReader");
        assertNotNull(reader);
        assertEquals("TestReader", reader.name);
        assertEquals(0, reader.readBooks.size());
        assertNotNull(reader.library);
    }




    @Test
    public void testReaderAddingBooks() {
        Readers reader = new Readers("TestReader");
        reader.readBooks.add("Book1");
        reader.readBooks.add("Book2");
        assertEquals(2, reader.readBooks.size());
        assertTrue(reader.readBooks.contains("Book1"));
        assertTrue(reader.readBooks.contains("Book2"));
    }

    @Test
    public void testReaderBooks() {
        Readers reader = new Readers("TestReader");
        reader.readBooks.add("Book1");
        reader.readBooks.add("Book2");
        assertTrue(reader.readBooks.contains("Book1"));
        assertTrue(reader.readBooks.contains("Book2"));
    }

    @Test
    public void testReaderNameChange() {
        Readers reader = new Readers("OldName");
        assertEquals("OldName", reader.name);
        reader.name = "NewName";
        assertEquals("NewName", reader.name);
    }


}