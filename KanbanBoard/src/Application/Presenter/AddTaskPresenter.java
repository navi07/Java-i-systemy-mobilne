package Application.Presenter;

import Application.Resources.Container;
import javafx.beans.property.ListProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;


public class AddTaskPresenter {
    public static Stage primaryStage;

    public static ObservableList < Container > toDO;
    public static ObservableList < Container > inProgress;
    public static ObservableList < Container > done;
    public static ListProperty < Container > listProperty;
    public static ListView < Container > toDoID;


    public AddTaskPresenter(ObservableList < Container > toDO, ListView < Container > toDoID) {
        this.primaryStage = new Stage();
        this.toDO = toDO;
        this.toDoID = toDoID;
    }

    public void show() {
        try {
            // load layout from FXML file
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(this.getClass().getClassLoader().getResource("Application/Resources/AddTask.fxml"));
            Parent rootLayout = loader.load();

            // add layout to a scene and show them all
            Scene scene = new Scene(rootLayout);
            primaryStage.setTitle("Add new task");
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
        toDO.add(element);


    }


}