package at.fhhagenberg.sqe.viewmodel;

import at.fhhagenberg.sqe.IElevator;
import at.fhhagenberg.sqe.delete.IElevatorMock;
import at.fhhagenberg.sqe.interfaces.IElevatorService;
import at.fhhagenberg.sqe.model.Building;
import at.fhhagenberg.sqe.model.Elevator;
import at.fhhagenberg.sqe.model.FloorButton;
import at.fhhagenberg.sqe.model.RMIElevatorService;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import java.util.Random;
import java.util.Vector;

public class ECCViewModel {

    // TODO create all properties you need to show
    private StringProperty testLabel;
    private int currentSelectedElevator = 3;

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
    protected IElevatorService elevatorService;


    /**
     * CTor.
     * @param building
     */
    public ECCViewModel(Building building) {

        this.building = building;
        /**/
        this.building = new Building();
        this.building.setFloorHeight(10);
        int floorCnt = 8;
        for(int i =0; i<floorCnt;i++)
        {
            var button = new FloorButton();
            button.setButtonUp(true);
            button.setButtonDown(false);
            this.building.addFloorButton(button);
        }
        for (int i = 0; i< 3; i++)
        {
            Elevator elevator = new Elevator(1000,floorCnt);
            elevator.setDirection(i);
            elevator.setAcceleration(100);
            boolean val = true;
            for(int k=0; k<floorCnt; k++)
            {
                val = !val;
                elevator.setButton(k,val);
                elevator.setServicedFloor(k,true);
            }
            elevator.setAutomaticMode(true);
            elevator.setCurrentFloor(i);
            elevator.setWeight(700);
            elevator.setCurrentPositionFt(167);
            elevator.setCurrentSpeedFtPerSec(23);
            elevator.setDoorStatus(i+1);
            elevator.setFloorTarget(i+3);
            this.building.addElevator(elevator);
        }



        createElevatorService(new IElevatorMock());

        testLabel = new SimpleStringProperty();
        testLabel.setValue("Testoutput stands here!");
    }



    public StringProperty getTestLabel() { return testLabel; }


    protected void createElevatorService(IElevator elevator) {
        this.elevatorService = new RMIElevatorService(elevator);
    }

    public int getFloors()
    {
        return this.building.getFloorNum();
    }


    public void init() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                int elevatorNum = elevatorService.getElevatorNum();
                int floorHeight = elevatorService.getFloorHeight();
                int floorNum = elevatorService.getFloorNum();

                int maxPayload = 10;    // TODO find correct payload
                long clockTick = elevatorService.getClockTick();

                for (int idx = 0; idx < elevatorNum; idx++) {
                    Elevator elevator = new Elevator(maxPayload,floorNum);
                    building.addElevator(elevator);
                }
            }
        });
    }
    private int cnt = 0;    // TODO delete (just for testing
    private boolean flag = true;
    public void update() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                testLabel.setValue("called: " + cnt++);

                boolean elevatorButton = elevatorService.getElevatorButton(10,10);
                //building.getFloorButton(2).setButtonDown(!building.getFloorButton(2).isButtonDown());
                //floorButtonDownVector.setElementAt(new SimpleStringProperty("-fx-fill: white;"),2);
                /*flag = !flag;
                for (int i=0; i<getFloors(); i++)
                {
                    building.getFloorButton(i).setButtonDown(flag);
                    building.getFloorButton(i).setButtonUp(!flag);
                    building.getElevator(2).setButton(i,flag);
                }
                var rand = new Random();
                building.getElevator(2).setFloorTarget(rand.nextInt(getFloors()));
                building.getElevator(2).setCurrentFloor(rand.nextInt(getFloors()));

                 */
                var test = "test";
                // update in Elevator
                /*
                setDirection(elevatorService.getCommittedDirection(elevatorNumber));
                setAcceleration(elevatorService.getElevatorAccel(elevatorNumber));
                setDoorStatus(elevatorService.getElevatorDoorStatus(elevatorNumber));
                setCurrentFloor(elevatorService.getElevatorFloor(elevatorNumber));
                setCurrentPositionFt(elevatorService.getElevatorPosition(elevatorNumber));
                setCurrentSpeedFtPerSec(elevatorService.getElevatorSpeed(elevatorNumber));
                setWeight(elevatorService.getElevatorWeight(elevatorNumber));
                for(int i=0; i < floorCnt; i++)
                {
                    button[i] = elevatorService.getButton(elevatorNumber,i);
                }
                setFloorTarget(elevatorService.getTarget(elevatorNumber));
                boolean[] arr = new boolean[floorCnt];
                for(int i=0; i<floorCnt; i++)
                {
                    arr[i] = elevatorService.getServicesFloors(elevatorNumber,i);
                }
                setServicedFloors(arr);

                //TODO Modus setzen
                 */

                // update in Building
                /*
                for(int i = 0; i < floorCnt; i++)
                {
                    floorButtons[i].setButtonDown(elevatorService.getFloorButtonDown(i));
                    floorButtons[i].setButtonUp(elevatorService.getFloorButtonUp(i));
                }
                for(var elem : elevators)
                {
                    elem.Update();
                }
                 */
            }
        });

    }
    public Building getBuilding()
    {
        return this.building;
    }
}
