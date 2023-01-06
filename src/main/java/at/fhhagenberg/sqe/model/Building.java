package at.fhhagenberg.sqe.model;

import java.util.Vector;

public class Building {

    /**
     * List of all elevators in the building.
     */
    private Vector<Elevator> elevators;

    /**
     * Height of one floor.
     */
    private int floorHeight;

    /**
     * Status of the floor buttons.
     */
    private Vector<FloorButton> floorButtons;     // TODO may create a vector



    public Building() {
        elevators = new Vector<>();
        floorButtons = new Vector<>();
    }

    /*
    private void Init() throws RemoteException
    {
        elevatorCnt = elevatorService.getElevatorNum();
        floorHeight = elevatorService.getFloorHeight();
        floorCnt = elevatorService.getFloorNum();
        for (int i = 0; i < elevatorCnt; i++)
        {
            elevators.add(new Elevator(elevatorService.getElevatorCapacity(i),i,floorCnt));
        }
    }
    public void Update() throws RemoteException {
        for(int i = 0; i < floorCnt; i++)
        {
            floorButtons[i].setButtonDown(elevatorService.getFloorButtonDown(i));
            floorButtons[i].setButtonUp(elevatorService.getFloorButtonUp(i));
        }
        for(var elem : elevators)
        {
            elem.Update();
        }
    }
    */

    public Vector<Elevator> getElevators() { return elevators; }


    public int getElevatorNum() { return elevators.size(); }


    public Vector<FloorButton> getFloorButtons() { return floorButtons; }
    public void setFloorButtons(Vector<FloorButton> floorButtons) { this.floorButtons = floorButtons; }


    public int getFloorHeight() { return floorHeight; }
    public void setFloorHeight(int floorHeight) { this.floorHeight = floorHeight; }


    public int getFloorNum() { return floorButtons.size(); }
}
