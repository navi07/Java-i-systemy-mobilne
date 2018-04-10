package Application.Presenter;

import Application.Resources.Container;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EditTaskPresenterTest {

    Container container = new Container("new", "description","Low", 2018,4,9);
    int index = 1;

    EditTaskPresenter editTaskPresenter = new EditTaskPresenter();

    @Test
    void show() {
        /** ??? GUI TESTING ??? **/
    }

    @Test
    void getElement() {
        EditTaskPresenter.setElement(container);
        assertEquals(EditTaskPresenter.getElement(), container);
    }

    @Test
    void getIndex() {
        EditTaskPresenter.setIndex(index);
        assertEquals(EditTaskPresenter.getIndex(), 1);
    }

    @Test
    void add() {
      assertEquals(EditTaskPresenter.add(container, index), 0);
    }

    @Test
    void closeStage() {
        /** ??? GUI TESTING ??? **/
    }
}