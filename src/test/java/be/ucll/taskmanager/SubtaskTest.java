package be.ucll.taskmanager;

import be.ucll.taskmanager.models.Subtask;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class SubtaskTest {

    @Test
    public void testConstructorOne() {
        Subtask subtask = new Subtask("Titel","Description");
        assertNotNull(subtask);
    }

    @Test
    public void testGetters() {
        Subtask subtask = new Subtask("Titel","Description");

        assertNotNull(subtask);
        assertEquals(subtask.getTitle(),"Titel");
        assertEquals(subtask.getDescription(),"Description");
    }

    @Test
    public void testSetters() {
        Subtask subtask = new Subtask("Titel","Description");

        subtask.setTitle("newTitle");
        subtask.setDescription("newDescr");

        assertNotNull(subtask);
        assertEquals(subtask.getTitle(),"newTitle");
        assertEquals(subtask.getDescription(),"newDescr");
    }

}


