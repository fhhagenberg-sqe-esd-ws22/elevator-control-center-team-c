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

    @BeforeEach
    public void Setup() {
        elevatorMock = new IElevatorMock(3,8);
        RmiElevatorService = new RMIElevatorService(elevatorMock);
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


    //TODO: Test RemoteExceptions
/*
    @Test
    public void TestElevatorButton_Exception() throws RemoteException {
        RemoteException e = new RemoteException();
        elevatorMock.setElevatorButton(2,3,true);

        RMIElevatorService RmiElevatorServiceException = Mockito.spy(new RMIElevatorService(elevatorMock));

        //Mockito.doThrow(e).when(RmiElevatorServiceException).getElevatorButton(2, 3);
        Mockito.doAnswer(invocation -> { throw e; }).when(RmiElevatorServiceException).getElevatorButton(2, 3);
        assertFalse(RmiElevatorService.getElevatorButton(2,3));


        //Mockito.verify(RmiElevatorServiceException, Mockito.times(1)).getElevatorButton(2, 3);
        //Mockito.verify(RmiElevatorServiceException, Mockito.times(1)).printStackTrace();

    }*/
}
