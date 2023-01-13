package at.fhhagenberg.sqe.viewmodel;

import at.fhhagenberg.sqe.factories.ViewHandler;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.util.Duration;


public class ConnectingViewController {

    @FXML
    private ImageView imgWaiting;

    private ECCViewModel viewModel;
    private ViewHandler viewHandler;

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

    private final int checkIntervalMillis = 1000;

    private void runAutoCheck(ECCViewModel eccViewModel) {
        Thread thread = new Thread(() -> {
            try {

                Thread.sleep(checkIntervalMillis*3);
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
