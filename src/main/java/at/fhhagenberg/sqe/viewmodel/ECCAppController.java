package at.fhhagenberg.sqe.viewmodel;

import at.fhhagenberg.sqe.model.Elevator;
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

    public GridPane board;
    private ECCViewModel viewModel;


    @FXML
    private Button btnAutomaticMode;

    @FXML
    private AnchorPane anchorPane;

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
        for(int i = 0; i < 8; i++)
        {
            if(i<4)
                board.add(createGridPane(i),0,i);
            else
                board.add(createGridPane(i),1,i-4);
        }
        board.add(new Rectangle(100,100, Color.WHITE),1,4,2,1);
        for(int i = 0; i< 8;i++)
        {
            ((GridPane) board.getChildren().get(0)).getChildren().get(3)
                    .styleProperty().bind(viewModel.getFloorButtonsDownColorStringProp().elementAt(i));
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
        triangleUp.setFill(Color.WHITE);
        triangleUp.setStroke(Color.BLACK);
        GridPane.setValignment(triangleUp,VPos.TOP);
        triangleUp.setId("callRequest_Up_" + Integer.toString(floor));

        Polygon triangleDown = new Polygon();
        triangleDown.getPoints().addAll(-30.0, 27.0, 30.0, 27.0, 0.0, -30.0
        );
        triangleDown.setFill(Color.WHITE);
        triangleDown.setStroke(Color.BLACK);
        GridPane.setValignment(triangleDown,VPos.BOTTOM);
        triangleDown.setScaleY(-1);
        triangleDown.setId("callRequest_Down_" + Integer.toString(floor));


        innerGrid.add(outerCircleFirstRow,0,0);
        innerGrid.add(text,0,0);
        innerGrid.add(triangleUp,1,0);
        innerGrid.add(triangleDown,1,0);
        innerGrid.add(outerCircleThirdColumn,2,0);
        innerGrid.add(innerCircleThirdColum,2,0);
        innerGrid.setGridLinesVisible(true);

        return innerGrid;
    }
}
