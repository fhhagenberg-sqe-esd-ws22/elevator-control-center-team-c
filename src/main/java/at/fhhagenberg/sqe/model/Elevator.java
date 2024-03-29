package at.fhhagenberg.sqe.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;

public class Elevator {

    /**
     * Committed Direction.
     */
    private DirectionStatus direction;

    /**
     * Elevator Acceleration.
     */
    private int acceleration;

    /**
     * Stop request button in the elevator.
     */
    private ArrayList<BooleanProperty> button;

    /**
     * Current door status.
     */
    private DoorStatus doorStatus;

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
    private ArrayList<BooleanProperty> servicedFloor;
    private StringProperty servicedFloorStringProp;

    /**
     * Target floor of this elevator.
     */
    private ArrayList<BooleanProperty> floorTarget;
    private StringProperty floorTargetStringProp;

    /**
     * Maximum maxPayload of the elevator.
     */
    private final int maxPayload;

    /**
     * Define if automatic mode is enabled.
     */
    private StringProperty automaticMode;
    private BooleanProperty automaticModeBool;



    public Elevator(int maximumPayload, int floorCnt){
        direction = new DirectionStatus();
        doorStatus = new DoorStatus();
        button = new ArrayList<>();
        servicedFloor = new ArrayList<>();
        floorTarget = new ArrayList<>();
        servicedFloorStringProp = new SimpleStringProperty("");
        automaticMode = new SimpleStringProperty("OFF");
        automaticModeBool = new SimpleBooleanProperty(false);
        floorTargetStringProp = new SimpleStringProperty("");
        currentFloor = new SimpleStringProperty("0");
        currentPositionFt = new SimpleStringProperty("0 ft");
        currentSpeedFtPerSec = new SimpleStringProperty("0 ft/s");
        weight = new SimpleStringProperty("0 lbs");
        maxPayload = Math.max(maximumPayload, 0);
        for (int idx = 0; idx < floorCnt; idx++) {
            button.add(new SimpleBooleanProperty(Boolean.FALSE));
            servicedFloor.add(new SimpleBooleanProperty(Boolean.FALSE));
            floorTarget.add(new SimpleBooleanProperty(Boolean.FALSE));
        }
    }


    public DirectionStatus getDirection() {
        return direction;
    }
    public void setDirection(int status) {
        this.direction.setDirectionStatus(status);
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


    public DoorStatus getDoorStatus() {
        return this.doorStatus;
    }
    public void setDoorStatus(int status) {
        this.doorStatus.setDoorStatus(status);
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
        this.currentSpeedFtPerSec.setValue(currentSpeedFtPerSec + " ft/s");
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

    public StringProperty getServicedFloorStringProp()
    {
        return servicedFloorStringProp;
    }

    public void setServicedFloorStringProp()
    {
        StringBuilder test = new StringBuilder();
        boolean firstValReached = false;
        boolean flag = true;
        for(int i=0; i<servicedFloor.size(); i++)
        {
            if(Boolean.TRUE.equals(servicedFloor.get(i).getValue()))
            {

                if(firstValReached)
                {
                    test.append(", ");
                }
                test.append(i);
                firstValReached = true;
            }
            else
            {
                flag = false;
            }
        }
        if(flag)
        {
            servicedFloorStringProp.setValue("All floors");
        }
        else {
            servicedFloorStringProp.setValue(test.toString());
        }

    }
    public BooleanProperty getServicedFloor(int number) {
        if (number >= 0 && number < servicedFloor.size()) {
            return servicedFloor.get(number);
        }
        return new SimpleBooleanProperty(Boolean.FALSE);
    }
    public void setServicedFloor(int number, Boolean state) {
        if (number >= 0 && number < servicedFloor.size()) {
            servicedFloor.get(number).setValue(state);
        }
        setServicedFloorStringProp();
    }

    public StringProperty getFloorTargetStringProp()
    {
        return floorTargetStringProp;
    }
    public BooleanProperty getFloorTarget(int number) {
        if (number >= 0 && number < servicedFloor.size())
        {
            return floorTarget.get(number);
        }
        return new SimpleBooleanProperty(Boolean.FALSE);

    }
    public void setFloorTarget(int floorTarget) {
        this.floorTargetStringProp.setValue("");
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


    public BooleanProperty getAutomaticModeBool() { return automaticModeBool;}


    public StringProperty getAutomaticMode() { return automaticMode; }
    public void setAutomaticMode(boolean automaticMode) {
        this.automaticMode.setValue(automaticMode ? "ON" : "OFF");
        this.automaticModeBool.setValue(automaticMode);
    }

    public void resetTarget() {
        for (BooleanProperty booleanProperty : this.floorTarget) {
            booleanProperty.set(false);
        }
    }
}
