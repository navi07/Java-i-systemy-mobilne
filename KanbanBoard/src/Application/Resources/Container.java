package Application.Resources;

import java.time.LocalDate;

public class Container {

    private String title;
    private String description;
    private LocalDate date = LocalDate.now();
    private String priority;
    private String priorityColor;

    public Container() {
    }

    public Container(String title, String description, String priority, int y, int m, int d) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        date = LocalDate.of(y, m, d);

        if(priority.equals("Low"))
            priorityColor ="green";
        else if(priority.equals("Medium"))
            priorityColor ="yellow";
        else if(priority.equals("High"))
            priorityColor ="orange";
    }

    @Override
    public String toString() {
        return title;
    }

    public String getPriorityColor() {
        return priorityColor;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getPriority() {
        return priority;
    }





}
