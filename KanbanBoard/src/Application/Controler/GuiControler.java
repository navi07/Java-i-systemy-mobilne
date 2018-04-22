package Application.Controler;

import Application.Presenter.AddTaskPresenter;
import Application.Presenter.EditTaskPresenter;
import Application.Resources.Container;
import Application.Resources.Serializer;
import Application.Resources.Warning;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class GuiControler implements Initializable, Serializable
{
    public AnchorPane anchorPaneGlobalID;
    public AnchorPane anchorPaneMainID;
    private EditTaskPresenter editTaskPresenter;
    private AddTaskPresenter addTaskPresenter;

    public static ObservableList<Container> toDoList = FXCollections.observableArrayList();
    public static ObservableList<Container> inProgressList = FXCollections.observableArrayList();
    public static ObservableList<Container> doneList = FXCollections.observableArrayList();

    public ListView<Container> toDoID = new ListView<>(toDoList);
    public ListView<Container> inProgressID = new ListView<>(inProgressList);
    public ListView<Container> doneID = new ListView<>(doneList);

    public MenuItem closeID;
    public MenuItem saveToFileID;
    public MenuItem loadFromFileID;
    public MenuItem exportToFileID;
    public MenuItem importFromFileID;
    public Menu AboutID;

    private Serializer serializer = new Serializer();
    private Warning warning = new Warning();

    public GuiControler() { }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Label about = new Label("About");
        about.setOnMouseClicked(event -> AuthorMessage());

        AboutID.setGraphic(about);
        toDoID.setItems(toDoList);
        inProgressID.setItems(inProgressList);
        doneID.setItems(doneList);
        loadFromFile();

        saveToFileID.setOnAction(event -> saveToFile());
        loadFromFileID.setOnAction(event -> loadFromFile());
        importFromFileID.setOnAction(event -> importFromFile());
        exportToFileID.setOnAction(event -> exportToFile());

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

        toDoID.setCellFactory(list -> new ColorRectCell());
        inProgressID.setCellFactory(list -> new ColorRectCell());
        doneID.setCellFactory(list -> new ColorRectCell());
    }

    private void deserializeData(ObjectInputStream inputStream) throws IOException, ClassNotFoundException {
        serializer = (Serializer) inputStream.readObject();

        toDoList = FXCollections.observableArrayList(serializer.toDO);
        toDoID.setItems(toDoList);

        inProgressList = FXCollections.observableArrayList(serializer.inProgress);
        inProgressID.setItems(inProgressList);

        doneList = FXCollections.observableArrayList(serializer.done);
        doneID.setItems(doneList);
    }

    protected void saveToFile(){
        serializer.toDO = new ArrayList<>(toDoList);
        serializer.inProgress = new ArrayList<>(inProgressList);
        serializer.done = new ArrayList<>(doneList);

        try(ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("Object.bin"))){
            outputStream.writeObject(serializer);
        } catch (IOException e) {
            warning.showSaveErrorAlert();
        }
    }

    private void loadFromFile(){
        try(ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("Object.bin"))){
            deserializeData(inputStream);
        } catch (FileNotFoundException | NullPointerException e){
            warning.showFileNotFoundAlert();
        } catch (IOException | ClassNotFoundException e) {
            warning.showLoadErrorAlert();
        }
    }

    private void importFromFile(){
        FileChooser fileChooser = new FileChooser();
        Stage FCstage = (Stage) anchorPaneMainID.getScene().getWindow();
        File workingDirectory = new File(System.getProperty("user.dir"));
        fileChooser.setInitialDirectory(workingDirectory);
        fileChooser.setTitle("Choose file to import data");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JSON", "*.json"),
                new FileChooser.ExtensionFilter("CSV", "*.csv")
        );
        File file = fileChooser.showOpenDialog(FCstage);

        if (file != null) {
            try(ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file.getAbsolutePath()))){
                deserializeData(inputStream);
            } catch (FileNotFoundException | NullPointerException e){
                warning.showFileNotFoundAlert();
            } catch (IOException | ClassNotFoundException e) {
                warning.showLoadErrorAlert();
            }
        }
    }

    private void exportToFile(){
        FileChooser fileChooser = new FileChooser();
        Stage FCstage = (Stage) anchorPaneMainID.getScene().getWindow();
        File workingDirectory = new File(System.getProperty("user.dir"));
        fileChooser.setInitialDirectory(workingDirectory);
        fileChooser.setTitle("Make file to export data");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JSON", "*.json"),
                new FileChooser.ExtensionFilter("CSV", "*.csv")
        );
        File file = fileChooser.showSaveDialog(FCstage);

        if (file != null) {
            serializer.toDO = new ArrayList<>(toDoList);
            serializer.inProgress = new ArrayList<>(inProgressList);
            serializer.done = new ArrayList<>(doneList);

            try(ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file.getAbsolutePath()))){
                outputStream.writeObject(serializer);
            } catch (IOException e) {
                warning.showSaveErrorAlert();
            }
        }
    }

    public void setEditTaskPresenter(EditTaskPresenter editTaskPresenter){
        this.editTaskPresenter = editTaskPresenter;
    }

    public void setAddTaskPresenter(AddTaskPresenter addTaskPresenter) {
        this.addTaskPresenter = addTaskPresenter; }


    public void AuthorMessage() {
        warning.showAuthorMessage();
    }

    @FXML
    public void handlenewTaskButton(){addTaskPresenter.show();}

    @FXML
    public void handleCloseID() {
        saveToFile();
        System.exit(0);
    }


    public void handleDeleteFirst() {
        if (toDoID.getSelectionModel().getSelectedIndex() >= 0) {
            int index = toDoID.getSelectionModel().getSelectedIndex();
            toDoID.getItems().remove(index);
        }
    }

    public void handleEditFirst() {
        if (toDoID.getSelectionModel().getSelectedIndex() >= 0) {
            int index = toDoID.getSelectionModel().getSelectedIndex();
            editTaskPresenter.show(toDoID.getItems().get(index), index);
            EditTaskPresenter.listChooser = 1;
        }
    }

    public void handleDeleteSecond() {
        if (inProgressID.getSelectionModel().getSelectedIndex() >= 0) {
            int index = inProgressID.getSelectionModel().getSelectedIndex();
            inProgressID.getItems().remove(index);
        }
    }

    public void handleEditSecond() {
        if (inProgressID.getSelectionModel().getSelectedIndex() >= 0) {
            int index = inProgressID.getSelectionModel().getSelectedIndex();
            editTaskPresenter.show(inProgressID.getItems().get(index), index);
            EditTaskPresenter.listChooser = 2;
        }
    }

    public void handleDeleteThird() {
        if (doneID.getSelectionModel().getSelectedIndex() >= 0) {
            int index = doneID.getSelectionModel().getSelectedIndex();
            doneID.getItems().remove(index);
        }
    }

    public void handleEditThird() {
        if (doneID.getSelectionModel().getSelectedIndex() >= 0) {
            int index = doneID.getSelectionModel().getSelectedIndex();
            editTaskPresenter.show(doneID.getItems().get(index), index);
            EditTaskPresenter.listChooser = 3;
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

