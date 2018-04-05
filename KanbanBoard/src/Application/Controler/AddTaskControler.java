package Application.Controler;


import Application.Presenter.AddTaskPresenter;
import Application.Resources.Container;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;


public class AddTaskControler {
    public TextArea descriptionID;
    public TextField titleID;
    public ComboBox priorityComboBoxID;
    public DatePicker expDateID = new DatePicker(null);
    ObservableList < String > priority_list = FXCollections.observableArrayList("Low", "Medium", "High");

    public AddTaskControler() {}
    @FXML
    public void initialize() {
        priorityComboBoxID.setItems(priority_list);
    }

    @FXML
    public void handleaddButtonID() {
            try {
                if (titleID.getText().equals("") ||
                        priorityComboBoxID.getSelectionModel().isEmpty() ||
                        expDateID.getValue().equals(null)) {
                    throw  new NullPointerException();
                }
                else {
                    String title = titleID.getText();
                    int d = (expDateID.getValue().getDayOfMonth());
                    int m = (expDateID.getValue().getMonthValue());
                    int y = (expDateID.getValue().getYear());
                    String priority = (String) priorityComboBoxID.getValue();
                    String description = descriptionID.getText();

                    AddTaskPresenter.add(new Container(title, description, priority, y, m, d));
                    AddTaskPresenter.closeStage();
                }

            } catch (NullPointerException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Input all data !");
                alert.show();
            }
        }
    }


