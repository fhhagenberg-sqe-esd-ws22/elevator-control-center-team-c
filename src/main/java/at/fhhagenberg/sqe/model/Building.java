package at.fhhagenberg.sqe.model;

import java.util.Vector;

public class Building {

    /**
     * List of all elevators in the building.
     */
    private final Vector<Elevator> elevators;

    /**
     * Height of one floor.
     */
    private int floorHeight;

    /**
     * Status of the floor buttons.
     */
    private final Vector<FloorButton> floorButtons;



    public Building() {
        elevators = new Vector<>();
        floorButtons = new Vector<>();
    }


    public Elevator getElevator(int number) {
        if (number >= 0 && number < elevators.size()) {
            return elevators.get(number);
        }
        return null;
    }
    public void addElevator(Elevator elevator) {
        elevators.add(elevator);
    }


    public int getElevatorNum() { return elevators.size(); }


    public FloorButton getFloorButton(int floor) {
        if (floor >= 0 && floor < floorButtons.size()) {
            return floorButtons.get(floor);
        }
        return null;
    }
    public void addFloorButton(FloorButton button) {
        floorButtons.add(button);
    }


    public int getFloorHeight() { return floorHeight; }
    public void setFloorHeight(int floorHeight) { this.floorHeight = floorHeight; }


    public int getFloorNum() { return floorButtons.size(); }
}
