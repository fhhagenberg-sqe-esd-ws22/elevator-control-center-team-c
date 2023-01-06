package at.fhhagenberg.sqe;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    /*
    @Override
    public void start(Stage stage) {
        var javaVersion = SystemInfo.javaVersion();
        var javafxVersion = SystemInfo.javafxVersion();

        var label = new Label("Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".");
        var layout = new BorderPane(label);
        var button = new Button("Click me!");
        button.setOnAction(evt -> button.setText("Clicked!"));
        layout.setBottom(button);

        var scene = new Scene(layout, 640, 480);

        stage.setScene(scene);
        stage.show();
    }

     */

    @Override
    public void start(Stage stage) {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/fxml/ECCAppView.fxml"));

        var heigth = 160;
        var width = 430;
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), width, heigth);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.setMinHeight(heigth);
        stage.setMinWidth(width);
        stage.setTitle("project");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}