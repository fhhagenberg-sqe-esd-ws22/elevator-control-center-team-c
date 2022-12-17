package at.fhhagenberg.sqe.model;

import java.rmi.RemoteException;

public class ElevatorInfo {
    private Elevator elevatorService = new Elevator();
    private int direction;
    private boolean[] servicedFloors;
    private int acceleration;
    private boolean mode;
    private boolean[] stopRequests;
    private int doorStatus;
    private int currentFloor;
    private int currentPositionFt;
    private int currentSpeedFtPerSec;
    private int weight;
    private final int capacity;
    private int floorTarget;
    private int floorCnt;
    private final int elevatorNumber;
    ElevatorInfo(int capacity, int elevatorNumber, int floorCnt){
        this.capacity = capacity;
        this.elevatorNumber = elevatorNumber;
        this.floorCnt = floorCnt;
        stopRequests = new boolean[floorCnt];
    }
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
            stopRequests[i] = elevatorService.getElevatorButton(elevatorNumber,i);
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


    public boolean isMode() {
        return mode;
    }

    public void setMode(boolean mode) {
        this.mode = mode;
    }

    public boolean[] getStopRequests() {
        return stopRequests;
    }

    public void setStopRequests(boolean[] stopRequests) {
        this.stopRequests = stopRequests;
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

    public int getCapacity() {
        return capacity;
    }

    public int getFloorTarget() {
        return floorTarget;
    }
    public boolean[] getServicedFloors() {
        return servicedFloors;
    }
    public void setServicedFloors(boolean[] servicedFloors) {
        this.servicedFloors = servicedFloors;
    }
    public void setFloorTarget(int floorTarget) {
        this.floorTarget = floorTarget;
    }
}
