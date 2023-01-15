package at.fhhagenberg.sqe.viewmodel;

import at.fhhagenberg.sqe.IElevator;
import at.fhhagenberg.sqe.mockobjects.IElevatorMock;
import at.fhhagenberg.sqe.interfaces.IElevatorService;
import at.fhhagenberg.sqe.model.Building;
import at.fhhagenberg.sqe.model.Elevator;
import at.fhhagenberg.sqe.model.FloorButton;
import at.fhhagenberg.sqe.model.RMIElevatorService;
import javafx.application.Platform;

/**
 * Class that handles the communication with the elevator Service
 * and the update process for the data structure.
 */
public class ECCViewModel {

    /**
     * Reference to the data structure.
     */
    private final Building building;

    /**
     * Flag to indicate if the building is already initialized.
     */
    private Boolean initialized;

    /**
     * Reference to the elevator service where the information is retrieved.
     */
    protected IElevatorService elevatorService;


    /**
     * CTor.
     * @param building
     */
    public ECCViewModel(Building building) {
        this.building = building;
        this.initialized = Boolean.FALSE;

        int floorCnt = 8;
        int elevatorCnt = 5;
        createElevatorService(new IElevatorMock(elevatorCnt,floorCnt));     // TODO change the interface and may the location of initialization
    }

    /**
     * Create an elevator service.
     * @param service
     */
    protected void createElevatorService(IElevator service) {
        this.elevatorService = new RMIElevatorService(service);
    }

    /**
     * Initialize the structure of one building. Should be called only once.
     */
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

    /**
     * Update content of the building.
     */
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

    /**
     * Retrieve the building.
     * @return
     */
    public Building getBuilding()
    {
        return this.building;
    }

    /**
     * Flag to check if the building is already initialized.
     * @return
     */
    public Boolean isInitialized() {
        return this.initialized;
    }

    /**
     * Send a committed direction to the service.
     * @param elevatorNumber
     * @param direction
     */
    public void setCommittedDirection(int elevatorNumber, int direction) {
        Elevator elevator = this.building.getElevator(elevatorNumber);
        if (elevator != null) {
            elevator.setDirection(direction);
            elevatorService.setCommittedDirection(elevatorNumber, direction);
        }
    }

    /**
     * Send a serviced floor to the service.
     * @param elevatorNumber
     * @param floor
     * @param service
     */
    public void setServicesFloors(int elevatorNumber, int floor, boolean service) {
        Elevator elevator = this.building.getElevator(elevatorNumber);
        if (elevator != null) {
            elevator.setServicedFloor(floor, service);
            elevatorService.setServicesFloors(elevatorNumber, floor, service);
        }
    }

    /**
     * Send a new target floor to the service.
     * @param elevatorNumber
     * @param target
     */
    public void setTarget(int elevatorNumber, int target) {
        Elevator elevator = this.building.getElevator(elevatorNumber);
        if (elevator != null) {
            elevator.setFloorTarget(target);
            elevatorService.setTarget(elevatorNumber, target);
        }
    }
}
