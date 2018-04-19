package Application.controller;

import Application.model.DrawerTask;
import Application.resources.NewPointEvent;
import Application.resources.NewPointListener;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import java.awt.image.BufferedImage;

public class Controller implements NewPointListener {

    public TextField numberOfPointsField;
    private BufferedImage bufferedImage;
    private GraphicsContext gc;
    private int pointsNumber;
    private DrawerTask task;
    private double height;

    @FXML
    private Canvas canvas;
    @FXML
    private Button buttonStart;
    @FXML
    private Button buttonStop;
    @FXML
    private ProgressBar progressBar;
    @FXML
    private TextField resultField;
   
    @FXML
    void handleStartButton(){
        changeStateOfFields(false);
        gc = canvas.getGraphicsContext2D();
        draw(gc);
        height = gc.getCanvas().getHeight();
    }
    @FXML
    void handleStopButton(){
        task.cancel();
        changeStateOfFields(true);
    }

    public void draw(GraphicsContext gc) throws IllegalArgumentException{

try {
    bufferedImage = new BufferedImage(550, 550, BufferedImage.TYPE_INT_RGB);

    if (numberOfPointsField.getText().equals("")) {
        pointsNumber = 5000000;
    }
    else {
        pointsNumber = Integer.parseInt(numberOfPointsField.getText());
    }
        task = new DrawerTask(pointsNumber, gc);
        task.setListener(this);

        task.setOnSucceeded(event -> doOnSucceeded());
    progressBar.progressProperty().bind(task.progressProperty());
        new Thread(task).start();

}
catch(IllegalArgumentException e){
        bufferedImage = new BufferedImage(550, 550, BufferedImage.TYPE_INT_RGB);
        gc.drawImage(SwingFXUtils.toFXImage(bufferedImage, null), 0, 0);

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERROR");
        alert.setContentText("Input correct data !");
        alert.showAndWait();
        changeStateOfFields(true);
}
    }

    @Override
    public void onPointCalculated(NewPointEvent event) {
        int b = (int) height - 1;
        int pointX = (int)((b - 1) * (event.getX() - (-8)) / (8 - (-8))+ 1 );
        int pointY = (int) ((b -1) * (event.getY() -(-8)) / (8 - (-8))+ 1 );
        pointY = (int) height - pointY;

        if(event.isInside())
            bufferedImage.setRGB(pointX, pointY, java.awt.Color.YELLOW.getRGB());
        else
            bufferedImage.setRGB(pointX, pointY, java.awt.Color.BLUE.getRGB());
        if(event.getCounter() % (pointsNumber/100) == 0) {
            gc.drawImage(SwingFXUtils.toFXImage(bufferedImage, null), 0, 0);
        }
    }

    private void changeStateOfFields(boolean isEnabled) {
        boolean isDisabled = !isEnabled;
        buttonStop.setDisable(isEnabled);
        numberOfPointsField.setDisable(isDisabled);
        buttonStart.setDisable(isDisabled);
    }

    private void doOnSucceeded(){
        resultField.setText(task.getValue().toString());
        changeStateOfFields(true);
    }

}
