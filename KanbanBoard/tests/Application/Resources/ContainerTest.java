package Application.Resources;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ContainerTest {
    private LocalDate date = LocalDate.now();


    @Test
    void  getPriorityColor() {
        Container container = new Container("new", "description","Low", 2018,4,9);
        assertEquals (container.getPriorityColor(), "green");
    }

    @Test
    void getTitle() {
        Container container = new Container("new", "description","Low", 2018,4,9);
        assertEquals (container.getTitle(), "new");
    }

    @Test
    void getDescription() {
        Container container = new Container("new", "description","Low", 2018,4,9);
        assertEquals (container.getDescription(), "description");
    }

    @Test
    void getDate() {
        Container container = new Container("new", "description","Low", 2018,4,9);
        date = LocalDate.of(2018, 4, 9);
        assertEquals (container.getDate(), date);
    }

    @Test
    void getPriority() {
        Container container = new Container("new", "description","Low", 2018,4,9);
        assertEquals (container.getPriority(), "Low");
    }
}