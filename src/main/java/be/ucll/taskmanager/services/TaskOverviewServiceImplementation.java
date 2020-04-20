package be.ucll.taskmanager.services;

import be.ucll.taskmanager.models.Subtask;
import be.ucll.taskmanager.models.SubtaskDTO;
import be.ucll.taskmanager.models.Task;
import be.ucll.taskmanager.models.TaskDTO;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskOverviewServiceImplementation implements TaskOverviewService {

    private final TaskRepository repository;

    public TaskOverviewServiceImplementation(TaskRepository repository){
        this.repository = repository;
    }

    @Override
    public List<TaskDTO> getTasks() {
        return repository.findAll().stream().map(h-> {
            TaskDTO dto = new TaskDTO();
            dto.setTitle(h.getTitle());
            dto.setDescription(h.getDescription());
            dto.setDueDate(h.getDueDate());
            dto.setSubtasks(h.getSubtasks());
            dto.setId(h.getId());
            return dto;
        }).collect(Collectors.toList()); //vroeger; return heads
    }

    @Override
    public TaskDTO getSelectedTask(long id) {
        TaskDTO task = null;

        for(TaskDTO t : getTasks()){ //was service.findAll() enz...
            if (t.getId() == id){
                task = t;
                task.setTitle(t.getTitle());
                task.setDescription(t.getDescription());
                task.setDueDate(t.getDueDate());
                task.setSubtasks(t.getSubtasks());
                task.setId(t.getId());
            }
        }
        return task;

        //NICK: this.repository.getOne(id)
    }

    @Override
    public void addTask(TaskDTO taskDTO)
    {
        Task task1 = new Task();
        List<Task> tasks = repository.findAll();
        long idlasttask;

        if(tasks.size() != 0){
            Task laatsteTask = tasks.get(tasks.size()-1);
            idlasttask = laatsteTask.getId();
            taskDTO.setId(idlasttask+1);

            //tasks.add(task);
            task1.setTitle(taskDTO.getTitle());
            task1.setDescription(taskDTO.getDescription());
            task1.setDueDate(taskDTO.getDueDate());
            task1.setSubtasks(taskDTO.getSubtasks());
            task1.setId(taskDTO.getId());
            repository.save(task1);
        }else{
            idlasttask = 1;
            taskDTO.setId(idlasttask);

            //tasks.add(task);
            task1.setTitle(taskDTO.getTitle());
            task1.setDescription(taskDTO.getDescription());
            task1.setDueDate(taskDTO.getDueDate());
            task1.setSubtasks(taskDTO.getSubtasks());
            task1.setId(taskDTO.getId());
            repository.save(task1);
        }
    }

    @Override
    public List<SubtaskDTO> getSubtasks() {
        return repository.findAll().stream().map(h-> {
            SubtaskDTO dto = new SubtaskDTO();
            dto.setTitle(h.getTitle());
            dto.setDescription(h.getDescription());
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public void addSubtask(long id, SubtaskDTO subtaskDTO) {

        Task task = new Task();
        task = repository.getOne(id);

        Subtask subtask = new Subtask();
        subtask.setTitle(subtaskDTO.getTitle());
        subtask.setDescription(subtaskDTO.getDescription());
        task.addSubtask(subtask);
        repository.save(task);

    }

    @Override
    public void editTask(long id, TaskDTO task) {
        Task e = repository.getOne(id);
        e.setTitle(task.getTitle());
        e.setDescription(task.getDescription());
        e.setDueDate(task.getDueDate());
        repository.save(e);
    }

}
