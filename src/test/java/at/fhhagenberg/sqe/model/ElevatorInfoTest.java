package at.fhhagenberg.sqe.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.rmi.RemoteException;

import static org.junit.jupiter.api.Assertions.*;

class ElevatorInfoTest {

    private ElevatorInfo elevator;
    @BeforeEach
    public void Start(){
        elevator = new ElevatorInfo(10,1,10);
    }
    @Test
    public void testUpdate() throws RemoteException {
        elevator.Update();
        assertEquals(1, elevator.getDirection());
        assertEquals(2, elevator.getAcceleration());
        assertEquals(1, elevator.getDoorStatus());
        assertEquals(1, elevator.getCurrentFloor());
        assertEquals(4, elevator.getCurrentPositionFt());
        assertEquals(3, elevator.getCurrentSpeedFtPerSec());
        assertEquals(2, elevator.getWeight());
        assertTrue(elevator.getStopRequests()[0]);
        assertEquals(2, elevator.getFloorTarget());
    }

    @Test
    public void testDirection() {
        elevator.setDirection(1);
        assertEquals(1,elevator.getDirection());
    }

    @Test
    public void testAcceleration() {
        elevator.setAcceleration(10);
        assertEquals(10,elevator.getAcceleration());
    }

    @Test
    public void testIsMode() {
        elevator.setMode(true);
        assertTrue(elevator.isMode());
    }
    @Test
    public void testStopRequests() {
        elevator.setStopRequests(new boolean[10]);
        assertEquals(10,elevator.getStopRequests().length);
    }

    @Test
    public void testDoorStatus() {
        elevator.setDoorStatus(2);
        assertEquals(2,elevator.getDoorStatus());
    }

    @Test
    public void testCurrentFloor() {
        elevator.setCurrentFloor(4);
        assertEquals(4,elevator.getCurrentFloor());
    }

    @Test
    public void testCurrentPositionFt() {
        elevator.setCurrentPositionFt(234);
        assertEquals(234, elevator.getCurrentPositionFt());
    }

    @Test
    public void testCurrentSpeedFtPerSec() {
        elevator.setCurrentSpeedFtPerSec(23);
        assertEquals(23, elevator.getCurrentSpeedFtPerSec());
    }

    @Test
    public void testWeight() {
        elevator.setWeight(300);
        assertEquals(300,elevator.getWeight());
    }

    @Test
    public void testGetCapacity() {
        assertEquals(10, elevator.getCapacity());
    }

    @Test
    public void testServicedFloors() throws RemoteException {
        elevator.setServicedFloors(new boolean[10]);
        assertEquals(10, elevator.getServicedFloors().length);
        elevator.Update();
        assertTrue(elevator.getServicedFloors()[0]);
    }
    @Test
    public void testFloorTarget() {
        elevator.setFloorTarget(3);
        assertEquals(3, elevator.getFloorTarget());
    }
}