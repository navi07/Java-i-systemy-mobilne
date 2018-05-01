package Application.Resources;

import java.io.Serializable;
import java.time.LocalDate;

public class Container implements Serializable {

    private String title;
    private String description;
    private LocalDate date = LocalDate.now();
    private String priority;
    private String priorityColor;
    private Type type;

    public Container(String title, String description, String priority, int y, int m, int d) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.type = Type.toDo;
        date = LocalDate.of(y, m, d);

        if(priority.equals("Mały"))
            priorityColor ="green";
        else if(priority.equals("Średni"))
            priorityColor ="yellow";
        else if(priority.equals("Wysoki"))
            priorityColor ="red";
    }

    @Override
    public String toString() {
        return  (title + ", " +
                description + ", " +
                priority + ", " +
                date.getYear() + ", " +
                date.getMonthValue() + ", " +
                date.getDayOfMonth() + ", " +
                type);
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

    public int getYear() {
        return date.getYear();
    }

    public int getMonth(){
        return date.getMonthValue();
    }

    public int getDay(){
        return date.getDayOfMonth();
    }
    public void setType(Type type) {
        this.type = type;
    }

    public Type getType() {
        return type;
    }
}
