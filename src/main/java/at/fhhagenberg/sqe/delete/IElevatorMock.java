package at.fhhagenberg.sqe.delete;

import at.fhhagenberg.sqe.IElevator;

import java.rmi.RemoteException;
import java.util.Vector;

public class IElevatorMock implements IElevator {

    public IElevatorMock(int maxElevatorsCnt, int maxElevatorFloorsCnt)
    {
        //maxElevatorFloorsCnt= maxElevatorFloorsCnt + 1;
        //maxElevatorsCnt = maxElevatorsCnt +1;

        mElevatorNum = maxElevatorsCnt;
        mFloorNum = maxElevatorFloorsCnt;

        mCommittedDirection = new int[maxElevatorsCnt];
        mElevatorAccel = new int[maxElevatorsCnt];
        mElevatorButton = new boolean[maxElevatorsCnt][maxElevatorFloorsCnt];
        mElevatorDoorStatus = new int[maxElevatorsCnt];
        mElevatorFloor = new int[maxElevatorsCnt];
        mElevatorPosition = new int[maxElevatorsCnt];
        mElevatorSpeed = new int[maxElevatorsCnt];
        mElevatorWeight = new int[maxElevatorsCnt];
        mElevatorCapacity = new int[maxElevatorsCnt];
        mFloorButtonDown = new boolean[maxElevatorFloorsCnt];
        mFloorButtonUp = new boolean[maxElevatorFloorsCnt];
        mServicesFloors = new boolean[maxElevatorsCnt][maxElevatorFloorsCnt];
        mTarget = new int[maxElevatorsCnt];
    }


    private final int[] mCommittedDirection;
    private final int[] mElevatorAccel;
    private final boolean[][] mElevatorButton;
    private final int[] mElevatorDoorStatus;
    private final int[] mElevatorFloor;
    private int mElevatorNum;
    private final int[] mElevatorPosition;
    private final int[] mElevatorSpeed;
    private final int[] mElevatorWeight;
    private final int[] mElevatorCapacity;
    private final boolean[] mFloorButtonDown;
    private final boolean[] mFloorButtonUp;
    private int mFloorHeight;
    private int mFloorNum;
    private final boolean[][] mServicesFloors;

    private final int[] mTarget;

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
        return mElevatorAccel[elevatorNumber];
    }
    public void setElevatorAccel (int elevatorNumber, int elevatorAccel)
    {
        mElevatorAccel[elevatorNumber] = elevatorAccel;
    }

    @Override
    public boolean getElevatorButton(int elevatorNumber, int floor) throws RemoteException {
        return mElevatorButton[elevatorNumber][floor];
    }
    public void setElevatorButton (int elevatorNumber, int floor, boolean elevatorButton)
    {
        mElevatorButton[elevatorNumber][floor] = elevatorButton;
    }

    @Override
    public int getElevatorDoorStatus(int elevatorNumber) throws RemoteException {
        return mElevatorDoorStatus[elevatorNumber];
    }
    public void setElevatorDoorStatus(int elevatorNumber, int elevatorDoorStatus)
    {
        mElevatorDoorStatus[elevatorNumber] = elevatorDoorStatus;
    }

    @Override
    public int getElevatorFloor(int elevatorNumber) throws RemoteException {
        return mElevatorFloor[elevatorNumber];
    }
    public void setElevatorFloor(int elevatorNumber, int elevatorFloor)
    {
        mElevatorFloor[elevatorNumber] = elevatorFloor;
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
        return mElevatorPosition[elevatorNumber];
    }
    public void setElevatorPosition(int elevatorNumber, int elevatorPosition)
    {
        mElevatorPosition[elevatorNumber] = elevatorPosition;
    }

    @Override
    public int getElevatorSpeed(int elevatorNumber) throws RemoteException {
        return mElevatorSpeed[elevatorNumber];
    }
    public void setElevatorSpeed(int elevatorNumber, int elevatorSpeed)
    {
        mElevatorSpeed[elevatorNumber] = elevatorSpeed;
    }

    @Override
    public int getElevatorWeight(int elevatorNumber) throws RemoteException {
        return mElevatorWeight[elevatorNumber];
    }
    public void setElevatorWeight(int elevatorNumber, int elevatorWeight)
    {
        mElevatorWeight[elevatorNumber] = elevatorWeight;
    }

    @Override
    public int getElevatorCapacity(int elevatorNumber) throws RemoteException {
        return mElevatorCapacity[elevatorNumber];
    }
    public void setElevatorCapacity(int elevatorNumber, int elevatorCapacity)
    {
        mElevatorCapacity[elevatorNumber] = elevatorCapacity;
    }

    @Override
    public boolean getFloorButtonDown(int floor) throws RemoteException {
        return mFloorButtonDown[floor];
    }
    public void setFloorButtonDown(int floor, boolean floorButtonDown)
    {
        mFloorButtonDown[floor] = floorButtonDown;
    }

    @Override
    public boolean getFloorButtonUp(int floor) throws RemoteException {
        return mFloorButtonUp[floor];
    }
    public void setFloorButtonUp(int floor, boolean floorButtonUp)
    {
        mFloorButtonUp[floor] = floorButtonUp;
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
