package Application.Controler;


import Application.Presenter.EditTaskPresenter;
import Application.Resources.Container;
import Application.Resources.Warning;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class EditTaskControler {

    public TextArea descriptionID;
    public TextField titleID;
    public ComboBox priorityID;
    public DatePicker expDateID;
    private ObservableList < String > priority_list = FXCollections.observableArrayList("Mały", "Średni", "Wysoki");
    private Warning warning = new Warning();

    public EditTaskControler() {}

    @FXML
    public void initialize() {
        priorityID.setItems(priority_list);
        titleID.setText(EditTaskPresenter.getElement().getTitle());
        descriptionID.setText(EditTaskPresenter.getElement().getDescription());
        priorityID.setValue(EditTaskPresenter.getElement().getPriority());
        expDateID.setValue(EditTaskPresenter.getElement().getDate());
    }

    @FXML
    public void handleEditButtonID() {
        try {
            if (titleID.getText().equals("") ||
                    priorityID.getSelectionModel().isEmpty() ||
                    expDateID.getValue() == null) {
                throw  new NullPointerException();
            }
            else {
                String title = titleID.getText();
                int d = (expDateID.getValue().getDayOfMonth());
                int m = (expDateID.getValue().getMonthValue());
                int y = (expDateID.getValue().getYear());
                String priority = (String) priorityID.getValue();
                String description = descriptionID.getText();

                EditTaskPresenter.add(new Container(title, description, priority, y, m, d), EditTaskPresenter.getIndex());
                EditTaskPresenter.closeStage();
            }

        } catch (NullPointerException e) {
            warning.showErrorAlert();
        }
    }
}
