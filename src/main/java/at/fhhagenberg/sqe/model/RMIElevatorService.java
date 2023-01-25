package at.fhhagenberg.sqe.model;
import sqelevator.IElevator;
import at.fhhagenberg.sqe.interfaces.IElevatorService;

import java.rmi.RemoteException;

public class RMIElevatorService implements IElevatorService {

    private IElevator elevatorService;

    public RMIElevatorService(IElevator service) {
        elevatorService = service;
    }

    public int getCommittedDirection(int elevatorNumber) {
        try {
            return elevatorService.getCommittedDirection(elevatorNumber);
        }
        catch (RemoteException e)
        {
            return IElevator.ELEVATOR_DIRECTION_UNCOMMITTED;
        }
    }

    public int getElevatorAccel(int elevatorNumber) {
        try {
            return elevatorService.getElevatorAccel(elevatorNumber);
        }
        catch (RemoteException e)
        {
            return 0;
        }
    }

    public boolean getElevatorButton(int elevatorNumber, int floor) {
        try {
            return elevatorService.getElevatorButton(elevatorNumber, floor);
        }
        catch (RemoteException e)
        {
            return false;
        }
    }

    public int getElevatorDoorStatus(int elevatorNumber) {
        try {
            return elevatorService.getElevatorDoorStatus(elevatorNumber);
        }
        catch (RemoteException e)
        {
            return 0;
        }
    }

    public int getElevatorFloor(int elevatorNumber) {
        try {
            return elevatorService.getElevatorFloor(elevatorNumber);
        }
        catch (RemoteException e)
        {
            return 0;
        }
    }

    public int getElevatorNum() {
        try {
            return elevatorService.getElevatorNum();
        }
        catch (RemoteException e)
        {
            return 0;
        }
    }

    public int getElevatorPosition(int elevatorNumber) {
        try {
            return elevatorService.getElevatorPosition(elevatorNumber);
        }
        catch (RemoteException e)
        {
            return 0;
        }
    }

    public int getElevatorSpeed(int elevatorNumber) {
        try {
            return elevatorService.getElevatorSpeed(elevatorNumber);
        }
        catch (RemoteException e)
        {
            return 0;
        }
    }

    public int getElevatorWeight(int elevatorNumber) {
        try {
            return elevatorService.getElevatorWeight(elevatorNumber);
        }
        catch (RemoteException e)
        {
            return 0;
        }
    }

    public int getElevatorCapacity(int elevatorNumber) {
        try {
            return elevatorService.getElevatorCapacity(elevatorNumber);
        }
        catch (RemoteException e)
        {
            return 0;
        }
    }

    public boolean getFloorButtonDown(int floor) {
        try {
            return elevatorService.getFloorButtonDown(floor);
        }
        catch (RemoteException e)
        {
            return false;
        }
    }

    public boolean getFloorButtonUp(int floor) {
        try {
            return elevatorService.getFloorButtonUp(floor);
        }
        catch (RemoteException e)
        {
            return false;
        }
    }

    public int getFloorHeight() {
        try {
            return elevatorService.getFloorHeight();
        }
        catch (RemoteException e)
        {
            return 0;
        }
    }

    public int getFloorNum() {
        try {
            return elevatorService.getFloorNum();
        }
        catch (RemoteException e)
        {
            return 0;
        }
    }

    public boolean getServicesFloors(int elevatorNumber, int floor) {
        try {
            return elevatorService.getServicesFloors(elevatorNumber, floor);
        }
        catch (RemoteException e)
        {
            return false;
        }
    }

    public int getTarget(int elevatorNumber) {
        try {
            return elevatorService.getTarget(elevatorNumber);
        }
        catch (RemoteException e)
        {
            return 0;
        }
    }

    public void setCommittedDirection(int elevatorNumber, int direction) {
        try {
            elevatorService.setCommittedDirection(elevatorNumber, direction);
        }
        catch (RemoteException e)
        {
            return;
        }
    }

    public void setServicesFloors(int elevatorNumber, int floor, boolean service) {
        try {
            elevatorService.setServicesFloors(elevatorNumber, floor, service);
        }
        catch (RemoteException e)
        {
            return;
        }
    }

    public void setTarget(int elevatorNumber, int target) {
        try {
            elevatorService.setTarget(elevatorNumber, target);
        }
        catch (RemoteException e)
        {
            return;
        }
    }

    public long getClockTick() {
        try {
            return elevatorService.getClockTick();
        }
        catch (RemoteException e)
        {
            return 0;
        }
    }
}
