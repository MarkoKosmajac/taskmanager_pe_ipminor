package be.ucll.taskmanager.services;

import be.ucll.taskmanager.models.SubtaskDTO;
import be.ucll.taskmanager.models.TaskDTO;

import java.util.List;

public interface TaskOverviewService {
    TaskDTO getSelectedTask(long id);

    List<TaskDTO> getTasks();
    void addTask(TaskDTO task);

    List<SubtaskDTO> getSubtasks();
    void addSubtask(long id, SubtaskDTO subtask);

    void editTask(long id, TaskDTO task);
}
