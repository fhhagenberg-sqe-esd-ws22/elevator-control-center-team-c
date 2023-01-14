package at.fhhagenberg.sqe.viewmodel;

import at.fhhagenberg.sqe.IElevator;
import at.fhhagenberg.sqe.model.Elevator;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;


public class ECCAppController {

    private final int FLOORBUTTONUP = 4;
    private final int FLOORBUTTONDOWN = 6;
    private final int STOPREQUEST = 1;
    private final int TARGETFLOOR = 8;

    public GridPane board;
    public Polygon elevator_upwards;
    public Polygon elevator_downwards;
    public Label speed;
    public Label weight;
    public Label position;
    public Label elevator_count;
    public Label floor_height;
    public Label services_floors;
    public Rectangle opening_closing_elevator;
    public Rectangle open_elevator;
    public Line closed_elevator;
    public Label auto_mode_setting;
    public ComboBox elevator_selection;
    public Label target_floor;
    public RadioButton auto_mode_radio;
    private ECCViewModel viewModel;
    public StringProperty autoModeStringProp;


    @FXML
    private Button btnAutomaticMode;

    @FXML
    private AnchorPane anchorPane;

    private int selectedElevator = 0;
    private boolean autoMode = false;
    @FXML
    private Label labTest;

    @FXML
    private GridPane gridPane;

    @FXML
    private ComboBox<Elevator> cbSelectElevator;

    private ObservableList<Elevator> elevators;

    public void init(ECCViewModel eccViewModel) {
        this.viewModel = eccViewModel;
        autoModeStringProp = new SimpleStringProperty("OFF");


        for(int i = 0; i < viewModel.getBuilding().getFloorNum(); i++)
        {
            if(i<viewModel.getBuilding().getFloorNum()/2)
                board.add(createGridPane(i),0,i);
            else
                board.add(createGridPane(i),1,i-4);
        }
        var rectangle = new Rectangle(100,100,Color.WHITE);
        rectangle.setStroke(Color.BLACK);

        elevator_selection.setItems(FXCollections.observableArrayList(createElevatorSelection(Integer.parseInt(viewModel.getBuilding().getElevatorNum().getValue()))));


        board.add(rectangle,0,viewModel.getBuilding().getFloorNum()/2,2,1);



        GridPane.setValignment(rectangle,VPos.CENTER);
        GridPane.setHalignment(rectangle,HPos.CENTER);

        Label currentFloor = new Label();
        currentFloor.setStyle("-fx-font-size:50px;");
        currentFloor.setText("test");
        board.add(currentFloor,0,viewModel.getBuilding().getFloorNum()/2,2,1);

        GridPane.setValignment(currentFloor,VPos.CENTER);
        GridPane.setHalignment(currentFloor,HPos.CENTER);
        ((Label)board.getChildren().get(9)).textProperty().bind(viewModel.getBuilding().getElevator(selectedElevator).getCurrentFloor());

        // TODO bind services floors
        // TODO choose elevator
        // TODO bind upwards, downwards, nothing
        // TODO switch between automatic and manual mode
    }
    // TODO on combo box selection change the binding to the selected elevator
    //      but maybe in the viewModel and not here

    @FXML
    public void handleButtonClick(MouseEvent mouseEvent) {
        System.out.print("Automatic Mode Button\n");
    }

    private String[] createElevatorSelection(int amount)
    {
        String[] selections = new String[amount];
        for(int i=0; i<amount; i++)
        {
            selections[i] = "Elevator " + i;
        }
        return selections;
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
        innerCircleThirdColum.setVisible(false);


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

        Polygon triangleUp_pressed = new Polygon();
        triangleUp_pressed.getPoints().addAll(-30.0, 27.0, 30.0, 27.0, 0.0, -30.0
        );
        triangleUp_pressed.setFill(Color.GREEN);
        triangleUp_pressed.setStroke(Color.BLACK);
        triangleUp_pressed.setVisible(false);
        GridPane.setValignment(triangleUp_pressed,VPos.TOP);
        triangleUp_pressed.setId("callRequest_Up_" + Integer.toString(floor));

        Polygon triangleUp= new Polygon();
        triangleUp.getPoints().addAll(-30.0, 27.0, 30.0, 27.0, 0.0, -30.0
        );
        triangleUp.setFill(Color.WHITE);
        triangleUp.setStroke(Color.BLACK);
        GridPane.setValignment(triangleUp,VPos.TOP);

        Polygon triangleDown = new Polygon();
        triangleDown.getPoints().addAll(-30.0, 27.0, 30.0, 27.0, 0.0, -30.0
        );
        triangleDown.setFill(Color.WHITE);
        triangleDown.setStroke(Color.BLACK);
        GridPane.setValignment(triangleDown,VPos.BOTTOM);
        triangleDown.setScaleY(-1);


        Polygon triangleDown_pressed = new Polygon();
        triangleDown_pressed.getPoints().addAll(-30.0, 27.0, 30.0, 27.0, 0.0, -30.0
        );
        triangleDown_pressed.setFill(Color.GREEN);
        triangleDown_pressed.setStroke(Color.BLACK);
        triangleDown_pressed.setVisible(false);
        GridPane.setValignment(triangleDown_pressed,VPos.BOTTOM);
        triangleDown_pressed.setScaleY(-1);
        triangleDown_pressed.setId("callRequest_Down_" + Integer.toString(floor));


        innerGrid.add(outerCircleFirstRow,0,0);
        innerGrid.add(outerCircleFirstRow_Pressed,0,0);
        innerGrid.add(text,0,0);
        innerGrid.add(triangleUp,1,0);
        innerGrid.add(triangleUp_pressed,1,0);
        innerGrid.add(triangleDown,1,0);
        innerGrid.add(triangleDown_pressed,1,0);
        innerGrid.add(outerCircleThirdColumn,2,0);
        innerGrid.add(innerCircleThirdColum,2,0);
        //innerGrid.setGridLinesVisible(true);
        GridPane.setMargin(innerGrid,new Insets(10,10,10,10));

        return innerGrid;
    }
    /*
     * BINDINGS
     */
    private void setBindings()
    {
        for(int i = 0; i<viewModel.getBuilding().getFloorNum(); i++)
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
        position.textProperty().bind(viewModel.getBuilding().getElevator(selectedElevator).getCurrentPositionFt());
        speed.textProperty().bind(viewModel.getBuilding().getElevator(selectedElevator).getCurrentSpeedFtPerSec());
        weight.textProperty().bind(viewModel.getBuilding().getElevator(selectedElevator).getWeight());
        elevator_count.textProperty().bind(viewModel.getBuilding().getElevatorNum());
        floor_height.textProperty().bind(viewModel.getBuilding().getFloorHeight());
        open_elevator.visibleProperty().bind(viewModel.getBuilding().getElevator(selectedElevator).getDoorStatus().getOpenedProperty());
        closed_elevator.visibleProperty().bind(viewModel.getBuilding().getElevator(selectedElevator).getDoorStatus().getClosedProperty());
        opening_closing_elevator.visibleProperty().bind(Bindings.or(
                viewModel.getBuilding().getElevator(selectedElevator).getDoorStatus().getOpeningProperty(),
                viewModel.getBuilding().getElevator(selectedElevator).getDoorStatus().getClosingProperty()
        ));
        elevator_upwards.visibleProperty().bind(viewModel.getBuilding().getElevator(selectedElevator).getDirection().getUpProperty());
        elevator_downwards.visibleProperty().bind(viewModel.getBuilding().getElevator(selectedElevator).getDirection().getDownProperty());
        target_floor.textProperty().bind(viewModel.getBuilding().getElevator(selectedElevator).getFloorTargetStringProp());
        auto_mode_setting.textProperty().bind(viewModel.getBuilding().getElevator(selectedElevator).getAutomaticMode());
        //auto_mode_radio.selectedProperty().bind(viewModel.getBuilding().getElevator(selectedElevator).getAutomaticMode_bool());
    }
    public void elevator_selected(ActionEvent actionEvent) {
        //System.out.print(elevator_selection.getValue());
        selectedElevator = Character.getNumericValue(elevator_selection.getValue().toString().charAt(elevator_selection.getValue().toString().length()-1));
        setBindings();
    }

    public void switch_mode(MouseEvent keyEvent) {
        autoMode = !autoMode;
        viewModel.getBuilding().getElevator(selectedElevator).setAutomaticMode(autoMode);
    }
}
