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



public class AddTaskPresenter {
    private static Stage primaryStage;
    private static ObservableList < Container > toDO;
    private static ListView < Container > toDoID;

    public AddTaskPresenter() {
    }

    AddTaskPresenter(ObservableList<Container> toDO, ListView<Container> toDoID) {
        primaryStage = new Stage();
        AddTaskPresenter.toDO = toDO;
        AddTaskPresenter.toDoID = toDoID;
    }

    public void show() {
        try {
            // load layout from FXML file
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(this.getClass().getClassLoader().getResource("Application/Resources/AddTask.fxml"));
            Parent rootLayout = loader.load();

            // add layout to a scene and show them all
            Scene scene = new Scene(rootLayout);
            primaryStage.setTitle("Dodaj wpis");
            primaryStage.setScene(scene);
            primaryStage.setMaximized(false);
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void closeStage() {
        primaryStage.close();
    }

    public static void add(Container element) {
        GuiControler.toDoList.add(element);
    }
}