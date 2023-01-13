package at.fhhagenberg.sqe.model;

import at.fhhagenberg.sqe.IElevator;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

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
    private Vector<BooleanProperty> button;

    /**
     * Current door status.
     */
    private int doorStatus;

    /**
     * Nearest floor.
     */
    private StringProperty currentFloor;

    /**
     * Position in feet from the bottom.
     */
    private StringProperty currentPositionFt;

    /**
     * Current speed in feet per second.
     */
    private StringProperty currentSpeedFtPerSec;

    /**
     * Weight in the elevator.
     */
    private StringProperty weight;

    /**
     * Defines if a floor is serviced by the elevator.
     */
    private Vector<Boolean> servicedFloor;

    /**
     * Target floor of this elevator.
     */
    private Vector<BooleanProperty> floorTarget;

    /**
     * Maximum maxPayload of the elevator.
     */
    private final int maxPayload;

    /**
     * Define if automatic mode is enabled.
     */
    private boolean automaticMode;



    public Elevator(int maximumPayload, int floorCnt){

        maxPayload = Math.max(maximumPayload, 0);
        direction = IElevator.ELEVATOR_DIRECTION_UNCOMMITTED;
        doorStatus = IElevator.ELEVATOR_DOORS_OPEN;
        currentFloor = new SimpleStringProperty("0");
        currentPositionFt = new SimpleStringProperty("0 ft");
        currentSpeedFtPerSec = new SimpleStringProperty("0 ft/s");
        weight = new SimpleStringProperty("0 lbs");

        button = new Vector<>();
        servicedFloor = new Vector<>();
        floorTarget = new Vector<>();
        for (int idx = 0; idx < floorCnt; idx++) {
            button.add(new SimpleBooleanProperty(Boolean.FALSE));
            servicedFloor.add(Boolean.FALSE);
            floorTarget.add(new SimpleBooleanProperty(Boolean.FALSE));
        }
    }
    public int getDirection() {
        return direction;
    }
    public void setDirection(int direction) {
        if (direction == IElevator.ELEVATOR_DIRECTION_UP ||
            direction == IElevator.ELEVATOR_DIRECTION_DOWN ||
            direction == IElevator.ELEVATOR_DIRECTION_UNCOMMITTED) {
            this.direction = direction;
        } else {
            this.direction = IElevator.ELEVATOR_DIRECTION_UNCOMMITTED;
        }
    }


    public int getAcceleration() {
        return acceleration;
    }
    public void setAcceleration(int acceleration) {
        this.acceleration = acceleration;
    }


    public BooleanProperty getButton(int number) {
        if (number >= 0 && number < button.size()) {
            return button.get(number);
        }
        return new SimpleBooleanProperty(Boolean.FALSE);
    }
    public void setButton(int number, Boolean state) {
        if (number >= 0 && number < button.size()) {
            button.get(number).setValue(state);
        }
    }


    public int getDoorStatus() {
        return doorStatus;
    }
    public void setDoorStatus(int doorStatus) {
        if (doorStatus == IElevator.ELEVATOR_DOORS_OPEN ||
            doorStatus == IElevator.ELEVATOR_DOORS_CLOSED ||
            doorStatus == IElevator.ELEVATOR_DOORS_OPENING ||
            doorStatus == IElevator.ELEVATOR_DOORS_CLOSING) {
            this.doorStatus = doorStatus;
        } else {
            this.doorStatus = IElevator.ELEVATOR_DOORS_OPEN;
        }
    }


    public StringProperty getCurrentFloor() {
        return currentFloor;
    }
    public void setCurrentFloor(int currentFloor) {
        if (currentFloor >= 0 && currentFloor < button.size()) {
            this.currentFloor.setValue(Integer.toString(currentFloor));
        } else {
            this.currentFloor.setValue(Integer.toString(0));
        }
    }


    public StringProperty getCurrentPositionFt() {
        return currentPositionFt;
    }
    public void setCurrentPositionFt(int currentPositionFt) {
        if (currentPositionFt >= 0) {
            this.currentPositionFt.setValue(currentPositionFt + " ft");
        } else {
            this.currentPositionFt.setValue("0 ft");
        }
    }


    public StringProperty getCurrentSpeedFtPerSec() {
        return currentSpeedFtPerSec;
    }
    public void setCurrentSpeedFtPerSec(int currentSpeedFtPerSec) {
        if (currentSpeedFtPerSec >= 0) {
            this.currentSpeedFtPerSec.setValue(currentSpeedFtPerSec + " ft/s");
        } else {
            this.currentSpeedFtPerSec.setValue("0 ft/s");
        }
    }


    public StringProperty getWeight() {
        return weight;
    }
    public void setWeight(int weight) {
        if (weight >= 0) {
            this.weight.setValue(weight + " lbs");
        } else {
            this.weight.setValue("0 lbs");
        }
    }


    public Boolean getServicedFloor(int number) {
        if (number >= 0 && number < servicedFloor.size()) {
            return servicedFloor.get(number);
        }
        return Boolean.FALSE;
    }
    public void setServicedFloor(int number, Boolean state) {
        if (number >= 0 && number < servicedFloor.size()) {
            servicedFloor.set(number, state);
        }
    }


    public BooleanProperty getFloorTarget(int number) {
        if (number >= 0 && number < servicedFloor.size())
        {
            return floorTarget.elementAt(number);
        }
        return new SimpleBooleanProperty(Boolean.FALSE);

    }
    public void setFloorTarget(int floorTarget) {

        for(int i = 0; i < this.floorTarget.size(); i++)
        {
            this.floorTarget.get(i).setValue(i == floorTarget);
        }
    }


    public int getMaxPayload() {
        return maxPayload;
    }


    public boolean getAutomaticMode() { return automaticMode; }
    public void setAutomaticMode(boolean automaticMode) { this.automaticMode = automaticMode; }
}
