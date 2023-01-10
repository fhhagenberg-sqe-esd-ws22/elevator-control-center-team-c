package at.fhhagenberg.sqe.viewmodel;

import at.fhhagenberg.sqe.IElevator;
import at.fhhagenberg.sqe.delete.IElevatorMock;
import at.fhhagenberg.sqe.interfaces.IElevatorService;
import at.fhhagenberg.sqe.model.Building;
import at.fhhagenberg.sqe.model.Elevator;
import at.fhhagenberg.sqe.model.RMIElevatorService;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ECCViewModel {

    // TODO create all properties you need to show
    private StringProperty testLabel;

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

        createElevatorService(new IElevatorMock());

        testLabel = new SimpleStringProperty();
        testLabel.setValue("Testoutput stands here!");
    }



    public StringProperty getTestLabel() { return testLabel; }


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

                for (int idx = 0; idx < elevatorNum; idx++) {
                    Elevator elevator = new Elevator(maxPayload,floorNum);
                    building.addElevator(elevator);
                }
            }
        });
    }

    private int cnt = 0;    // TODO delete (just for testing

    public void update() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                testLabel.setValue("called: " + cnt++);

                boolean elevatorButton = elevatorService.getElevatorButton(10,10);

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
}
