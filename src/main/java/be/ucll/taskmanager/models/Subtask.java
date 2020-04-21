package be.ucll.taskmanager.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Entity
public class Subtask {

    @Id
    @GeneratedValue
    private Long DbID;

    @NotEmpty
    private String title;
    private String description;

    public Subtask(){}

    public Subtask(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Long getDbID() {
        return DbID;
    }

    public void setDbID(Long dbID) {
        DbID = dbID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
