package be.ucll.taskmanager.models;


import javax.validation.constraints.NotEmpty;

public class SubtaskDTO{

    @NotEmpty
    private String title;
    private String description;

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

    public SubtaskDTO(){}

    public SubtaskDTO(String title, String description) {
        this.title = title;
        this.description = description;
    }


}
