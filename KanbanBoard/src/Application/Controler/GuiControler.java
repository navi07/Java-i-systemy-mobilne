package Application.Controler;

import Application.Presenter.AddTaskPresenter;
import Application.Presenter.EditTaskPresenter;
import Application.Resources.Container;
import Application.Resources.Serializer;
import Application.Resources.Type;
import Application.Resources.Warning;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.ResourceBundle;
import com.google.gson.Gson;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

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
        Label about = new Label("Autor");
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

        toDoID.setOnMouseClicked(mouseEvent -> {
            if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
                if(mouseEvent.getClickCount() == 2){
                    handleEditFirst();
                }
            }
        });

        inProgressID.setOnMouseClicked(mouseEvent -> {
            if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
                if(mouseEvent.getClickCount() == 2){
                    handleEditSecond();
                }
            }
        });

        doneID.setOnMouseClicked(mouseEvent -> {
            if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
                if(mouseEvent.getClickCount() == 2){
                   handleEditThird();
                }
            }
        });

        toDoID.setOnKeyPressed(event -> {
                    switch (event.getCode()) {
                        case RIGHT:
                            if (!toDoID.getItems().isEmpty()) {
                                inProgressID.getItems().add(toDoID.getItems().get(toDoID.getFocusModel().getFocusedIndex()));
                                toDoID.getItems().get(toDoID.getFocusModel().getFocusedIndex()).setType(Type.inProgress);
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
                        inProgressID.getItems().get(inProgressID.getFocusModel().getFocusedIndex()).setType(Type.done);
                        inProgressID.getItems().remove(inProgressID.getItems().get(inProgressID.getFocusModel().getFocusedIndex()));
                    }
                    break;
                case LEFT:
                    if (!inProgressID.getItems().isEmpty()) {
                        toDoID.getItems().add(inProgressID.getItems().get(inProgressID.getFocusModel().getFocusedIndex()));
                        inProgressID.getItems().get(inProgressID.getFocusModel().getFocusedIndex()).setType(Type.toDo);
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
                        doneID.getItems().get(doneID.getFocusModel().getFocusedIndex()).setType(Type.inProgress);
                        doneID.getItems().remove(doneID.getItems().get(doneID.getFocusModel().getFocusedIndex()));
                    }
                    break;
            }
        });

        toDoID.setCellFactory(list -> new ColorRectCell());
        inProgressID.setCellFactory(list -> new ColorRectCell());
        doneID.setCellFactory(list -> new ColorRectCell());
    }

    private void generateCSV(File file){
        try (
                BufferedWriter writer = Files.newBufferedWriter(Paths.get(file.getAbsolutePath()));

                CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
                        .withHeader("Title", "Description", "Priority", "Y", "M", "D", "Type"));
        ) {


            for (int i = 0; i < serializer.toDO.size(); i++){
                csvPrinter.printRecord(serializer.toDO.get(i).getTitle(),
                        serializer.toDO.get(i).getDescription(),
                        serializer.toDO.get(i).getPriority(),
                        serializer.toDO.get(i).getYear(),
                        serializer.toDO.get(i).getMonth(),
                        serializer.toDO.get(i).getDay(),
                        serializer.toDO.get(i).getType());
            }

            for (int i = 0; i < serializer.inProgress.size(); i++){
                csvPrinter.printRecord(serializer.inProgress.get(i).getTitle(),
                        serializer.inProgress.get(i).getDescription(),
                        serializer.inProgress.get(i).getPriority(),
                        serializer.inProgress.get(i).getYear(),
                        serializer.inProgress.get(i).getMonth(),
                        serializer.inProgress.get(i).getDay(),
                        serializer.inProgress.get(i).getType());
            }

            for (int i = 0; i < serializer.done.size(); i++){
                csvPrinter.printRecord(serializer.done.get(i).getTitle(),
                        serializer.done.get(i).getDescription(),
                        serializer.done.get(i).getPriority(),
                        serializer.done.get(i).getYear(),
                        serializer.done.get(i).getMonth(),
                        serializer.done.get(i).getDay(),
                        serializer.done.get(i).getType());
            }

            csvPrinter.flush();
        } catch (IOException e){
            warning.showSaveErrorAlert();
    }

    }

    private void generateJSON(File file){
        Gson gson = new Gson();

        try(FileWriter writer = new FileWriter(file.getAbsolutePath())){
            gson.toJson(serializer, writer);
        } catch (IOException e) {
            warning.showSaveErrorAlert();
        }
    }

    private void loadJSON(Reader reader){
        Gson gson = new Gson();
        serializer =  gson.fromJson(reader, Serializer.class);
    }

    private void loadCSV(File file) {

        try (
                Reader reader = Files.newBufferedReader(Paths.get(file.getAbsolutePath()));
                CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                        .withFirstRecordAsHeader()
                        .withIgnoreHeaderCase()
                        .withTrim());
        ) {
            serializer.toDO.clear();
            serializer.inProgress.clear();
            serializer.done.clear();

            for (CSVRecord csvRecord : csvParser) {
                String title = csvRecord.get("Title");
                String description = csvRecord.get("Description");
                String priority = csvRecord.get("Priority");
                int y = Integer.parseInt(csvRecord.get("Y"));
                int m = Integer.parseInt(csvRecord.get("M"));
                int d = Integer.parseInt(csvRecord.get("D"));
                String type = csvRecord.get("Type");

                switch (type) {
                    case "toDo":
                        serializer.toDO.add(new Container(title, description, priority, y, m, d, Type.toDo));
                        break;
                    case "inProgress":
                        serializer.inProgress.add(new Container(title, description, priority, y, m, d, Type.inProgress));
                        break;
                    case "done":
                        serializer.done.add(new Container(title, description, priority, y, m, d, Type.done));
                        break;
                }

            }
        } catch (IOException e){
            warning.showLoadErrorAlert();
         }
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
        serializer.toDO = new ArrayList<>(toDoList);
        serializer.inProgress = new ArrayList<>(inProgressList);
        serializer.done = new ArrayList<>(doneList);

        FileChooser fileChooser = new FileChooser();
        Stage FCstage = (Stage) anchorPaneMainID.getScene().getWindow();
        File workingDirectory = new File(System.getProperty("user.dir"));
        fileChooser.setInitialDirectory(workingDirectory);
        fileChooser.setTitle("Wybierz plik do wczytania");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JSON", "*.json"),
                new FileChooser.ExtensionFilter("CSV", "*.csv")
        );
        File file = fileChooser.showOpenDialog(FCstage);

        if (file != null) {
            try(Reader reader = new FileReader(file.getAbsolutePath())){
                switch (fileChooser.getSelectedExtensionFilter().getDescription()){
                    case "JSON" :
                        loadJSON(reader);
                        break;
                    case "CSV" :
                        loadCSV(file);
                        break;
                }

                toDoList = FXCollections.observableArrayList(serializer.toDO);
                toDoID.setItems(toDoList);
                inProgressList = FXCollections.observableArrayList(serializer.inProgress);
                inProgressID.setItems(inProgressList);
                doneList = FXCollections.observableArrayList(serializer.done);
                doneID.setItems(doneList);
            } catch (FileNotFoundException | NullPointerException e){
                warning.showFileNotFoundAlert();
            } catch (IOException e) {
                warning.showLoadErrorAlert();
            }
        }
    }

    private void exportToFile(){
        FileChooser fileChooser = new FileChooser();
        Stage FCstage = (Stage) anchorPaneMainID.getScene().getWindow();
        File workingDirectory = new File(System.getProperty("user.dir"));
        fileChooser.setInitialDirectory(workingDirectory);
        fileChooser.setTitle("Zapisz dane do pliku");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JSON", "*.json"),
                new FileChooser.ExtensionFilter("CSV", "*.csv")
        );
        File file = fileChooser.showSaveDialog(FCstage);

        if (file != null) {
            serializer.toDO = new ArrayList<>(toDoList);
            serializer.inProgress = new ArrayList<>(inProgressList);
            serializer.done = new ArrayList<>(doneList);

            switch (fileChooser.getSelectedExtensionFilter().getDescription()){
                case "JSON" :
                    generateJSON(file);
                    break;
                case "CSV" :
                    generateCSV(file);
                    break;
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

