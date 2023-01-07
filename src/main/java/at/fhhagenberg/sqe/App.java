package at.fhhagenberg.sqe;

import at.fhhagenberg.sqe.factories.ModelFactory;
import at.fhhagenberg.sqe.factories.ViewHandler;
import at.fhhagenberg.sqe.factories.ViewModelFactory;
import at.fhhagenberg.sqe.viewmodel.ECCViewModel;
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

        // start thread to update all data
        //runAutoUpdate(vmf.getEccViewModel());    // TODO make this thread work
    }

    private void runAutoUpdate(ECCViewModel eccViewModel) {

        Thread thread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(5000);
                    eccViewModel.update();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
    }
}