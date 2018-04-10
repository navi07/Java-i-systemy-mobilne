package Application.Presenter;

import Application.Resources.Container;
import javafx.beans.property.ListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddTaskPresenterTest {

    Container container = new Container("new", "description","Low", 2018,4,9);

    public ObservableList<Container> toDO  = FXCollections.observableArrayList();

    @Test
    void show() {
        /** ??? GUI TESTING ??? **/
    }

    @Test
    void closeStage() {
        /** ??? GUI TESTING ??? **/
    }

    @Test
    void add() {
        toDO.add(container);
        assertTrue(toDO.contains(container));
    }
}