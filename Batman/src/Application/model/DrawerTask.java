package Application.model;

import Application.resources.NewPointEvent;
import Application.resources.NewPointListener;
import javafx.concurrent.Task;
import javafx.scene.canvas.GraphicsContext;

import java.util.Random;

public class DrawerTask extends Task {

    private int pointsNumber;
    private NewPointListener listener;
    private GraphicsContext gc;
    private int delay;


    public DrawerTask(int pointsNumber, GraphicsContext gc) {
        this.pointsNumber = pointsNumber;
        this.gc = gc;
    }

    public void setListener(NewPointListener listener) {
        this.listener = listener;
    }

    @Override
    protected Object call() throws Exception {

        int amountOfcorrectPoints = 0;
        for(int i=0; i<pointsNumber; ++i){

            Random random = new Random();
            double rangeMin = -8;
            double rangeMax = 8;
            double x = rangeMin + (rangeMax - rangeMin) * random.nextDouble();
            double y = rangeMin + (rangeMax - rangeMin) * random.nextDouble();


            if(Equation.calc(x , y)) {
                listener.onPointCalculated(new NewPointEvent(x, y, true, i));
                amountOfcorrectPoints++;
            }
            else
                listener.onPointCalculated(new NewPointEvent(x, y, false,i));
            if(i % (pointsNumber * 0.01) == 0) {
                updateProgress(i, pointsNumber);
            }
            if(isCancelled())
                break;
        }

        return (amountOfcorrectPoints*16.0*16/pointsNumber);
    }

}
