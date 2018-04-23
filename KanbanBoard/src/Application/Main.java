package Application;

import Application.Controler.ShutDownThread;
import Application.Presenter.GuiPresenter;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application
{
    public Main() {}

    public static void main(String[] args) {
        launch(args); }

    @Override
    public void start(Stage primaryStage)
    {
        primaryStage.setTitle("Lista zadań");
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("Resources/icon_png.png")));
        GuiPresenter guiPresenter = new GuiPresenter(primaryStage);
        Runtime.getRuntime().addShutdownHook(new ShutDownThread());
        guiPresenter.init();
    }
}

