package Application.Presenter;

import Application.Controler.GuiControler;
import Application.Resources.Container;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;

public class EditTaskPresenter {
    private static Stage primaryStage;
    public static int listChooser;
    private static Container element;
    private static int index;

    private static ObservableList < Container > toDO;
    private static ObservableList < Container > inProgress;
    private static ObservableList < Container > done;
    private static ListView < Container > toDoID;
    private static ListView < Container > inProgressID;
    private static ListView < Container > doneID;

    public EditTaskPresenter(ObservableList < Container > toDO, ListView < Container > toDoID,
                             ObservableList < Container > inProgress, ListView < Container > inProgressID,
                             ObservableList < Container > done, ListView < Container > doneID) {
        primaryStage = new Stage();
        EditTaskPresenter.toDoID = toDoID;
        EditTaskPresenter.toDO = toDO;
        EditTaskPresenter.inProgress = inProgress;
        EditTaskPresenter.inProgressID = inProgressID;
        EditTaskPresenter.done = done;
        EditTaskPresenter.doneID = doneID;
    }

    public EditTaskPresenter() {
        this.primaryStage = new Stage();
    }

    public void show(Container element, int index) {
        EditTaskPresenter.element = element;
        EditTaskPresenter.index = index;
        try {

            // load layout from FXML file
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(this.getClass().getClassLoader().getResource("Application/Resources/EditTask.fxml"));
            Parent rootLayout = loader.load();

            // add layout to a scene and show them all
            Scene scene = new Scene(rootLayout);
            primaryStage.setTitle("Edytuj wpis");
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
            GuiControler.toDoList.remove(index);
            GuiControler.toDoList.add(element);
            return 1;
        } else if (listChooser == 2) {
            GuiControler.inProgressList.remove(index);
            GuiControler.inProgressList.add(element);
            return 1;
        } else if (listChooser == 3) {
            GuiControler.doneList.remove(index);
            GuiControler.doneList.add(element);
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
}