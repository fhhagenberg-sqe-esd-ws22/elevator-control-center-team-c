package at.fhhagenberg.sqe.viewmodel;

import at.fhhagenberg.sqe.factories.ViewHandler;
import javafx.animation.RotateTransition;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

/**
 * Controller for the connecting view.
 */
public class ConnectingViewController {

    /**
     * Rotating image that is displayed.
     */
    @FXML
    private ImageView imgWaiting;

    /**
     * Reference to the ECCViewModel.
     */
    private ECCViewModel viewModel;

    /**
     * Reference to the ViewHandler to switch views.
     */
    private ViewHandler viewHandler;

    /**
     * Initialize this view.
     * @param eccViewModel: ECCViewModel.
     * @param vh: ViewHandler to switch views.
     */
    public void init(ECCViewModel eccViewModel, ViewHandler vh) {
        this.viewModel = eccViewModel;
        this.viewHandler = vh;

        RotateTransition rotateTransition = new RotateTransition();
        rotateTransition.setNode(imgWaiting);
        rotateTransition.setDuration(Duration.millis(2000));
        rotateTransition.setCycleCount(RotateTransition.INDEFINITE);
        rotateTransition.setByAngle(360);
        rotateTransition.play();

        runAutoCheck(this.viewModel);
    }

    /**
     * Interval to check if the data structure is completely initialized.
     */
    private final int checkIntervalMillis = 500;

    /**
     * Thread to check if the data structure is completely initialized. If
     * so, switch to the main page.
     * @param eccViewModel
     */
    private void runAutoCheck(ECCViewModel eccViewModel) {
        Thread thread = new Thread(() -> {
            try {

                Thread.sleep(checkIntervalMillis);
                while (!viewModel.isInitialized()) {
                    Thread.sleep(checkIntervalMillis);
                }

                this.viewHandler.openView("/fxml/ECCAppView.fxml");

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        thread.setDaemon(true);
        thread.start();
    }
}
