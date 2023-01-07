package at.fhhagenberg.sqe;

import at.fhhagenberg.sqe.factories.ModelFactory;
import at.fhhagenberg.sqe.factories.ViewHandler;
import at.fhhagenberg.sqe.factories.ViewModelFactory;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        ModelFactory mf = new ModelFactory();
        ViewModelFactory vmf = new ViewModelFactory(mf);
        ViewHandler vh = new ViewHandler(stage, vmf);
        vh.start();

        // TODO start thread that updates the data structure
        //      the thread has to update data in the ECCViewModel because that is the model that is bind to the view
    }
}