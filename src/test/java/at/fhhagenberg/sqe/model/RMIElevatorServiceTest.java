package at.fhhagenberg.sqe.model;

import at.fhhagenberg.sqe.IElevator;
import at.fhhagenberg.sqe.mockobjects.IElevatorMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mock;
import org.mockito.Mockito;


import java.rmi.RemoteException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.doThrow;

public class RMIElevatorServiceTest {

    private RMIElevatorService RmiElevatorService;
    private IElevatorMock elevatorMock;

    private IElevatorMock elevatorMockException;

    @BeforeEach
    public void Setup() {
        elevatorMock = new IElevatorMock(3,8);
        RmiElevatorService = new RMIElevatorService(elevatorMock);

        elevatorMockException = Mockito.spy(new IElevatorMock(3,8));
    }


    @Test
    void TestCommittedDirection() throws RemoteException {
        RmiElevatorService.setCommittedDirection(2,1);
        assertEquals(RmiElevatorService.getCommittedDirection(2),1);
    }

    @Test
    void TestElevatorAccel() throws RemoteException {
        elevatorMock.setElevatorAccel(2,10);

        assertEquals(RmiElevatorService.getElevatorAccel(2), 10);
    }

    @Test
    public void TestElevatorButton() throws RemoteException{
        elevatorMock.setElevatorButton(2,3,true);

        assertTrue(RmiElevatorService.getElevatorButton(2,3));
    }

    @Test
    void TestElevatorDoorStatus() throws RemoteException {
        elevatorMock.setElevatorDoorStatus(2,2);

        assertEquals(RmiElevatorService.getElevatorDoorStatus(2), 2);
    }

    @Test
    void TestElevatorFloor() throws RemoteException {
        elevatorMock.setElevatorFloor(2,7);

        assertEquals(RmiElevatorService.getElevatorFloor(2), 7);
    }

    @Test
    void TestElevatorNum() throws RemoteException {
        elevatorMock.setElevatorNum(15);

        assertEquals(RmiElevatorService.getElevatorNum(), 15);
    }

    @Test
    void TestElevatorPosition() throws RemoteException {
        elevatorMock.setElevatorPosition(2, 11);

        assertEquals(RmiElevatorService.getElevatorPosition(2), 11);
    }

    @Test
    void TestElevatorSpeed() throws RemoteException {
        elevatorMock.setElevatorSpeed(1, 42);

        assertEquals(RmiElevatorService.getElevatorSpeed(1), 42);
    }

    @Test
    void TestElevatorWeight() throws RemoteException {
        elevatorMock.setElevatorWeight(2, 666);

        assertEquals(RmiElevatorService.getElevatorWeight(2),666);
    }

    @Test
    void TestElevatorCapacity() throws RemoteException {
        elevatorMock.setElevatorCapacity(0, 5);

        assertEquals(RmiElevatorService.getElevatorCapacity(0), 5);
    }

    @Test
    void TestFloorButtonDown() throws RemoteException {
        elevatorMock.setFloorButtonDown(7,true);

        assertTrue(RmiElevatorService.getFloorButtonDown(7));
    }

    @Test
    void TestFloorButtonUp() throws RemoteException {
        elevatorMock.setFloorButtonUp(0, true);

        assertTrue(RmiElevatorService.getFloorButtonUp(0));
    }

    @Test
    void TestFloorHeight() throws RemoteException {
        elevatorMock.setFloorHeight(369);

        assertEquals(RmiElevatorService.getFloorHeight(), 369);
    }

    @Test
    void TestFloorNum() throws RemoteException {
        elevatorMock.setFloorNum(49);

        assertEquals(RmiElevatorService.getFloorNum(), 49);
    }

    @Test
    void TestServicesFloors() throws RemoteException {
        RmiElevatorService.setServicesFloors(2,0, true);
        assertTrue(RmiElevatorService.getServicesFloors(2,0));
    }

    @Test
    void TestTarget() throws RemoteException {
        RmiElevatorService.setTarget(0,7);
        assertEquals(RmiElevatorService.getTarget(0), 7);
    }

    @Test
    void TestGetClockTick() throws RemoteException {
        elevatorMock.setClockTick(299792458);

        assertEquals(RmiElevatorService.getClockTick(), 299792458);
    }



    @Test
    public void TestCommittedDirection_Exception() throws RemoteException {
        elevatorMockException.setCommittedDirection(1,1);
        RMIElevatorService RmiElevatorServiceException = new RMIElevatorService(elevatorMockException);
        Mockito.doThrow(new RemoteException()).when(elevatorMockException).getCommittedDirection(1);

        assertEquals(RmiElevatorServiceException.getCommittedDirection(1), IElevator.ELEVATOR_DIRECTION_UNCOMMITTED);


    }
    @Test
    public void TestElevatorAccel_Exception() throws RemoteException {
        elevatorMockException.setElevatorAccel(1,10);
        RMIElevatorService RmiElevatorServiceException = new RMIElevatorService(elevatorMockException);
        Mockito.doThrow(new RemoteException()).when(elevatorMockException).getElevatorAccel(1);

        assertEquals(RmiElevatorServiceException.getElevatorAccel(1), 0);
    }
    @Test
    public void TestElevatorButton_Exception() throws RemoteException {
        elevatorMockException.setElevatorButton(2,3,true);
        RMIElevatorService RmiElevatorServiceException = new RMIElevatorService(elevatorMockException);
        Mockito.doThrow(new RemoteException()).when(elevatorMockException).getElevatorButton(2, 3);

        assertFalse(RmiElevatorServiceException.getElevatorButton(2,3));
    }

    @Test
    public void TestElevatorDoorStatus_Exception() throws RemoteException {
        elevatorMockException.setElevatorDoorStatus(2,2);
        RMIElevatorService RmiElevatorServiceException = new RMIElevatorService(elevatorMockException);
        Mockito.doThrow(new RemoteException()).when(elevatorMockException).getElevatorDoorStatus(2);

        assertEquals(RmiElevatorServiceException.getElevatorDoorStatus(2), 0);
    }
    @Test
    public void TestElevatorFloor_Exception() throws RemoteException {
        elevatorMockException.setElevatorFloor(0,7);
        RMIElevatorService RmiElevatorServiceException = new RMIElevatorService(elevatorMockException);
        Mockito.doThrow(new RemoteException()).when(elevatorMockException).getElevatorFloor(0);

        assertEquals(RmiElevatorServiceException.getElevatorFloor(0), 0);
    }
    @Test
    public void TestElevatorNum_Exception() throws RemoteException {
        elevatorMockException.setFloorNum(15);
        RMIElevatorService RmiElevatorServiceException = new RMIElevatorService(elevatorMockException);
        Mockito.doThrow(new RemoteException()).when(elevatorMockException).getElevatorNum();

        assertEquals(RmiElevatorServiceException.getElevatorNum(), 0);
    }
    @Test
    public void TestElevatorPosition_Exception() throws RemoteException {
        elevatorMockException.setElevatorPosition(2,3);
        RMIElevatorService RmiElevatorServiceException = new RMIElevatorService(elevatorMockException);
        Mockito.doThrow(new RemoteException()).when(elevatorMockException).getElevatorPosition(2);

        assertEquals(RmiElevatorServiceException.getElevatorPosition(2), 0);
    }
    @Test
    public void TestElevatorSpeed_Exception() throws RemoteException {
        elevatorMockException.setElevatorSpeed(0,42);
        RMIElevatorService RmiElevatorServiceException = new RMIElevatorService(elevatorMockException);
        Mockito.doThrow(new RemoteException()).when(elevatorMockException).getElevatorSpeed(0);

        assertEquals(RmiElevatorServiceException.getElevatorSpeed(0), 0);
    }
    @Test
    public void TestElevatorWeight_Exception() throws RemoteException {
        elevatorMockException.setElevatorWeight(2,666);
        RMIElevatorService RmiElevatorServiceException = new RMIElevatorService(elevatorMockException);
        Mockito.doThrow(new RemoteException()).when(elevatorMockException).getElevatorWeight(2);

        assertEquals(RmiElevatorServiceException.getElevatorWeight(2), 0);
    }
    @Test
    public void TestElevatorCapacity_Exception() throws RemoteException {
        elevatorMockException.setElevatorCapacity(1,6);
        RMIElevatorService RmiElevatorServiceException = new RMIElevatorService(elevatorMockException);
        Mockito.doThrow(new RemoteException()).when(elevatorMockException).getElevatorCapacity(1);

        assertEquals(RmiElevatorServiceException.getElevatorCapacity(1), 0);
    }
    @Test
    public void TestFloorButtonDown_Exception() throws RemoteException {
        elevatorMockException.setFloorButtonDown(2,true);
        RMIElevatorService RmiElevatorServiceException = new RMIElevatorService(elevatorMockException);
        Mockito.doThrow(new RemoteException()).when(elevatorMockException).getFloorButtonDown(2);

        assertFalse(RmiElevatorServiceException.getFloorButtonDown(2));
    }
    @Test
    public void TestFloorButtonUp_Exception() throws RemoteException {
        elevatorMockException.setFloorButtonUp(2,true);
        RMIElevatorService RmiElevatorServiceException = new RMIElevatorService(elevatorMockException);
        Mockito.doThrow(new RemoteException()).when(elevatorMockException).getFloorButtonUp(2);

        assertFalse(RmiElevatorServiceException.getFloorButtonUp(2));
    }
    @Test
    public void TestFloorHeight_Exception() throws RemoteException {
        elevatorMockException.setFloorHeight(369);
        RMIElevatorService RmiElevatorServiceException = new RMIElevatorService(elevatorMockException);
        Mockito.doThrow(new RemoteException()).when(elevatorMockException).getFloorHeight();

        assertEquals(RmiElevatorServiceException.getFloorHeight(), 0);
    }
    @Test
    public void TestFloorNum_Exception() throws RemoteException {
        elevatorMockException.setFloorNum(60);
        RMIElevatorService RmiElevatorServiceException = new RMIElevatorService(elevatorMockException);
        Mockito.doThrow(new RemoteException()).when(elevatorMockException).getFloorNum();

        assertEquals(RmiElevatorServiceException.getFloorNum(), 0);
    }
    @Test
    public void TestServicesFloors_Exception() throws RemoteException {
        elevatorMockException.setServicesFloors(2,5,true);
        RMIElevatorService RmiElevatorServiceException = new RMIElevatorService(elevatorMockException);
        Mockito.doThrow(new RemoteException()).when(elevatorMockException).getServicesFloors(2,5);

        assertFalse(RmiElevatorServiceException.getServicesFloors(2, 5));
    }
    @Test
    public void TestTarget_Exception() throws RemoteException {
        elevatorMockException.setTarget(1,5);
        RMIElevatorService RmiElevatorServiceException = new RMIElevatorService(elevatorMockException);
        Mockito.doThrow(new RemoteException()).when(elevatorMockException).getTarget(1);

        assertEquals(RmiElevatorServiceException.getTarget(1), 0);
    }
    @Test
    public void TestClockTick_Exception() throws RemoteException {
        elevatorMockException.setClockTick(662607015);
        RMIElevatorService RmiElevatorServiceException = new RMIElevatorService(elevatorMockException);
        Mockito.doThrow(new RemoteException()).when(elevatorMockException).getClockTick();

        assertEquals(RmiElevatorServiceException.getClockTick(), 0);
    }



}
