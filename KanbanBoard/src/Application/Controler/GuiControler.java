package Application.Controler;

import Application.Presenter.AddTaskPresenter;
import Application.Presenter.EditTaskPresenter;
import Application.Resources.Container;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Callback;
import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;


public class GuiControler implements Initializable
{
    private EditTaskPresenter editTaskPresenter;
    private AddTaskPresenter addTaskPresenter;

    public ObservableList<Container> toDoList = FXCollections.observableArrayList();
    public ObservableList<Container> inProgressList = FXCollections.observableArrayList();
    public ObservableList<Container> doneList = FXCollections.observableArrayList();

    public ListView<Container> toDoID = new ListView<>(toDoList);
    public ListView<Container> inProgressID = new ListView<>(inProgressList);
    public ListView<Container> doneID = new ListView<>(doneList);

    public MenuItem CloseID;
    public Menu AboutID;

    public GuiControler() { }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Label about = new Label("About");
        about.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                AuthorMessage();
            }
        });

        AboutID.setGraphic(about);
        toDoID.setItems(toDoList);
        inProgressID.setItems(inProgressList);
        doneID.setItems(doneList);

        toDoID.setOnKeyPressed(event -> {
                    switch (event.getCode()) {
                        case RIGHT:
                            if (!toDoID.getItems().isEmpty()) {
                                inProgressID.getItems().add(toDoID.getItems().get(toDoID.getFocusModel().getFocusedIndex()));
                                toDoID.getItems().remove(toDoID.getItems().get(toDoID.getFocusModel().getFocusedIndex()));
                            }
                            break;
                    }
                }
        );

        inProgressID.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case RIGHT:
                    if (!inProgressID.getItems().isEmpty()) {
                        doneID.getItems().add(inProgressID.getItems().get(inProgressID.getFocusModel().getFocusedIndex()));
                        inProgressID.getItems().remove(inProgressID.getItems().get(inProgressID.getFocusModel().getFocusedIndex()));
                    }
                    break;
                case LEFT:
                    if (!inProgressID.getItems().isEmpty()) {
                        toDoID.getItems().add(inProgressID.getItems().get(inProgressID.getFocusModel().getFocusedIndex()));
                        inProgressID.getItems().remove(inProgressID.getItems().get(inProgressID.getFocusModel().getFocusedIndex()));
                    }
                    break;
            }
        });

        doneID.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case LEFT:
                    if (!doneID.getItems().isEmpty()) {
                        inProgressID.getItems().add(doneID.getItems().get(doneID.getFocusModel().getFocusedIndex()));
                        doneID.getItems().remove(doneID.getItems().get(doneID.getFocusModel().getFocusedIndex()));
                    }
                    break;
            }
        });

        toDoID.setCellFactory(new Callback<ListView<Container>, ListCell<Container>>() {
            @Override public ListCell<Container> call(ListView<Container> list) {
                return new ColorRectCell();
            }
        });
        inProgressID.setCellFactory(new Callback<ListView<Container>,ListCell<Container>>() {
            @Override
            public ListCell<Container> call(ListView<Container> list) {
                return new ColorRectCell();
            }}
        );
        doneID.setCellFactory(new Callback<ListView<Container>,ListCell<Container>>() {
            @Override
            public ListCell<Container> call(ListView<Container> list) {
                return new ColorRectCell();
            }}
        );

    }

    public void setEditTaskPresenter(EditTaskPresenter editTaskPresenter){
        this.editTaskPresenter = editTaskPresenter;
    }

    public void setAddTaskPresenter(AddTaskPresenter addTaskPresenter) {
        this.addTaskPresenter = addTaskPresenter; }


    public void AuthorMessage() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About author");
        alert.setHeaderText("Author : Patryk Kurzeja");
        alert.showAndWait();
    }

    @FXML
    public void handlenewTaskButton(){addTaskPresenter.show();}

    @FXML
    public void handleCloseID(ActionEvent actionEvent) {
        System.exit(0);
    }


    public void handleDeleteFirst(ActionEvent actionEvent) {
        if (toDoID.getSelectionModel().getSelectedIndex() >= 0) {
            int index = toDoID.getSelectionModel().getSelectedIndex();
            toDoID.getItems().remove(index);
        }
    }

    public void handleEditFirst(ActionEvent actionEvent) {
        if (toDoID.getSelectionModel().getSelectedIndex() >= 0) {
            int index = toDoID.getSelectionModel().getSelectedIndex();
            editTaskPresenter.show(toDoID.getItems().get(index), index);
            editTaskPresenter.listChooser = 1;
        }
    }

    public void handleDeleteSecond(ActionEvent actionEvent) {
        if (inProgressID.getSelectionModel().getSelectedIndex() >= 0) {
            int index = inProgressID.getSelectionModel().getSelectedIndex();
            inProgressID.getItems().remove(index);
        }
    }

    public void handleEditSecond(ActionEvent actionEvent) {
        if (inProgressID.getSelectionModel().getSelectedIndex() >= 0) {
            int index = inProgressID.getSelectionModel().getSelectedIndex();
            editTaskPresenter.show(inProgressID.getItems().get(index), index);
            editTaskPresenter.listChooser = 2;
        }
    }

    public void handleDeleteThird(ActionEvent actionEvent) {
        if (doneID.getSelectionModel().getSelectedIndex() >= 0) {
            int index = doneID.getSelectionModel().getSelectedIndex();
            doneID.getItems().remove(index);
        }
    }

    public void handleEditThird(ActionEvent actionEvent) {
        if (doneID.getSelectionModel().getSelectedIndex() >= 0) {
            int index = doneID.getSelectionModel().getSelectedIndex();
            editTaskPresenter.show(doneID.getItems().get(index), index);
            editTaskPresenter.listChooser = 3;
        }
    }

    static class ColorRectCell extends ListCell<Container> {
        private Circle getCircle(){
            Circle rect = new Circle(10);
            rect.setFill(Color.web(getItem().getPriorityColor()));
            return rect;
        }

        @Override
        public void updateItem(Container item, boolean empty) {
            super.updateItem(item, empty);

            if (empty) {
                setText(null);
                setGraphic(null);
                setTooltip(null);
            }
            else{
                setText(getItem().getTitle());
                setGraphic(getCircle());
                Tooltip tooltip = new Tooltip();
                tooltip.setText(getItem().getDescription());
                setTooltip(tooltip);
            }
        }
    }

}

