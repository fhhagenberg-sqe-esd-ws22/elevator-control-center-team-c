package at.fhhagenberg.sqe.factories;

import at.fhhagenberg.sqe.viewmodel.ECCAppController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ViewHandler {

    /**
     * Stage that is used.
     */
    private final Stage stage;

    /**
     * Reference to the ViewModelFactory.
     */
    private final ViewModelFactory viewModelFactory;

    /**
     * CTor.
     * @param stage: Used window.
     * @param viewModelFactory: Reference to the ViewModelFactory.
     */
    public ViewHandler(Stage stage, ViewModelFactory viewModelFactory) {
        this.stage = stage;
        this.viewModelFactory = viewModelFactory;
    }

    /**
     * Start the whole application.
     * @throws Exception
     */
    public void start() throws Exception {
        openView("/fxml/ECCAppView.fxml");
    }

    /**
     * Open/switch to a view.
     * @param viewToOpen: View to be shown.
     * @throws Exception
     */
    public void openView(String viewToOpen) throws Exception {
        Scene scene;
        FXMLLoader loader = new FXMLLoader();
        Parent root;

        loader.setLocation(getClass().getResource(viewToOpen));
        root = loader.load();

        if ("/fxml/ECCAppView.fxml".equals(viewToOpen)) {
            ECCAppController view = loader.getController();
            view.init(viewModelFactory.getEccViewModel());
            stage.setTitle("ECC Application");
        } else {
            // TODO error handling
        }

        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
