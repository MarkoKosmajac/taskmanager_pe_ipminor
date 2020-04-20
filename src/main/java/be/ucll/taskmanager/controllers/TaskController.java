package be.ucll.taskmanager.controllers;

import be.ucll.taskmanager.models.SubtaskDTO;
import be.ucll.taskmanager.models.TaskDTO;
import be.ucll.taskmanager.services.TaskOverviewService;
import be.ucll.taskmanager.services.TaskOverviewServiceImplementation;
import be.ucll.taskmanager.services.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private final TaskOverviewService service;

    public TaskController(TaskRepository repository){
        service = new TaskOverviewServiceImplementation(repository);
    }

    @GetMapping
    public String getTasks(Model model)
    {
        model.addAttribute("tasks",service.getTasks());
        return "tasks";
    }

    @GetMapping("/new")
    public String getCreateForm()
    {
        //model.addAttribute("head", new Head());???
        return "createTask";
    }

    @PostMapping("/new")
    public String postNewTask(@ModelAttribute TaskDTO task)
    {
        service.addTask(task);
        System.out.println("maked: " + task.getId());
        return "redirect:/tasks";
    }

    @GetMapping("/{id}")
    public String getId(@PathVariable(name = "id") long id, Model model)
    {
        model.addAttribute("id",id);
        model.addAttribute("getSelectedTask",service.getSelectedTask(id));
        return "taskdetail";
    }

    @GetMapping("/edit/{id}")
    public String getEditForm(@PathVariable(name = "id") long id, Model model)
    {
        model.addAttribute("id",id);
        model.addAttribute("getSelectedTask",service.getSelectedTask(id));
        return "editTask";
    }

    @PostMapping("/edit/{id}")
    public String editTask(@PathVariable(name = "id") long id, @ModelAttribute TaskDTO taskDTO)
    {
        this.service.editTask(id, taskDTO);
        return "redirect:/tasks/{id}";
    }

    @GetMapping("/{id}/sub/create")
    public String getNewSubtaskForm(@PathVariable(name = "id") long id, Model model)
    {
        //model.addAttribute("id",id);
        model.addAttribute("getSelectedTask",service.getSelectedTask(id));
        model.addAttribute("subtask", new SubtaskDTO());
        return "createSubtask";
    }

    @PostMapping("/{id}/sub/create")
    public String makeNewSubtask(@PathVariable(name = "id") long id, @ModelAttribute SubtaskDTO subtaskDTO, Model model)
    {
        this.service.addSubtask(id, subtaskDTO);
        return "redirect:/tasks/{id}";
    }

}
