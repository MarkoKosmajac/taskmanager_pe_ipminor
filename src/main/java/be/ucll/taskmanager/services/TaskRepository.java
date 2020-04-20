package be.ucll.taskmanager.services;

import be.ucll.taskmanager.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {

}
