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
    private Vector<BooleanProperty> direction;

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
    private Vector<BooleanProperty> doorStatus;

    private BooleanProperty opening_closing_doorStatus;

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
    private StringProperty automaticMode;
    private BooleanProperty automaticMode_bool;

    private StringProperty floorTargetStringProp;

    public Elevator(int maximumPayload, int floorCnt){

        maxPayload = Math.max(maximumPayload, 0);
        for(int i=0; i<3; i++)
        {
            direction.elementAt(i).setValue(false);

        }
        for(int i=0; i<4; i++)
        {
            doorStatus.elementAt(i).setValue(false);
        }
        doorStatus.elementAt(0).setValue(true);
        currentFloor = new SimpleStringProperty("0");
        currentPositionFt = new SimpleStringProperty("0 ft");
        currentSpeedFtPerSec = new SimpleStringProperty("0 ft/s");
        weight = new SimpleStringProperty("0 lbs");
        currentFloor = new SimpleStringProperty();
        automaticMode_bool = new SimpleBooleanProperty();
        doorStatus = new Vector<>();
        automaticMode = new SimpleStringProperty();
        currentPositionFt = new SimpleStringProperty();
        currentSpeedFtPerSec = new SimpleStringProperty();
        opening_closing_doorStatus = new SimpleBooleanProperty();
        floorTargetStringProp = new SimpleStringProperty();
        weight = new SimpleStringProperty();
        button = new Vector<>();
        servicedFloor = new Vector<>();
        floorTarget = new Vector<>();
        direction = new Vector<>();
        for (int idx = 0; idx < floorCnt; idx++) {
            button.add(new SimpleBooleanProperty(Boolean.FALSE));
            servicedFloor.add(Boolean.FALSE);
            floorTarget.add(new SimpleBooleanProperty(Boolean.FALSE));
        }
        for(int i=0; i<4; i++)
        {
            doorStatus.add(new SimpleBooleanProperty(Boolean.FALSE));
        }
        for(int i=0; i<3; i++)
        {
            direction.add(new SimpleBooleanProperty(Boolean.FALSE));
        }
    }
    public BooleanProperty getDirection(int val) {
        if(val<3)
        {
            return direction.elementAt(val);
        }
        return new SimpleBooleanProperty(Boolean.FALSE);
    }
    public void setDirection(int direction) {
        if (direction == IElevator.ELEVATOR_DIRECTION_UP ||
            direction == IElevator.ELEVATOR_DIRECTION_DOWN ||
            direction == IElevator.ELEVATOR_DIRECTION_UNCOMMITTED) {
            for(int i=0; i<3; i++)
            {
                this.direction.elementAt(i).setValue(i==direction);
            }
        } else {
            for(int i=0; i<3; i++)
            {
                this.direction.elementAt(i).setValue(i==direction);
            }
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


    public BooleanProperty getDoorStatus(int position) {
        if(position < 4)
        {
            return doorStatus.elementAt(position);
        }
        else
            return new SimpleBooleanProperty(Boolean.FALSE);
    }
    public void setDoorStatus(int doorStatus) {
        if (doorStatus == IElevator.ELEVATOR_DOORS_OPEN ||
            doorStatus == IElevator.ELEVATOR_DOORS_CLOSED ||
            doorStatus == IElevator.ELEVATOR_DOORS_OPENING ||
            doorStatus == IElevator.ELEVATOR_DOORS_CLOSING) {
            for(int i=0; i<4; i++)
            {
                this.doorStatus.elementAt(i).setValue((i+1) == doorStatus);
            }
            if(this.doorStatus.elementAt(IElevator.ELEVATOR_DOORS_OPENING - 1).getValue()
                    || this.doorStatus.elementAt(IElevator.ELEVATOR_DOORS_CLOSING -1).getValue())
            {
                opening_closing_doorStatus.setValue(Boolean.TRUE);
            }
            else
            {
                opening_closing_doorStatus.setValue(Boolean.FALSE);
            }
        } else {
            // TODO Default wert setzen
            for(int i=0; i<4; i++)
            {
                this.doorStatus.elementAt(i).setValue((i+1) == doorStatus);
            }
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

    public StringProperty getFloorTargetStringProp()
    {
        return floorTargetStringProp;
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
            if(i==floorTarget)
            {
                this.floorTargetStringProp.setValue(Integer.toString(floorTarget));
            }
        }
    }


    public int getMaxPayload() {
        return maxPayload;
    }

    public BooleanProperty getAutomaticMode_bool() { return automaticMode_bool;}

    public StringProperty getAutomaticMode() { return automaticMode; }
    public void setAutomaticMode(boolean automaticMode) {
        this.automaticMode.setValue(automaticMode ? "ON" : "OFF");
        this.automaticMode_bool.setValue(automaticMode);
    }
}
