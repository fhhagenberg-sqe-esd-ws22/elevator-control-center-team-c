package at.fhhagenberg.sqe.model;

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
        this.maxPayload = maximumPayload;
        currentFloor = new SimpleStringProperty();
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
        this.direction = direction;
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
        this.doorStatus = doorStatus;
    }


    public StringProperty getCurrentFloor() {
        return currentFloor;
    }
    public void setCurrentFloor(int currentFloor) {
        this.currentFloor.setValue(Integer.toString(currentFloor));
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


    public BooleanProperty getFloorTarget(int number) {
        if (number > 0 && number < servicedFloor.size())
        {
            return floorTarget.elementAt(number);
        }
        return new SimpleBooleanProperty(Boolean.FALSE);

    }
    public void setFloorTarget(int floorTarget) {

        for(int i=0; i<this.floorTarget.size(); i++)
        {
            if(i==floorTarget)
            {
                this.floorTarget.get(i).setValue(true);
            }
            else
            {
                this.floorTarget.get(i).setValue(false);
            }
        }
    }


    public int getMaxPayload() {
        return maxPayload;
    }


    public boolean getAutomaticMode() { return automaticMode; }
    public void setAutomaticMode(boolean automaticMode) { this.automaticMode = automaticMode; }
}
