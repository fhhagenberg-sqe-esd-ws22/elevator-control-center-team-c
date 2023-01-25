package at.fhhagenberg.sqe.factories;

import at.fhhagenberg.sqe.viewmodel.ConnectingViewController;
import at.fhhagenberg.sqe.viewmodel.ECCAppController;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewHandler {

    /**
     * Stage that is used.
     */
    private Stage stage;

    /**
     * Reference to the ViewModelFactory.
     */
    private final ViewModelFactory viewModelFactory;

    /**
     * CTor.
     * @param viewModelFactory: Reference to the ViewModelFactory.
     */
    public ViewHandler(ViewModelFactory viewModelFactory) {
        this.viewModelFactory = viewModelFactory;
    }

    /**
     * Start the whole application.
     * @param stage: Used window.
     * @throws IOException error at start
     */
    public void start(Stage stage) throws IOException {
        this.stage = stage;
        openView("/fxml/ConnectingView.fxml");
    }

    /**
     * Open/switch to a view.
     * @param viewToOpen: View to be shown.
     */
    public void openView(String viewToOpen) {

        class OneShotTask implements Runnable {
            ViewHandler viewHandler;
            OneShotTask(ViewHandler vh) { this.viewHandler = vh; }
            public void run() {
                try {

                    Scene scene;
                    FXMLLoader loader = new FXMLLoader();
                    Parent root;

                    loader.setLocation(getClass().getResource(viewToOpen));
                        root = loader.load();

                    if ("/fxml/ECCAppView.fxml".equals(viewToOpen)) {
                        ECCAppController view = loader.getController();
                        view.init(viewModelFactory.getEccViewModel());
                        stage.setTitle("ECC Application");
                    } else if ("/fxml/ConnectingView.fxml".equals(viewToOpen)) {
                        ConnectingViewController view = loader.getController();
                        view.init(viewModelFactory.getEccViewModel(), this.viewHandler);
                        stage.setTitle("ECC Connecting");
                    } else {
                        return;
                    }

                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        Platform.runLater(new OneShotTask(this));
    }
}
