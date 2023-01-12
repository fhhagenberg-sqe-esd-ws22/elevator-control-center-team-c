package at.fhhagenberg.sqe.viewmodel;

import at.fhhagenberg.sqe.factories.ViewHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ConnectingViewController {
    @FXML
    public Label lbConnecting;

    private ECCViewModel viewModel;
    private ViewHandler viewHandler;

    public void init(ECCViewModel eccViewModel, ViewHandler vh) {
        this.viewModel = eccViewModel;
        this.viewHandler = vh;

        runAutoCheck(this.viewModel);
    }

    private final int checkIntervalMillis = 1000;

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
