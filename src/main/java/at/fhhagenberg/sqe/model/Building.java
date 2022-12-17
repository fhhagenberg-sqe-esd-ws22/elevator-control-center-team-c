package at.fhhagenberg.sqe.model;

import java.rmi.RemoteException;
import java.util.Vector;

public class Building {
    public Building() throws RemoteException {
        Elevators = new Vector<>();
        Init();
    }
    private final Elevator elevatorService = new Elevator();
    private void Init() throws RemoteException
    {
        elevatorCnt = elevatorService.getElevatorNum();
        floorHeight = elevatorService.getFloorHeight();
        floorCnt = elevatorService.getFloorNum();
        for (int i = 0; i < elevatorCnt; i++)
        {
            Elevators.add(new ElevatorInfo(elevatorService.getElevatorCapacity(i),i,floorCnt));
        }
    }
    public void Update() throws RemoteException {
        for(int i = 0; i < floorCnt; i++)
        {
            floorButtons[i].setButtonDown(elevatorService.getFloorButtonDown(i));
            floorButtons[i].setButtonUp(elevatorService.getFloorButtonUp(i));
        }
    }
    private Vector<ElevatorInfo> Elevators;
    private int elevatorCnt;
    private int floorHeight;
    private int floorCnt;
    private FloorButton[] floorButtons;
}
