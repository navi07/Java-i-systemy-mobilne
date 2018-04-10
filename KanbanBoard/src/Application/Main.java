<<<<<<< HEAD
package Application;

import Application.Presenter.GuiPresenter;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application
{
    public Main() {}

    public static void main(String[] args) {
        launch(args); }

    @Override
    public void start(Stage primaryStage)
    {
        primaryStage.setTitle("Kanban Board");
        GuiPresenter guiPresenter = new GuiPresenter(primaryStage);
        guiPresenter.init();
    }
=======
package Application;

import Application.Presenter.GuiPresenter;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application
{
    public Main() {}

    public static void main(String[] args) {
        launch(args); }

    @Override
    public void start(Stage primaryStage)
    {
        primaryStage.setTitle("Kanban Board");
        GuiPresenter guiPresenter = new GuiPresenter(primaryStage);
        guiPresenter.init();
    }
>>>>>>> b2973ad2250d635383f24470f8ac94847efa011d
}