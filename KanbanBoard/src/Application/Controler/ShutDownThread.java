package Application.Controler;

public class ShutDownThread extends Thread  {
    private GuiControler guiControler = new GuiControler();

    public void run() {
        guiControler.saveToFile(); }
}
