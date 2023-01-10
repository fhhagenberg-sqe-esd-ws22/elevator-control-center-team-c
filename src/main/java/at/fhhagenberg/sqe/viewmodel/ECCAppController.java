package at.fhhagenberg.sqe.viewmodel;

import at.fhhagenberg.sqe.model.Elevator;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;


public class ECCAppController {

    private final int FLOORBUTTONUP = 3;
    private final int FLOORBUTTONDOWN = 4;
    private final int STOPREQUEST = 1;
    private final int TARGETFLOOR = 6;

    public GridPane board;
    private ECCViewModel viewModel;


    @FXML
    private Button btnAutomaticMode;

    @FXML
    private AnchorPane anchorPane;

    private int selectedElevator = 2;
    @FXML
    private Label labTest;

    @FXML
    private GridPane gridPane;

    @FXML
    private ComboBox<Elevator> cbSelectElevator;

    private ObservableList<Elevator> elevators;

    public void init(ECCViewModel eccViewModel) {
        this.viewModel = eccViewModel;

        // TODO do the bindings here
        labTest.textProperty().bind(viewModel.getTestLabel());
        for(int i = 0; i < viewModel.getFloors(); i++)
        {
            if(i<viewModel.getFloors()/2)
                board.add(createGridPane(i),0,i);
            else
                board.add(createGridPane(i),1,i-4);
        }
        var rectangle = new Rectangle(100,100,Color.WHITE);
        rectangle.setStroke(Color.BLACK);


        board.add(rectangle,0,viewModel.getFloors()/2,2,1);
        GridPane.setValignment(rectangle,VPos.CENTER);
        GridPane.setHalignment(rectangle,HPos.CENTER);

        Label currentFloor = new Label();
        currentFloor.setStyle("-fx-font-size:50px;");
        currentFloor.setText("test");
        board.add(currentFloor,0,viewModel.getFloors()/2,2,1);

        GridPane.setValignment(currentFloor,VPos.CENTER);
        GridPane.setHalignment(currentFloor,HPos.CENTER);
        ((Label)board.getChildren().get(9)).textProperty().bind(viewModel.getBuilding().getElevator(selectedElevator).getCurrentFloor());
        for(int i = 0; i<viewModel.getFloors(); i++)
        {
            ((GridPane) board.getChildren().get(i)).getChildren().get(FLOORBUTTONDOWN)
                    .visibleProperty().bind(viewModel.getBuilding().getFloorButton(i).isButtonDown());
            ((GridPane) board.getChildren().get(i)).getChildren().get(FLOORBUTTONUP)
                    .visibleProperty().bind(viewModel.getBuilding().getFloorButton(i).isButtonUp());
            ((GridPane) board.getChildren().get(i)).getChildren().get(STOPREQUEST)
                    .visibleProperty().bind(viewModel.getBuilding().getElevator(selectedElevator).getButton(i));
            ((GridPane) board.getChildren().get(i)).getChildren().get(TARGETFLOOR)
                    .visibleProperty().bind(viewModel.getBuilding().getElevator(selectedElevator).getFloorTarget(i));

        }


    }
    // TODO on combo box selection change the binding to the selected elevator
    //      but maybe in the viewModel and not here

    @FXML
    public void handleButtonClick(MouseEvent mouseEvent) {
        System.out.print("Automatic Mode Button\n");
    }

    private GridPane createGridPane(int floor)
    {
        var innerGrid = new GridPane();
        innerGrid.setId("gridPane_"+Integer.toString(floor));

        Circle outerCircleFirstRow = new Circle();
        outerCircleFirstRow.setRadius(35);
        outerCircleFirstRow.setFill(Color.WHITE);
        outerCircleFirstRow.setStroke(Color.BLACK);
        outerCircleFirstRow.setStrokeWidth(1);

        Circle outerCircleFirstRow_Pressed = new Circle();
        outerCircleFirstRow_Pressed.setRadius(30);
        outerCircleFirstRow_Pressed.setVisible(false);
        outerCircleFirstRow_Pressed.setFill(Color.GREEN);
        outerCircleFirstRow_Pressed.setStroke(Color.BLACK);
        outerCircleFirstRow_Pressed.setStrokeWidth(1);

        Circle outerCircleThirdColumn = new Circle();
        outerCircleThirdColumn.setRadius(35);
        outerCircleThirdColumn.setFill(Color.WHITE);
        outerCircleThirdColumn.setStroke(Color.BLACK);
        outerCircleThirdColumn.setStrokeWidth(1);

        Circle innerCircleThirdColum = new Circle();
        innerCircleThirdColum.setRadius(15);
        innerCircleThirdColum.setId("stopRequest_"+Integer.toString(floor));
        innerCircleThirdColum.setFill(Color.RED);


        Text text = new Text();
        text.setText(Integer.toString(floor));
        text.setStyle("-fx-font: 48 arial;");

        for(int i = 0; i < 3; i++)
        {
            ColumnConstraints col = new ColumnConstraints(50,100,150, Priority.SOMETIMES,HPos.CENTER,true);
            col.setPrefWidth(100);
            innerGrid.getColumnConstraints().addAll(col);
        }
        RowConstraints row = new RowConstraints(50,100,150, Priority.SOMETIMES,VPos.CENTER,true);
        innerGrid.getRowConstraints().add(row);

        Polygon triangleUp = new Polygon();
        triangleUp.getPoints().addAll(-30.0, 27.0, 30.0, 27.0, 0.0, -30.0
        );
        triangleUp.setFill(Color.GREEN);
        triangleUp.setStroke(Color.BLACK);
        triangleUp.setVisible(false);
        GridPane.setValignment(triangleUp,VPos.TOP);
        triangleUp.setId("callRequest_Up_" + Integer.toString(floor));

        Polygon triangleDown = new Polygon();
        triangleDown.getPoints().addAll(-30.0, 27.0, 30.0, 27.0, 0.0, -30.0
        );
        triangleDown.setFill(Color.GREEN);
        triangleDown.setStroke(Color.BLACK);
        triangleDown.setVisible(false);
        GridPane.setValignment(triangleDown,VPos.BOTTOM);
        triangleDown.setScaleY(-1);
        triangleDown.setId("callRequest_Down_" + Integer.toString(floor));


        innerGrid.add(outerCircleFirstRow,0,0);
        innerGrid.add(outerCircleFirstRow_Pressed,0,0);
        innerGrid.add(text,0,0);
        innerGrid.add(triangleUp,1,0);
        innerGrid.add(triangleDown,1,0);
        innerGrid.add(outerCircleThirdColumn,2,0);
        innerGrid.add(innerCircleThirdColum,2,0);
        innerGrid.setGridLinesVisible(true);

        return innerGrid;
    }
}
