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
        runAutoUpdate(vmf.getEccViewModel());
    }

    /**
     * Interval to update all information of one building [milliseconds].
     */
    private final int updateIntervalMillis = 2000;

    /**
     * Thread to update all information of one building.
     * @param eccViewModel: The ViewModel that is updated.
     */
    private void runAutoUpdate(ECCViewModel eccViewModel) {
        Thread thread = new Thread(() -> {

            // TODO set up connection to RMI
            // TODO initialize the view model once

            while (true) {
                try {
                    Thread.sleep(updateIntervalMillis);
                    eccViewModel.update();
                } catch (InterruptedException e) {      // TODO make useful error handling
                    e.printStackTrace(System.err);
                    throw new RuntimeException(e);
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
    }
}