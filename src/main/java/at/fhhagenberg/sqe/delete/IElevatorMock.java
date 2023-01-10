package at.fhhagenberg.sqe.delete;

import at.fhhagenberg.sqe.IElevator;

import java.rmi.RemoteException;
import java.util.Vector;

public class IElevatorMock implements IElevator {

    public IElevatorMock(int maxElevatorsCnt, int maxElevatorFloorsCnt)
    {
        mCommittedDirection = new int[maxElevatorsCnt];
        mTarget = new int[maxElevatorsCnt];
        mServicesFloors = new boolean[maxElevatorsCnt][maxElevatorFloorsCnt];
    }


    private int[] mCommittedDirection;
    private int mElevatorAccel;
    private boolean mElevatorButton;
    private int mElevatorDoorStatus;
    private int mElevatorFloor;
    private int mElevatorNum;
    private int mElevatorPosition;
    private int mElevatorSpeed;
    private int mElevatorWeight;
    private int mElevatorCapacity;
    private boolean mFloorButtonDown;
    private boolean mFloorButtonUp;
    private int mFloorHeight;
    private int mFloorNum;
    private boolean[][] mServicesFloors;

    private int[] mTarget;

    private long mClockTick;

    @Override
    public int getCommittedDirection(int elevatorNumber) throws RemoteException {
        return mCommittedDirection[elevatorNumber];
    }
    @Override
    public void setCommittedDirection(int elevatorNumber, int direction) throws RemoteException {
        mCommittedDirection[elevatorNumber] = direction;
    }

    @Override
    public int getElevatorAccel(int elevatorNumber) throws RemoteException {
        return mElevatorAccel;
    }
    public void setElevatorAccel (int elevatorAccel)
    {
        mElevatorAccel = elevatorAccel;
    }

    @Override
    public boolean getElevatorButton(int elevatorNumber, int floor) throws RemoteException {
        return mElevatorButton;
    }
    public void setElevatorButton (boolean elevatorButton)
    {
        mElevatorButton = elevatorButton;
    }

    @Override
    public int getElevatorDoorStatus(int elevatorNumber) throws RemoteException {
        return mElevatorDoorStatus;
    }
    public void setElevatorDoorStatus(int elevatorDoorStatus)
    {
        mElevatorDoorStatus = elevatorDoorStatus;
    }

    @Override
    public int getElevatorFloor(int elevatorNumber) throws RemoteException {
        return mElevatorFloor;
    }
    public void setElevatorFloor(int elevatorFloor)
    {
        mElevatorFloor = elevatorFloor;
    }

    @Override
    public int getElevatorNum() throws RemoteException {
        return mElevatorNum;
    }
    public void setElevatorNum(int elevatorNum)
    {
        mElevatorNum = elevatorNum;
    }

    @Override
    public int getElevatorPosition(int elevatorNumber) throws RemoteException {
        return mElevatorPosition;
    }
    public void setElevatorPosition(int elevatorPosition)
    {
        mElevatorPosition = elevatorPosition;
    }

    @Override
    public int getElevatorSpeed(int elevatorNumber) throws RemoteException {
        return mElevatorSpeed;
    }
    public void setElevatorSpeed(int elevatorSpeed)
    {
        mElevatorSpeed = elevatorSpeed;
    }

    @Override
    public int getElevatorWeight(int elevatorNumber) throws RemoteException {
        return mElevatorWeight;
    }
    public void setElevatorWeight(int elevatorWeight)
    {
        mElevatorWeight = elevatorWeight;
    }

    @Override
    public int getElevatorCapacity(int elevatorNumber) throws RemoteException {
        return mElevatorCapacity;
    }
    public void setElevatorCapacity(int elevatorCapacity)
    {
        mElevatorCapacity = elevatorCapacity;
    }

    @Override
    public boolean getFloorButtonDown(int floor) throws RemoteException {
        return mFloorButtonDown;
    }
    public void setFloorButtonDown(boolean floorButtonDown)
    {
        mFloorButtonDown = floorButtonDown;
    }

    @Override
    public boolean getFloorButtonUp(int floor) throws RemoteException {
        return mFloorButtonUp;
    }
    public void setFloorButtonUp(boolean floorButtonUp)
    {
        mFloorButtonUp = floorButtonUp;
    }

    @Override
    public int getFloorHeight() throws RemoteException {
        return mFloorHeight;
    }
    public void setFloorHeight(int floorHeight)
    {
        mFloorHeight = floorHeight;
    }

    @Override
    public int getFloorNum() throws RemoteException {
        return mFloorNum;
    }
    public void setFloorNum(int floorNum)
    {
        mFloorNum = floorNum;
    }

    @Override
    public boolean getServicesFloors(int elevatorNumber, int floor) throws RemoteException {
        return mServicesFloors[elevatorNumber][floor];
    }
    @Override
    public void setServicesFloors(int elevatorNumber, int floor, boolean service) throws RemoteException {
        mServicesFloors[elevatorNumber][floor] = service;
    }

    @Override
    public int getTarget(int elevatorNumber) throws RemoteException {
        return mTarget[elevatorNumber];
    }
    @Override
    public void setTarget(int elevatorNumber, int target) throws RemoteException {
        mTarget[elevatorNumber] = target;
    }

    @Override
    public long getClockTick() throws RemoteException {
        return mClockTick;
    }
    public void setClockTick(long clockTick)
    {
        mClockTick = clockTick;
    }
}
