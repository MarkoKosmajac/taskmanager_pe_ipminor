package be.ucll.taskmanager.controllers;

import be.ucll.taskmanager.services.TaskOverviewService;
import be.ucll.taskmanager.services.TaskOverviewServiceImplementation;
import be.ucll.taskmanager.services.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class NavHomeController {

    @Autowired
    private final TaskOverviewService service;

    public NavHomeController(TaskRepository repository){
        service = new TaskOverviewServiceImplementation(repository);
    }

    @GetMapping("/")
    public String getHomePage(Model model)
    {
        model.addAttribute("tasks",service.getTasks());
        return "tasks";
    }
}
