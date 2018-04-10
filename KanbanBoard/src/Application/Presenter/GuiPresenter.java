<<<<<<< HEAD
package Application.Presenter;

import Application.Controler.GuiControler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class GuiPresenter {
    private Stage primaryStage;

    public GuiPresenter(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void init() {
        try {
            // load layout from FXML file
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(this.getClass().getClassLoader().getResource("Application/Resources/Gui.fxml"));
            AnchorPane rootLayout = loader.load();

            GuiControler guiControler = loader.getController();
            guiControler.setEditTaskPresenter(new EditTaskPresenter(guiControler.toDoList, guiControler.toDoID,
                    guiControler.inProgressList, guiControler.inProgressID,
                    guiControler.doneList, guiControler.doneID));
            guiControler.setAddTaskPresenter(new AddTaskPresenter(guiControler.toDoList, guiControler.toDoID));

            // add layout to a scene and show them all
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.setMaximized(false);
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



=======
package Application.Presenter;

import Application.Controler.GuiControler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class GuiPresenter {
    private Stage primaryStage;

    public GuiPresenter(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void init() {
        try {
            // load layout from FXML file
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(this.getClass().getClassLoader().getResource("Application/Resources/Gui.fxml"));
            AnchorPane rootLayout = loader.load();

            GuiControler guiControler = loader.getController();
            guiControler.setEditTaskPresenter(new EditTaskPresenter(guiControler.toDoList, guiControler.toDoID,
                    guiControler.inProgressList, guiControler.inProgressID,
                    guiControler.doneList, guiControler.doneID));
            guiControler.setAddTaskPresenter(new AddTaskPresenter(guiControler.toDoList, guiControler.toDoID));

            // add layout to a scene and show them all
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.setMaximized(false);
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



>>>>>>> b2973ad2250d635383f24470f8ac94847efa011d
}