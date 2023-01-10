package at.fhhagenberg.sqe.model;

import java.util.Vector;

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
    private Vector<Boolean> button;

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
    private Vector<Boolean> servicedFloor;

    /**
     * Target floor of this elevator.
     */
    private int floorTarget;

    /**
     * Maximum maxPayload of the elevator.
     */
    private final int maxPayload;

    /**
     * Define if automatic mode is enabled.
     */
    private boolean automaticMode;



    public Elevator(int maximumPayload, int floorCnt){
        this.maxPayload = maximumPayload;
        if (floorCnt > 0) {
            button = new Vector<>(floorCnt);
            servicedFloor = new Vector<>(floorCnt);
        }
        else {
            button = new Vector<>();
            servicedFloor = new Vector<>();
        }
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


    public Boolean getButton(int number) {
        if (number > 0 && number < button.size()) {
            return button.get(number);
        }
        return Boolean.FALSE;
    }
    public void setButton(int number, Boolean state) {
        if (number > 0 && number < button.size()) {
            button.set(number, state);
        }
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


    public Boolean getServicedFloor(int number) {
        if (number > 0 && number < servicedFloor.size()) {
            return servicedFloor.get(number);
        }
        return Boolean.FALSE;
    }
    public void setServicedFloor(int number, Boolean state) {
        if (number > 0 && number < servicedFloor.size()) {
            servicedFloor.set(number, state);
        }
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
