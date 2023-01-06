package at.fhhagenberg.sqe.model;

public class Elevator {

    /**
     * Committed Direction.
     */
    private int direction;

    /**
     * Elevator Acceleration.
     */
    private int acceleration;

    /**
     * Stop request button in the elevator.
     */
    private boolean[] button;

    /**
     * Current door status.
     */
    private int doorStatus;

    /**
     * Nearest floor.
     */
    private int currentFloor;

    /**
     * Position in feet from the bottom.
     */
    private int currentPositionFt;

    /**
     * Current speed in feet per second.
     */
    private int currentSpeedFtPerSec;

    /**
     * Weight in the elevator.
     */
    private int weight;

    /**
     * Defines if a floor is serviced by the elevator.
     */
    private boolean[] servicedFloors;

    /**
     * Target floor of this elevator.
     */
    private int floorTarget;

    /**
     * Number of the current elevator.
     */
    private final int elevatorNumber;

    /**
     * Maximum maxPayload of the elevator.
     */
    private final int maxPayload;

    /**
     * Define if automatic mode is enabled.
     */
    private boolean automaticMode;



    Elevator(int maximumPayload, int elevatorNumber, int floorCnt){
        this.maxPayload = maximumPayload;
        this.elevatorNumber = elevatorNumber;
        button = new boolean[floorCnt];
    }

    /*
    public void Update() throws RemoteException {
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
    }
    */

    public int getDirection() {
        return direction;
    }
    public void setDirection(int direction) {
        this.direction = direction;
    }


    public int getAcceleration() {
        return acceleration;
    }
    public void setAcceleration(int acceleration) {
        this.acceleration = acceleration;
    }


    public boolean[] getButton() {
        return button;
    }
    public void setButton(boolean[] button) {
        this.button = button;
    }


    public int getDoorStatus() {
        return doorStatus;
    }
    public void setDoorStatus(int doorStatus) {
        this.doorStatus = doorStatus;
    }


    public int getCurrentFloor() {
        return currentFloor;
    }
    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }


    public int getCurrentPositionFt() {
        return currentPositionFt;
    }
    public void setCurrentPositionFt(int currentPositionFt) {
        this.currentPositionFt = currentPositionFt;
    }


    public int getCurrentSpeedFtPerSec() {
        return currentSpeedFtPerSec;
    }
    public void setCurrentSpeedFtPerSec(int currentSpeedFtPerSec) {
        this.currentSpeedFtPerSec = currentSpeedFtPerSec;
    }


    public int getWeight() {
        return weight;
    }
    public void setWeight(int weight) {
        this.weight = weight;
    }


    public boolean[] getServicedFloors() {
        return servicedFloors;
    }
    public void setServicedFloors(boolean[] servicedFloors) {
        this.servicedFloors = servicedFloors;
    }


    public int getFloorTarget() {
        return floorTarget;
    }
    public void setFloorTarget(int floorTarget) {
        this.floorTarget = floorTarget;
    }


    public int getMaxPayload() {
        return maxPayload;
    }


    public boolean getAutomaticMode() { return automaticMode; }
    public void setAutomaticMode(boolean automaticMode) { this.automaticMode = automaticMode; }
}
