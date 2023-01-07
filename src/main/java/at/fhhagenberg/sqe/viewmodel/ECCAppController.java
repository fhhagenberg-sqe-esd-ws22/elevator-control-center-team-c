package at.fhhagenberg.sqe.viewmodel;

import at.fhhagenberg.sqe.model.Elevator;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Vector;

public class ECCAppController {

    private ECCViewModel viewModel;


    @FXML
    private Button btnAutomaticMode;

    @FXML
    private ComboBox<Elevator> cbSelectElevator;

    private ObservableList<Elevator> elevators;

    public void init(ECCViewModel eccViewModel) {
        this.viewModel = eccViewModel;

        // TODO do the binding here
        //x.ValueProperty().bind(viewModel.xProperty());    // *example!
    }

    // TODO on combo box selection change the binding to the selected elevator
    //      but maybe in the viewModel and not here

    @FXML
    public void handleButtonClick(MouseEvent mouseEvent) {
        System.out.print("Automatic Mode Button\n");
    }
}
