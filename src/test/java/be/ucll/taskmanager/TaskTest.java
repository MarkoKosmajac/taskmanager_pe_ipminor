package be.ucll.taskmanager;

import be.ucll.taskmanager.models.Subtask;
import be.ucll.taskmanager.models.Task;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TaskTest {

    @Test
    public void testConstructorOne() {
        //testing constructor 1: String title, LocalDateTime dueDate, String description
        Task task = new Task("Titel",LocalDateTime.of(2020, 3, 10, 10, 0),"Description");
        assertNotNull(task);
    }

    @Test
    public void testConstructorTwo() {
        //testing constructor 2: String title, String description
        Task task = new Task("eee","eeee");
        assertNotNull(task);
    }

    @Test
    public void testGetters() {
        Task task = new Task("Titel",LocalDateTime.of(2020, 3, 10, 10, 0),"Description");

        assertNotNull(task);
        assertEquals(task.getTitle(),"Titel");
        assertEquals(task.getDescription(),"Description");
        assertEquals(task.getDueDate(),LocalDateTime.of(2020, 3, 10, 10, 0));
        assertEquals(task.getId(),0);
    }

    @Test
    public void testSetters() {
        Task task = new Task("Titel",LocalDateTime.of(2020, 3, 10, 10, 0),"Description");
        List<Subtask> subtasks = new ArrayList<Subtask>();
        subtasks.add(new Subtask("eee","eee"));

        task.setTitle("newTitle");
        task.setDescription("newDescr");
        task.setDueDate(LocalDateTime.of(2100,1,1,1,1));
        task.setId(8);
        task.setSubtasks(subtasks);

        assertNotNull(task);
        assertEquals(task.getTitle(),"newTitle");
        assertEquals(task.getDescription(),"newDescr");
        assertEquals(task.getDueDate(),LocalDateTime.of(2100,1,1,1,1));
        assertEquals(task.getId(),8);
        assertEquals(task.getSubtasks().get(0),subtasks.get(0));
    }
    
}
