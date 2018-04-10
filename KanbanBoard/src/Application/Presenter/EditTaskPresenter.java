package Application.Presenter;

import Application.Resources.Container;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import java.io.IOException;

public class EditTaskPresenter {
    public static Stage primaryStage;
    public static int listChooser;
    static Container element;
    static int index;

    public static ObservableList < Container > toDO;
    public static ObservableList < Container > inProgress;
    public static ObservableList < Container > done;
    public static ListView < Container > toDoID;
    public static ListView < Container > inProgressID;
    public static ListView < Container > doneID;

    public EditTaskPresenter(ObservableList < Container > toDO, ListView < Container > toDoID,
                             ObservableList < Container > inProgress, ListView < Container > inProgressID,
                             ObservableList < Container > done, ListView < Container > doneID) {
        this.primaryStage = new Stage();
        this.toDoID = toDoID;
        this.toDO = toDO;
        this.inProgress = inProgress;
        this.inProgressID = inProgressID;
        this.done = done;
        this.doneID = doneID;
    }

    public EditTaskPresenter() {

    }

    public void show(Container element, int index) {
        this.element = element;
        this.index = index;
        try {

            // load layout from FXML file
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(this.getClass().getClassLoader().getResource("Application/Resources/EditTask.fxml"));
            Parent rootLayout = loader.load();

            // add layout to a scene and show them all
            Scene scene = new Scene(rootLayout);
            primaryStage.setTitle("Edit task");
            primaryStage.setScene(scene);
            primaryStage.setMaximized(false);
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Container getElement() {
        return element;
    }

    public static int getIndex() {
        return index;
    }

    public static int add(Container element, int index) {
        if (listChooser == 1) {
            toDO.remove(index);
            toDO.add(element);
            return 1;
        } else if (listChooser == 2) {
            inProgress.remove(index);
            inProgress.add(element);
            return 1;
        } else if (listChooser == 3) {
            done.remove(index);
            done.add(element);
            return 1;
        }
        else return 0;
    }
    public static void closeStage() {
        primaryStage.close();
    }

    public static void setIndex(int index) {
        EditTaskPresenter.index = index;
    }

    public static void setElement(Container element) {
        EditTaskPresenter.element = element;
    }

    public static void setListChooser(int listChooser) {
        EditTaskPresenter.listChooser = listChooser;
    }
}