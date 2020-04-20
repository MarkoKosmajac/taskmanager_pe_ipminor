package be.ucll.taskmanager.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Task {

    @Id
    @GeneratedValue
    private Long DbID;

    private String title;
    private String description;

    @OneToMany(cascade=CascadeType.ALL)
    private List<Subtask> subtasks;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime dueDate;
    private long id = 0;
    private static int count = 0;

    public Task(String title, LocalDateTime dueDate, String description) {
        subtasks = new ArrayList<Subtask>();
        this.title = title;
        this.dueDate = dueDate;
        this.description = description;
        id = count++;
    }

    public Task(){}

    public Task(String title, String description) {
        subtasks = new ArrayList<Subtask>();
        this.title = title;
        this.description = description;
        id = count++;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public List<Subtask> getSubtasks() {
        return subtasks;
    }

    public void setSubtasks(List<Subtask> subtasks) {
        this.subtasks = subtasks;
    }

    public Long getDbID() {
        return DbID;
    }

    public void setDbID(Long dbID) {
        DbID = dbID;
    }

    public void addSubtask(Subtask subtask){
        this.subtasks.add(subtask);
    }
}
