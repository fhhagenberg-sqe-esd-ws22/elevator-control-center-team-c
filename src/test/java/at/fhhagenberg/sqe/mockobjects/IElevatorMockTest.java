package at.fhhagenberg.sqe.mockobjects;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.rmi.RemoteException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IElevatorMockTest {

    private IElevatorMock sut;

    @BeforeEach
    void Setup(){
        sut = new IElevatorMock(5,10);
    }

    @Test
    void TestCommittedDirection() throws RemoteException {
        sut.setCommittedDirection(4,1);
        assertEquals(1, sut.getCommittedDirection(4));
    }

    @Test
    void TestElevatorAccel() throws RemoteException {
        sut.setElevatorAccel(0,20);
        assertEquals(20, sut.getElevatorAccel(0));
    }

    @Test
    void TestElevatorButton() throws RemoteException {
        sut.setElevatorButton(2,5, true);
        assertTrue(sut.getElevatorButton(2,5));
    }

    @Test
    void TestElevatorDoorStatus() throws RemoteException {
        sut.setElevatorDoorStatus(4,3);
        assertEquals(3, sut.getElevatorDoorStatus(4));
    }

    @Test
    void TestElevatorFloor() throws RemoteException {
        sut.setElevatorFloor(3, 9);
        assertEquals(9, sut.getElevatorFloor(3));
    }

    @Test
    void TestElevatorNum() throws RemoteException {
        assertEquals(5, sut.getElevatorNum());

        sut.setElevatorNum(3);
        assertEquals(3, sut.getElevatorNum());
    }

    @Test
    void TestElevatorPosition() throws RemoteException {
        sut.setElevatorPosition(1, 42);
        assertEquals(42, sut.getElevatorPosition(1));
    }

    @Test
    void TestElevatorSpeed() throws RemoteException {
        sut.setElevatorSpeed(0, 33);
        assertEquals(33, sut.getElevatorSpeed(0));
    }

    @Test
    void TestElevatorWeight() throws RemoteException {
        sut.setElevatorWeight(4, 420);
        assertEquals(420, sut.getElevatorWeight(4));
    }

    @Test
    void TestElevatorCapacity() throws RemoteException {
        sut.setElevatorCapacity(0, 10);
        assertEquals(10, sut.getElevatorCapacity(0));
    }

    @Test
    void TestFloorButtonDown() throws RemoteException {
        sut.setFloorButtonDown(8,true);
        assertTrue(sut.getFloorButtonDown(8));
    }

    @Test
    void TestFloorButtonUp() throws RemoteException {
        sut.setFloorButtonUp(2, true);
        assertTrue(sut.getFloorButtonUp(2));
    }

    @Test
    void TestFloorHeight() throws RemoteException {
        sut.setFloorHeight(12);
        assertEquals(12, sut.getFloorHeight());
    }

    @Test
    void TestFloorNum() throws RemoteException {
        assertEquals( 10, sut.getFloorNum());

        sut.setFloorNum(15);
        assertEquals(15, sut.getFloorNum());
    }

    @Test
    void TestServicesFloors() throws RemoteException {
        sut.setServicesFloors(3, 9, true);
        assertTrue(sut.getServicesFloors(3, 9));
    }

    @Test
    void TestTarget() throws RemoteException {
        sut.setTarget(2, 10);
        assertEquals(10, sut.getTarget(2));
    }

    @Test
    void TestClockTick() throws RemoteException {
        sut.setClockTick(4711);
        assertEquals(4711, sut.getClockTick());
    }
}
