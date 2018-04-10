package Application.Controler;

import Application.Presenter.AddTaskPresenter;
import Application.Presenter.EditTaskPresenter;
import Application.Resources.Container;
import javafx.beans.property.ListProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GuiControlerTest {

    private EditTaskPresenter editTaskPresenter;
    private AddTaskPresenter addTaskPresenter;

    @Test
    void initialize() {
        /** ??? GUI TESTING ??? **/
    }

    @Test
    void setEditTaskPresenter() {
        this.editTaskPresenter = new EditTaskPresenter();
        assertNotNull(editTaskPresenter);
    }

    @Test
    void setAddTaskPresenter() {
        this.addTaskPresenter = new AddTaskPresenter();
        assertNotNull(addTaskPresenter);
    }

    @Test
    void authorMessage() {
        /** ??? GUI TESTING ??? **/
    }

    @Test
    void handlenewTaskButton() {
        /** ??? GUI TESTING ??? **/
    }

    @Test
    void handleCloseID() {
        /** ??? GUI TESTING ??? **/
    }

    @Test
    void handleDeleteFirst() {
        /** ??? GUI TESTING ??? **/
    }

    @Test
    void handleEditFirst() {
        /** ??? GUI TESTING ??? **/
    }

    @Test
    void handleDeleteSecond() {
        /** ??? GUI TESTING ??? **/
    }

    @Test
    void handleEditSecond() {
        /** ??? GUI TESTING ??? **/
    }

    @Test
    void handleDeleteThird() {
        /** ??? GUI TESTING ??? **/
    }

    @Test
    void handleEditThird() {
        /** ??? GUI TESTING ??? **/
    }
}