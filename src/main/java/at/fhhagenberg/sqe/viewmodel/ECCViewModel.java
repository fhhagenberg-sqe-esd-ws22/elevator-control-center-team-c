package at.fhhagenberg.sqe.viewmodel;

import at.fhhagenberg.sqe.delete.IElevatorMock;
import at.fhhagenberg.sqe.interfaces.IElevatorService;
import at.fhhagenberg.sqe.model.Building;
import at.fhhagenberg.sqe.model.RMIElevatorService;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import java.util.Vector;

public class ECCViewModel {

    // TODO create all properties you need to show
    private StringProperty testLabel;

    public GridPane getGridInput() {
        return gridInput;
    }

    public void setGridInput(GridPane gridInput) {
        this.gridInput = gridInput;
    }

    private GridPane gridInput;

    /**
     * Reference of the data structure.
     */
    private Building building;
    private IElevatorService elevatorService;


    /**
     * CTor.
     * @param building
     */
    public ECCViewModel(Building building) {
        this.building = building;

        createElevatorService();

        testLabel = new SimpleStringProperty();
        testLabel.setValue("Testoutput stands here!");
    }



    public StringProperty getTestLabel() { return testLabel; }


    public void createElevatorService() {
        IElevatorMock deleteElevatorMock = new IElevatorMock();
        this.elevatorService = new RMIElevatorService(deleteElevatorMock);
    }

    public void init() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                // TODO do the initialization of the structure
            }
        });
    }
    private Vector<StringProperty> vector = new Vector<>();
    public Vector<StringProperty> getFloorButtonsDownColorStringProp (){
        /*for (var floor : building.getFloorButtons())
        {
            vector.add(boolToColorStringProperty(floor.isButtonDown()));
        }*/
        return vector;
    };
    private StringProperty boolToColorStringProperty(boolean val)
    {
        return val ? new SimpleStringProperty("-fx-fill: green;") : new SimpleStringProperty("-fx-fill: white;");
    }
    public String getStopRequestColorString (){
        return "-fx-background-color:blue;";
    };
    private int cnt = 0;    // TODO delete (just for testing

    public void update() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                testLabel.setValue("called: " + cnt++);

                boolean elevatorButton = elevatorService.getElevatorButton(10,10);
            }
        });
    }
}
