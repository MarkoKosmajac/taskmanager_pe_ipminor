package be.ucll.taskmanager;

import be.ucll.taskmanager.models.Subtask;
import be.ucll.taskmanager.models.SubtaskDTO;
import be.ucll.taskmanager.models.TaskDTO;
import be.ucll.taskmanager.services.TaskOverviewService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TaskOverviewServiceTest {

    @Autowired
    private TaskOverviewService taskOverviewService;

    @Test
    public void testGetTasks() {

        // setup
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setTitle("Koken");
        taskDTO.setDescription("Avondeten voorbereiden voor 4 personen.");
        taskDTO.setDueDate(LocalDateTime.of(2020, 3, 10, 10, 0));
        List<Subtask> subtasks = new ArrayList<>();
        subtasks.add(new Subtask("title","Description"));
        taskDTO.setSubtasks(subtasks);
        taskOverviewService.addTask(taskDTO);

        // method to be tested
        List<TaskDTO> tasks = taskOverviewService.getTasks();

        // checks
        assertNotNull(tasks); //test of de list niet NULL is
        assertFalse(tasks.isEmpty()); //test of de list niet EMPTY is
        assertEquals(1, tasks.size()); //test of de size stipt 1 is, want maar 1 ding toegevoegd
        TaskDTO task = tasks.get(0); //maak een nieuwe task en zet de vorige hierin
        assertNotNull(task); //test of de task niet NULL is
    }

    @Test
    public void testAddTask() {

        // setup
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setTitle("Afwas doen");
        taskDTO.setDescription("2x keer per dag!");
        taskDTO.setDueDate(LocalDateTime.of(2020, 3, 10, 10, 0));
        List<Subtask> subtasks = new ArrayList<>();
        subtasks.add(new Subtask("title","Description"));
        taskDTO.setSubtasks(subtasks);

        // method to be tested
        taskOverviewService.addTask(taskDTO);

        //helper methods
        List<TaskDTO> tasks = taskOverviewService.getTasks();
        TaskDTO addedTask = tasks.get(1);

        // checks
        assertNotNull(addedTask);
        assertEquals(addedTask.getTitle(),"Afwas doen");
        assertEquals(addedTask.getDescription(),"2x keer per dag!");
        assertEquals(addedTask.getDueDate(),LocalDateTime.of(2020, 3, 10, 10, 0));
        //assertEquals(addedTask.getSubtasks().get(1).getTitle(),"title"); //TODO: THROWS ERROR - CHANGE SUBTASKS IN TASKDTO TO SUBTASKDTO ??? --> lazy initialization error
    }

    @Test
    public void testGetSelectedTask() {

        //method to be tested
        TaskDTO taskDTO = taskOverviewService.getSelectedTask(1); //==> Geeft diegene met index 0!

        // checks
        assertNotNull(taskDTO);
        assertEquals(taskDTO.getTitle(),"Koken");
        assertEquals(taskDTO.getDescription(),"Avondeten voorbereiden voor 4 personen.");
        assertEquals(taskDTO.getDueDate(),LocalDateTime.of(2020, 3, 10, 10, 0));

    }

    @Transactional
    @Test
    public void testEditTask() {

        TaskDTO task = new TaskDTO();
        task.setTitle("Afwas doen");
        task.setDescription("2x keer per dag!");
        task.setDueDate(LocalDateTime.of(2020, 3, 10, 10, 0));
        taskOverviewService.addTask(task);

        //MEEGEGEVEN PARAMETERS
        //1e param: ´long id´ = de id van de task die ge-edit moet worden.
        //2e param: een DTO met nieuwe waardes (volwaardige DTO), bevat enkel titel,desc,en duedate, probeer ook eens met full alles...

        for(TaskDTO t : taskOverviewService.getTasks()){
            System.out.println("TASK w id: " + t.getId());
        }

        TaskDTO newtask = new TaskDTO();
        newtask.setTitle("ee");
        newtask.setDescription("eee");
        newtask.setDueDate(LocalDateTime.of(2020, 3, 10, 10, 0));
        taskOverviewService.editTask(1,newtask);

        //checks
        assertEquals("ee",taskOverviewService.getTasks().get(0).getTitle());

    }

    @Transactional
    @Test
    public void testGetSubtasks() {

        //List<SubtaskDTO> getSubtasks();

        // setup
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setTitle("555");
        taskDTO.setDescription("444");
        taskDTO.setDueDate(LocalDateTime.of(2020, 3, 10, 10, 0));
        List<Subtask> subtasks = new ArrayList<>();
        /*subtasks.add(new Subtask("title","Description"));
        subtasks.add(new Subtask("title2","Description2"));
        subtasks.add(new Subtask("title3","Description3"));
        subtasks.add(new Subtask("title4","Description4"));
        subtasks.add(new Subtask("title5","Description5"));*/
        taskDTO.setSubtasks(subtasks);
        taskOverviewService.addTask(taskDTO);

        // method to be tested
        List<SubtaskDTO> subtaskDTOList = taskOverviewService.getSubtasks();


        //assertNull(subtaskDTOList);
        assertFalse(subtaskDTOList.isEmpty());

        /*List<Subtask> subtasks1 = new ArrayList<>();
        //TODO/ lijntje hieronder gooit instansutiionlazy error
        for(TaskDTO t : taskOverviewService.getTasks()){
            subtasks1.addAll(t.getSubtasks());
        }
        */

        // checks
        /*assertNotNull(subtasks1); //test of de list niet NULL is
        assertFalse(subtasks1.isEmpty()); //test of de list niet EMPTY is
        assertEquals(7, subtasks1.size());*/
        //SubtaskDTO subtask = subtasks1.get(0);
        //assertNotNull(subtask); //test of de task niet NULL is

    }

    @Test
    @Transactional
    public void testAddSubtask() {
        // setup
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setTitle("Afwas doen");
        taskDTO.setDescription("2x keer per dag!");
        taskDTO.setDueDate(LocalDateTime.of(2020, 3, 10, 10, 0));
        SubtaskDTO subtask = new SubtaskDTO("title","Description");

        System.out.println(taskDTO.getId()); //=0
        System.out.println("Number of subtasks: " + taskOverviewService.getSubtasks().size()); // =2

        // method to be tested
        taskOverviewService.addSubtask(2,subtask);

        System.out.println("Number of subtasks: " + taskOverviewService.getSubtasks().size());

        // checks
        assertEquals(taskOverviewService.getTasks().get(1).getSubtasks().get(0).getTitle(),"title");
    }




}
