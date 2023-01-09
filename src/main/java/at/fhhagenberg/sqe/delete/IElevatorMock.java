package at.fhhagenberg.sqe.delete;

import at.fhhagenberg.sqe.IElevator;

import java.rmi.RemoteException;

public class IElevatorMock implements IElevator {
    @Override
    public int getCommittedDirection(int elevatorNumber) throws RemoteException {
        return 0;
    }

    @Override
    public int getElevatorAccel(int elevatorNumber) throws RemoteException {
        return 0;
    }

    @Override
    public boolean getElevatorButton(int elevatorNumber, int floor) throws RemoteException {
        return false;
    }

    @Override
    public int getElevatorDoorStatus(int elevatorNumber) throws RemoteException {
        return 0;
    }

    @Override
    public int getElevatorFloor(int elevatorNumber) throws RemoteException {
        return 0;
    }

    @Override
    public int getElevatorNum() throws RemoteException {
        return 0;
    }

    @Override
    public int getElevatorPosition(int elevatorNumber) throws RemoteException {
        return 0;
    }

    @Override
    public int getElevatorSpeed(int elevatorNumber) throws RemoteException {
        return 0;
    }

    @Override
    public int getElevatorWeight(int elevatorNumber) throws RemoteException {
        return 0;
    }

    @Override
    public int getElevatorCapacity(int elevatorNumber) throws RemoteException {
        return 0;
    }

    @Override
    public boolean getFloorButtonDown(int floor) throws RemoteException {
        return false;
    }

    @Override
    public boolean getFloorButtonUp(int floor) throws RemoteException {
        return false;
    }

    @Override
    public int getFloorHeight() throws RemoteException {
        return 0;
    }

    @Override
    public int getFloorNum() throws RemoteException {
        return 0;
    }

    @Override
    public boolean getServicesFloors(int elevatorNumber, int floor) throws RemoteException {
        return false;
    }

    @Override
    public int getTarget(int elevatorNumber) throws RemoteException {
        return 0;
    }

    @Override
    public void setCommittedDirection(int elevatorNumber, int direction) throws RemoteException {

    }

    @Override
    public void setServicesFloors(int elevatorNumber, int floor, boolean service) throws RemoteException {

    }

    @Override
    public void setTarget(int elevatorNumber, int target) throws RemoteException {

    }

    @Override
    public long getClockTick() throws RemoteException {
        return 0;
    }
}