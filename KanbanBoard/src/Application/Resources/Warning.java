package Application.Resources;

import javafx.scene.control.Alert;

public class Warning {
    public void showErrorAlert(){
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
        alert.setTitle("Wystąpił błąd");
        alert.setHeaderText("Wprowadź wszystkie dane !");
        alert.show();
    }

    public void showFileNotFoundAlert(){
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
        alert.setTitle("Wystąpił błąd");
        alert.setHeaderText("Nie znaleziono pliku z danymi !");
        alert.show();
    }

    public void showLoadErrorAlert(){
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
        alert.setTitle("Wystąpił błąd");
        alert.setHeaderText("Nie można wczytać danych !");
        alert.show();
    }

    public void showSaveErrorAlert(){
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
        alert.setTitle("Wystąpił błąd");
        alert.setHeaderText("Nie można zapisać danych !");
        alert.show();
    }

    public void showAuthorMessage(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("O autorze");
        alert.setHeaderText("Autor : Patryk Kurzeja");
        alert.showAndWait();
    }
}
