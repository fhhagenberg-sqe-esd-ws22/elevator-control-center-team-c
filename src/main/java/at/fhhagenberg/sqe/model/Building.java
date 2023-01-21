package at.fhhagenberg.sqe.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Vector;

public class Building {

    /**
     * List of all elevators in the building.
     */
    private final Vector<Elevator> elevators;

    /**
     * Height of one floor.
     */
    private final StringProperty floorHeight;

    /**
     * Status of the floor buttons.
     */
    private final Vector<FloorButton> floorButtons;

    private final StringProperty elevatorCnt;


    public Building() {
        elevators = new Vector<>();
        floorButtons = new Vector<>();
        floorHeight = new SimpleStringProperty("0 ft");
        elevatorCnt = new SimpleStringProperty("0");
    }


    public Elevator getElevator(int number) {
        if (number >= 0 && number < elevators.size()) {
            return elevators.get(number);
        }
        return null;
    }
    public void addElevator(Elevator elevator) {
        elevators.add(elevator);
        elevatorCnt.setValue(Integer.toString(elevators.size()));
    }


    public StringProperty getElevatorNum() { return elevatorCnt; }
    public int getElevatorNumINT() { return elevators.size(); }


    public FloorButton getFloorButton(int floor) {
        if (floor >= 0 && floor < floorButtons.size()) {
            return floorButtons.get(floor);
        }
        return null;
    }
    public void addFloorButton(FloorButton button) {
        floorButtons.add(button);
    }


    public StringProperty getFloorHeight() { return floorHeight; }
    public void setFloorHeight(int floorHeight) {
        if (floorHeight > 0) {
            this.floorHeight.set(floorHeight + " ft");
        } else {
            this.floorHeight.set("0 ft");
        }
    }


    public int getFloorNum() { return floorButtons.size(); }
}
