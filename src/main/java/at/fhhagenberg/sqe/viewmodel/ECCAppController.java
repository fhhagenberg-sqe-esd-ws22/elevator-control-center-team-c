package at.fhhagenberg.sqe.viewmodel;

import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
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
import sqelevator.IElevator;


public class ECCAppController {

    private static final int FLOORBUTTONUP = 4;
    private static final int FLOORBUTTONDOWN = 6;
    private static final int STOPREQUEST = 1;
    private static final int TARGETFLOOR = 8;
    //Creating the mouse event handler
    EventHandler<MouseEvent> eventHandler = this::calculateManualTarget;

    private void calculateManualTarget(MouseEvent e) {
        var positionClicked = Character.getNumericValue(((Circle) e.getSource()).getId().charAt(((Circle) e.getSource()).getId().length() - 1));
        var currentPosition = Integer.parseInt(viewModel.getBuilding().getElevator(selectedElevator).getCurrentFloor().getValue());

        if (!autoMode && currentPosition != positionClicked
                && Boolean.TRUE.equals(viewModel.getBuilding().getElevator(selectedElevator).getDoorStatus().getOpenedProperty().getValue())) {
            {
                setDirectionAndTarget(currentPosition,positionClicked);
            }
        }
    }

    private void setDirectionAndTarget(int currentPosition, int positionClicked)
    {
        viewModel.setTarget(selectedElevator, positionClicked);
        var committedDirection = currentPosition < positionClicked ? IElevator.ELEVATOR_DIRECTION_UP : IElevator.ELEVATOR_DIRECTION_DOWN;
        viewModel.setCommittedDirection(selectedElevator, committedDirection);
    }

    public GridPane Board;              //NOSONAR
    public Polygon Elevator_upwards;    //NOSONAR
    public Polygon Elevator_downwards;  //NOSONAR
    public Label Speed;                 //NOSONAR
    public Label Weight;                //NOSONAR
    public Label Position;              //NOSONAR
    public Label Elevator_count;        //NOSONAR
    public Label Floor_height;          //NOSONAR
    public Label Services_floors;       //NOSONAR
    public Rectangle Opening_closing_elevator;  //NOSONAR
    public Rectangle Open_elevator;     //NOSONAR
    public Line Closed_elevator;        //NOSONAR
    public Label Auto_mode_setting;     //NOSONAR
    public ComboBox<String> Elevator_selection; //NOSONAR
    public Label Target_floor;          //NOSONAR
    public RadioButton Auto_mode_radio; //NOSONAR
    public Label currentFloor = new Label();    //NOSONAR
    private ECCViewModel viewModel;     //NOSONAR
    public StringProperty autoModeStringProp;   //NOSONAR
    private int selectedElevator = 0;   //NOSONAR
    public boolean autoMode = false;    //NOSONAR


    public void init(ECCViewModel eccViewModel) {
        this.viewModel = eccViewModel;
        autoModeStringProp = new SimpleStringProperty("OFF");


        for(int i = 0; i < viewModel.getBuilding().getFloorNum(); i++)
        {
            if(i<viewModel.getBuilding().getFloorNum()/2)
                Board.add(createGridPane(i),0,i);
            else
                Board.add(createGridPane(i),1,i-4);
        }
        var rectangle = new Rectangle(100,100,Color.WHITE);
        rectangle.setStroke(Color.BLACK);

        Elevator_selection.setItems(FXCollections.observableArrayList(CreateElevatorSelection(Integer.parseInt(viewModel.getBuilding().getElevatorNum().getValue()))));

        Board.add(rectangle,0,viewModel.getBuilding().getFloorNum()/2,2,1);

        GridPane.setValignment(rectangle,VPos.CENTER);
        GridPane.setHalignment(rectangle,HPos.CENTER);

        currentFloor.setStyle("-fx-font-size:50px;");
        currentFloor.setId("current_floor");
        Board.add(currentFloor,0,viewModel.getBuilding().getFloorNum()/2,2,1);

        GridPane.setValignment(currentFloor,VPos.CENTER);
        GridPane.setHalignment(currentFloor,HPos.CENTER);
    }

    private String[] CreateElevatorSelection(int amount)
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
        innerGrid.setId("gridPane_"+ floor);

        Circle outerCircleFirstRow = new Circle();
        outerCircleFirstRow.setRadius(35);
        outerCircleFirstRow.setFill(Color.WHITE);
        outerCircleFirstRow.setStroke(Color.BLACK);
        outerCircleFirstRow.setStrokeWidth(1);

        Circle outerCircleFirstRowPressed = new Circle();
        outerCircleFirstRowPressed.setRadius(30);
        outerCircleFirstRowPressed.setVisible(false);
        outerCircleFirstRowPressed.setFill(Color.GREEN);
        outerCircleFirstRowPressed.setStroke(Color.BLACK);
        outerCircleFirstRowPressed.setStrokeWidth(1);

        Circle outerCircleThirdColumn = new Circle();
        outerCircleThirdColumn.setRadius(35);
        outerCircleThirdColumn.setFill(Color.WHITE);
        outerCircleThirdColumn.setStroke(Color.BLACK);
        outerCircleThirdColumn.setStrokeWidth(1);
        outerCircleThirdColumn.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);
        outerCircleThirdColumn.setId("stopRequest_"+ floor);


        Circle innerCircleThirdColum = new Circle();
        innerCircleThirdColum.setRadius(15);
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

        Polygon triangleUpPressed = new Polygon();
        triangleUpPressed.getPoints().addAll(-30.0, 27.0, 30.0, 27.0, 0.0, -30.0
        );
        triangleUpPressed.setFill(Color.GREEN);
        triangleUpPressed.setStroke(Color.BLACK);
        triangleUpPressed.setVisible(false);
        GridPane.setValignment(triangleUpPressed,VPos.TOP);
        triangleUpPressed.setId("callRequest_Up_" + floor);

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


        Polygon triangleDownPressed = new Polygon();
        triangleDownPressed.getPoints().addAll(-30.0, 27.0, 30.0, 27.0, 0.0, -30.0
        );
        triangleDownPressed.setFill(Color.GREEN);
        triangleDownPressed.setStroke(Color.BLACK);
        triangleDownPressed.setVisible(false);
        GridPane.setValignment(triangleDownPressed,VPos.BOTTOM);
        triangleDownPressed.setScaleY(-1);
        triangleDownPressed.setId("callRequest_Down_" + floor);

        innerGrid.add(outerCircleFirstRow,0,0);
        innerGrid.add(outerCircleFirstRowPressed,0,0);
        innerGrid.add(text,0,0);
        innerGrid.add(triangleUp,1,0);
        innerGrid.add(triangleUpPressed,1,0);
        innerGrid.add(triangleDown,1,0);
        innerGrid.add(triangleDownPressed,1,0);
        innerGrid.add(outerCircleThirdColumn,2,0);
        innerGrid.add(innerCircleThirdColum,2,0);
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
            ((GridPane) Board.getChildren().get(i)).getChildren().get(FLOORBUTTONDOWN)
                    .visibleProperty().bind(viewModel.getBuilding().getFloorButton(i).isButtonDown());
            ((GridPane) Board.getChildren().get(i)).getChildren().get(FLOORBUTTONUP)
                    .visibleProperty().bind(viewModel.getBuilding().getFloorButton(i).isButtonUp());
            ((GridPane) Board.getChildren().get(i)).getChildren().get(STOPREQUEST)
                    .visibleProperty().bind(viewModel.getBuilding().getElevator(selectedElevator).getButton(i));
            ((GridPane) Board.getChildren().get(i)).getChildren().get(TARGETFLOOR)
                    .visibleProperty().bind(viewModel.getBuilding().getElevator(selectedElevator).getFloorTarget(i));
        }
        currentFloor.textProperty().bind(viewModel.getBuilding().getElevator(selectedElevator).getCurrentFloor());
        Position.textProperty().bind(viewModel.getBuilding().getElevator(selectedElevator).getCurrentPositionFt());
        Speed.textProperty().bind(viewModel.getBuilding().getElevator(selectedElevator).getCurrentSpeedFtPerSec());
        Weight.textProperty().bind(viewModel.getBuilding().getElevator(selectedElevator).getWeight());
        Elevator_count.textProperty().bind(viewModel.getBuilding().getElevatorNum());
        Floor_height.textProperty().bind(viewModel.getBuilding().getFloorHeight());
        Open_elevator.visibleProperty().bind(viewModel.getBuilding().getElevator(selectedElevator).getDoorStatus().getOpenedProperty());
        Closed_elevator.visibleProperty().bind(viewModel.getBuilding().getElevator(selectedElevator).getDoorStatus().getClosedProperty());
        Opening_closing_elevator.visibleProperty().bind(Bindings.or(
                viewModel.getBuilding().getElevator(selectedElevator).getDoorStatus().getOpeningProperty(),
                viewModel.getBuilding().getElevator(selectedElevator).getDoorStatus().getClosingProperty()
        ));
        Elevator_upwards.visibleProperty().bind(viewModel.getBuilding().getElevator(selectedElevator).getDirection().getUpProperty());
        Elevator_downwards.visibleProperty().bind(viewModel.getBuilding().getElevator(selectedElevator).getDirection().getDownProperty());
        Target_floor.textProperty().bind(viewModel.getBuilding().getElevator(selectedElevator).getFloorTargetStringProp());
        Auto_mode_setting.textProperty().bind(viewModel.getBuilding().getElevator(selectedElevator).getAutomaticMode());
        Auto_mode_radio.setSelected(viewModel.getBuilding().getElevator(selectedElevator).getAutomaticModeBool().getValue());
        Services_floors.textProperty().bind(viewModel.getBuilding().getElevator(selectedElevator).getServicedFloorStringProp());
    }

    public void elevator_selected() {
        selectedElevator = Character.getNumericValue(Elevator_selection.getValue().charAt(Elevator_selection.getValue().length()-1));
        setBindings();
    }

    public void switchMode() {
        viewModel.getBuilding().getElevator(selectedElevator).setAutomaticMode(!viewModel.getBuilding().getElevator(selectedElevator).getAutomaticModeBool().getValue());
        autoMode =  !autoMode;
    }

}
