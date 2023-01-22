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

    protected ModelFactory mf;
    protected ViewModelFactory vmf;
    protected ViewHandler vh;

    /**
     * CTor.
     */
    public App() {
        this.mf = new ModelFactory();
        this.vmf = new ViewModelFactory(mf);
        this.vh = new ViewHandler(vmf);
    }

    @Override
    public void start(Stage stage) throws Exception {
        vh.start(stage);

        // start thread to update all data
        runAutoUpdate(vmf.getEccViewModel());
    }

    /**
     * Interval to update all information of one building [milliseconds].
     */
    private static final int UPDATE_INTERVAL_MILLIS = 100;

    /**
     * Thread to update all information of one building.
     * @param eccViewModel: The ViewModel that is updated.
     */
    private void runAutoUpdate(ECCViewModel eccViewModel) {
        Thread thread = new Thread(() -> {
            try {

                // set up connection and initialize the view model
                eccViewModel.init();

                // update the content
                while (true) {
                    Thread.sleep(UPDATE_INTERVAL_MILLIS);
                    eccViewModel.update();
                }

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        thread.setDaemon(true);
        thread.start();
    }
}