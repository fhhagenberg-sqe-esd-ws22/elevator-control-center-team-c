package at.fhhagenberg.sqe;

import at.fhhagenberg.sqe.mockobjects.IElevatorMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.rmi.RemoteException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IElevatorMockTest {

    private IElevatorMock sut;

    @BeforeEach
    public void Setup(){
        sut = new IElevatorMock(5,10);
    }

    @Test
    public void TestCommittedDirection() throws RemoteException {
        sut.setCommittedDirection(4,1);
        assertEquals(sut.getCommittedDirection(4), 1);
    }

    @Test
    public void TestElevatorAccel() throws RemoteException {
        sut.setElevatorAccel(0,20);
        assertEquals(sut.getElevatorAccel(0), 20);
    }

    @Test
    public void TestElevatorButton() throws RemoteException {
        sut.setElevatorButton(2,5, true);
        assertTrue(sut.getElevatorButton(2,5));
    }

    @Test
    public void TestElevatorDoorStatus() throws RemoteException {
        sut.setElevatorDoorStatus(4,3);
        assertEquals(sut.getElevatorDoorStatus(4), 3);
    }

    @Test
    public void TestElevatorFloor() throws RemoteException {
        sut.setElevatorFloor(3, 9);
        assertEquals(sut.getElevatorFloor(3), 9);
    }

    @Test
    public void TestElevatorNum() throws RemoteException {
        assertEquals(sut.getElevatorNum(), 5);

        sut.setElevatorNum(3);
        assertEquals(sut.getElevatorNum(), 3);
    }

    @Test
    public void TestElevatorPosition() throws RemoteException {
        sut.setElevatorPosition(1, 42);
        assertEquals(sut.getElevatorPosition(1), 42);
    }

    @Test
    public void TestElevatorSpeed() throws RemoteException {
        sut.setElevatorSpeed(0, 33);
        assertEquals(sut.getElevatorSpeed(0), 33);
    }

    @Test
    public void TestElevatorWeight() throws RemoteException {
        sut.setElevatorWeight(4, 420);
        assertEquals(sut.getElevatorWeight(4), 420);
    }

    @Test
    public void TestElevatorCapacity() throws RemoteException {
        sut.setElevatorCapacity(0, 10);
        assertEquals(sut.getElevatorCapacity(0), 10);
    }

    @Test
    public void TestFloorButtonDown() throws RemoteException {
        sut.setFloorButtonDown(8,true);
        assertTrue(sut.getFloorButtonDown(8));
    }

    @Test
    public void TestFloorButtonUp() throws RemoteException {
        sut.setFloorButtonUp(2, true);
        assertTrue(sut.getFloorButtonUp(2));
    }

    @Test
    public void TestFloorHeight() throws RemoteException {
        sut.setFloorHeight(12);
        assertEquals(sut.getFloorHeight(), 12);
    }

    @Test
    public void TestFloorNum() throws RemoteException {
        assertEquals(sut.getFloorNum(), 10);

        sut.setFloorNum(15);
        assertEquals(sut.getFloorNum(), 15);
    }

    @Test
    public void TestServicesFloors() throws RemoteException {
        sut.setServicesFloors(3, 9, true);
        assertTrue(sut.getServicesFloors(3, 9));
    }

    @Test
    public void TestTarget() throws RemoteException {
        sut.setTarget(2, 10);
        assertEquals(sut.getTarget(2), 10);
    }

    @Test
    public void TestClockTick() throws RemoteException {
        sut.setClockTick(4711);
        assertEquals(sut.getClockTick(), 4711);
    }
}
