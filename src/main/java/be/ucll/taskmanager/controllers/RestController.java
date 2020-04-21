package be.ucll.taskmanager.controllers;


import be.ucll.taskmanager.models.TaskDTO;
import be.ucll.taskmanager.services.TaskOverviewService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
public class RestController {

    private final TaskOverviewService taskOverviewService;

    public RestController(TaskOverviewService taskOverviewService) {
        this.taskOverviewService = taskOverviewService;
    }

    @GetMapping("/tasks")
    @ResponseBody
    public List<TaskDTO> getTasks() {
        return taskOverviewService.getTasks();
    }

    @PostMapping("/task")
    public void createNewTask(@RequestBody @Valid TaskDTO taskDTO){
        taskOverviewService.addTask(taskDTO);
    }

}


