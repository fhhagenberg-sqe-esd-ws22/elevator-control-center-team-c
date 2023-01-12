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
    private Boolean initialized;
    protected IElevatorService elevatorService;


    /**
     * CTor.
     * @param building
     */
    public ECCViewModel(Building building) {

        this.building = building;
        this.initialized = Boolean.FALSE;

        //this.building = new Building();
        //this.building.setFloorHeight(10);
        int floorCnt = 8;
        int elevatorCnt = 3;
        /*for(int i =0; i<floorCnt;i++)
        {
            var button = new FloorButton();
            button.setButtonUp(false);
            button.setButtonDown(true);
            this.building.addFloorButton(button);
        }
        for (int i = 0; i< elevatorCnt; i++)
        {
            Elevator elevator = new Elevator(1000,floorCnt);
            elevator.setDirection(1);
            elevator.setAcceleration(100);
            boolean val = true;
            for(int k=0; k<floorCnt; k++)
            {
                val = !val;
                elevator.setButton(k,val);
                elevator.setServicedFloor(k,true);
            }
            elevator.setAutomaticMode(true);
            elevator.setCurrentFloor(2);
            elevator.setWeight(700);
            elevator.setCurrentPositionFt(167);
            elevator.setCurrentSpeedFtPerSec(23);
            elevator.setDoorStatus(0);
            elevator.setFloorTarget(5);
            this.building.addElevator(elevator);
        }*/

        createElevatorService(new IElevatorMock(elevatorCnt,floorCnt));
    }

    protected void createElevatorService(IElevator elevator) {
        this.elevatorService = new RMIElevatorService(elevator);
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

                building.setFloorHeight(floorHeight);

                for (int idx = 0; idx < elevatorNum; idx++) {
                    Elevator elevator = new Elevator(maxPayload,floorNum);
                    building.addElevator(elevator);
                }
                for (int idx = 0; idx < floorNum; idx++) {
                    FloorButton floorButton = new FloorButton();
                    building.addFloorButton(floorButton);
                }

                initialized = Boolean.TRUE;
            }
        });
    }

    public void update() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                int elevatorNum = building.getElevatorNumINT();
                int floorNum = building.getFloorNum();

                for (int idxFloor = 0; idxFloor < floorNum; idxFloor++) {
                    building.getFloorButton(idxFloor).setButtonUp(elevatorService.getFloorButtonUp(idxFloor));
                    building.getFloorButton(idxFloor).setButtonDown(elevatorService.getFloorButtonDown(idxFloor));
                }

                for (int idxElevator = 0; idxElevator < elevatorNum; idxElevator++) {
                    Elevator elevator = building.getElevator(idxElevator);
                    elevator.setAcceleration(elevatorService.getElevatorAccel(idxElevator));
                    elevator.setCurrentFloor(elevatorService.getElevatorFloor(idxElevator));
                    elevator.setCurrentPositionFt(elevatorService.getElevatorPosition(idxElevator));
                    elevator.setCurrentSpeedFtPerSec(elevatorService.getElevatorSpeed(idxElevator));
                    elevator.setDirection(elevatorService.getCommittedDirection(idxElevator));
                    elevator.setDoorStatus(elevatorService.getElevatorDoorStatus(idxElevator));
                    elevator.setFloorTarget(elevatorService.getTarget(idxElevator));
                    elevator.setWeight(elevatorService.getElevatorWeight(idxElevator));

                    for (int idxFloor = 0; idxFloor < floorNum; idxFloor++) {
                        elevator.setButton(idxFloor, elevatorService.getElevatorButton(idxElevator, idxFloor));
                        elevator.setServicedFloor(idxFloor, elevatorService.getElevatorButton(idxElevator, idxFloor));
                    }
                }

                //elevator.setAutomaticMode();  // TODO set the automatic mode
            }
        });

    }

    public Building getBuilding()
    {
        return this.building;
    }

    public Boolean isInitialized() {
        return this.initialized;
    }
}
