package Application.Resources;

import javafx.scene.control.Alert;

public class Warning {
    public void showErrorAlert(){
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Input all data !");
        alert.show();
    }

    public void showFileNotFoundAlert(){
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("File not found !");
        alert.show();
    }

    public void showLoadErrorAlert(){
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Cannot load data !");
        alert.show();
    }

    public void showSaveErrorAlert(){
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Cannot save data !");
        alert.show();
    }

    public void showAuthorMessage(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About author");
        alert.setHeaderText("Author : Patryk Kurzeja");
        alert.showAndWait();
    }
}
