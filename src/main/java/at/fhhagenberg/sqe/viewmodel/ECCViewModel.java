package at.fhhagenberg.sqe.viewmodel;

import sqelevator.IElevator;
import at.fhhagenberg.sqe.interfaces.IElevatorService;
import at.fhhagenberg.sqe.model.Building;
import at.fhhagenberg.sqe.model.Elevator;
import at.fhhagenberg.sqe.model.FloorButton;
import at.fhhagenberg.sqe.model.RMIElevatorService;
import javafx.application.Platform;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

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
     * @param building holds elevator data
     */
    public ECCViewModel(Building building) {
        this.building = building;
        this.initialized = Boolean.FALSE;
    }

    /**
     * Create an elevator service.
     */
    protected boolean createElevatorService() {
        try {
            IElevator service = (IElevator) Naming.lookup("rmi://localhost/ElevatorSim");
            this.elevatorService = new RMIElevatorService(service);
        } catch (NotBoundException | MalformedURLException | RemoteException e) {
            return false;
        }
        return true;
    }

    /**
     * Interval trying to connect to via RMI.
     */
    private static final int CONNECTING_INTERVAL_MILLIS = 500;

    class InitMethod implements Runnable {
        @Override
        public void run() {

            // do the RMI connection
            while (!createElevatorService()) {
                try {
                    Thread.sleep(CONNECTING_INTERVAL_MILLIS);
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
            }

            // retrieve data
            int elevatorNum = elevatorService.getElevatorNum();
            int floorHeight = elevatorService.getFloorHeight();
            int floorNum = elevatorService.getFloorNum();

            int maxPayload = 5000;

            building.setFloorHeight(floorHeight);

            // fill structure
            for (int idx = 0; idx < elevatorNum; idx++) {
                Elevator elevator = new Elevator(maxPayload,floorNum);
                building.addElevator(elevator);
            }
            for (int idx = 0; idx < floorNum; idx++) {
                FloorButton floorButton = new FloorButton();
                building.addFloorButton(floorButton);
            }
            for(int idxElev=0; idxElev<elevatorNum; idxElev++)
            {
                for (int idxFloor = 0; idxFloor < floorNum; idxFloor++) {

                    elevatorService.setServicesFloors(idxElev,idxFloor,true);
                }
            }


            // indicate that the data structure is initialized
            initialized = Boolean.TRUE;
        }
    }

    /**
     * Initialize the structure of one building. Should be called only once.
     */
    public void init() {
        Platform.runLater(new InitMethod());
    }


    class UpdateMethod implements Runnable {

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
                    elevator.setFloorTarget(elevatorService.getTarget(idxElevator));
                    elevator.setDirection(elevatorService.getCommittedDirection(idxElevator));

                    if (elevatorService.getTarget(idxElevator) == elevatorService.getElevatorFloor(idxElevator) && elevatorService.getElevatorDoorStatus(idxElevator) == IElevator.ELEVATOR_DOORS_OPEN) {
                        elevatorService.setCommittedDirection(idxElevator, IElevator.ELEVATOR_DIRECTION_UNCOMMITTED);
                        elevator.resetTarget();
                    }
                    elevator.setDoorStatus(elevatorService.getElevatorDoorStatus(idxElevator));
                    elevator.setWeight(elevatorService.getElevatorWeight(idxElevator));

                    for (int idxFloor = 0; idxFloor < floorNum; idxFloor++) {
                        elevator.setButton(idxFloor, elevatorService.getElevatorButton(idxElevator, idxFloor));
                        elevator.setServicedFloor(idxFloor, elevatorService.getServicesFloors(idxElevator,idxFloor));
                    }
                }
                building.setFloorHeight(elevatorService.getFloorHeight());
        }
    }

    /**
     * Update content of the building.
     */
    public void update() {
        Platform.runLater(new UpdateMethod());
    }

    /**
     * Retrieve the building.
     * @return building
     */
    public Building getBuilding()
    {
        return this.building;
    }

    /**
     * Flag to check if the building is already initialized.
     * @return true if initialized
     */
    public Boolean isInitialized() {
        return this.initialized;
    }

    /**
     * Send a committed direction to the service.
     * @param elevatorNumber elevator
     * @param direction up, down, undefined
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
     * @param elevatorNumber elevator num
     * @param floor number of floor
     * @param service set service floor
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
     * @param elevatorNumber elevator num
     * @param target desired floor
     */
    public void setTarget(int elevatorNumber, int target) {
        Elevator elevator = this.building.getElevator(elevatorNumber);
        if (elevator != null) {
            elevator.setFloorTarget(target);
            elevatorService.setTarget(elevatorNumber, target);
        }
    }
}
