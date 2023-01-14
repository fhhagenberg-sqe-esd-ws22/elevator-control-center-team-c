package at.fhhagenberg.sqe.model;

import at.fhhagenberg.sqe.IElevator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class ElevatorTest {

    private Elevator elevator;

    @BeforeEach
    public void Setup(){
        elevator = new Elevator(100, 3);
    }

    @Test
    public void testDirection() {
        // test normal use
        assertEquals(IElevator.ELEVATOR_DIRECTION_UNCOMMITTED, elevator.getDirection());

        elevator.setDirection(IElevator.ELEVATOR_DIRECTION_UP);
        assertEquals(IElevator.ELEVATOR_DIRECTION_UP, elevator.getDirection());

        elevator.setDirection(IElevator.ELEVATOR_DIRECTION_DOWN);
        assertEquals(IElevator.ELEVATOR_DIRECTION_DOWN, elevator.getDirection());

        elevator.setDirection(IElevator.ELEVATOR_DIRECTION_UNCOMMITTED);
        assertEquals(IElevator.ELEVATOR_DIRECTION_UNCOMMITTED, elevator.getDirection());

        // test invalid input
        elevator.setDirection(-1);
        assertEquals(IElevator.ELEVATOR_DIRECTION_UNCOMMITTED, elevator.getDirection());

        elevator.setDirection(3);
        assertEquals(IElevator.ELEVATOR_DIRECTION_UNCOMMITTED, elevator.getDirection());
    }

    @Test
    public void testAcceleration() {
        assertEquals(0, elevator.getAcceleration());

        elevator.setAcceleration(-10);
        assertEquals(-10, elevator.getAcceleration());

        elevator.setAcceleration(10);
        assertEquals(10, elevator.getAcceleration());
    }

    @Test
    public void testStopRequestButton() {
        // test normal use
        assertEquals(Boolean.FALSE, elevator.getButton(0).getValue());
        assertEquals(Boolean.FALSE, elevator.getButton(1).getValue());
        assertEquals(Boolean.FALSE, elevator.getButton(2).getValue());

        elevator.setButton(0, Boolean.TRUE);
        assertEquals(Boolean.TRUE, elevator.getButton(0).getValue());
        assertEquals(Boolean.FALSE, elevator.getButton(1).getValue());
        assertEquals(Boolean.FALSE, elevator.getButton(2).getValue());

        elevator.setButton(2, Boolean.TRUE);
        assertEquals(Boolean.TRUE, elevator.getButton(0).getValue());
        assertEquals(Boolean.FALSE, elevator.getButton(1).getValue());
        assertEquals(Boolean.TRUE, elevator.getButton(2).getValue());

        // read invalid floors
        elevator.setButton(-1, Boolean.TRUE);
        elevator.setButton(5, Boolean.TRUE);
        assertEquals(Boolean.FALSE, elevator.getButton(-1).getValue());
        assertEquals(Boolean.FALSE, elevator.getButton(10).getValue());
    }

    @Test
    public void testDoorStatus() {
        // test normal use
        assertEquals(IElevator.ELEVATOR_DOORS_OPEN, elevator.getDoorStatus());

        elevator.setDoorStatus(IElevator.ELEVATOR_DOORS_OPEN);
        assertEquals(IElevator.ELEVATOR_DOORS_OPEN, elevator.getDoorStatus());

        elevator.setDoorStatus(IElevator.ELEVATOR_DOORS_CLOSED);
        assertEquals(IElevator.ELEVATOR_DOORS_CLOSED, elevator.getDoorStatus());

        elevator.setDoorStatus(IElevator.ELEVATOR_DOORS_OPENING);
        assertEquals(IElevator.ELEVATOR_DOORS_OPENING, elevator.getDoorStatus());

        elevator.setDoorStatus(IElevator.ELEVATOR_DOORS_CLOSING);
        assertEquals(IElevator.ELEVATOR_DOORS_CLOSING, elevator.getDoorStatus());

        // test invalid input
        elevator.setDoorStatus(0);
        assertEquals(IElevator.ELEVATOR_DOORS_OPEN, elevator.getDoorStatus());

        elevator.setDoorStatus(5);
        assertEquals(IElevator.ELEVATOR_DOORS_OPEN, elevator.getDoorStatus());
    }

    @Test
    public void testCurrentFloor() {
        // test normal use
        assertEquals("0", elevator.getCurrentFloor().getValue());

        elevator.setCurrentFloor(0);
        assertEquals("0", elevator.getCurrentFloor().getValue());

        elevator.setCurrentFloor(2);
        assertEquals("2", elevator.getCurrentFloor().getValue());

        // test invalid input
        elevator.setCurrentFloor(-1);
        assertEquals("0", elevator.getCurrentFloor().getValue());

        elevator.setCurrentFloor(5);
        assertEquals("0", elevator.getCurrentFloor().getValue());
    }

    @Test
    public void testCurrentPositionFt() {
        // test normal use
        assertEquals("0 ft", elevator.getCurrentPositionFt().getValue());

        elevator.setCurrentPositionFt(0);
        assertEquals("0 ft", elevator.getCurrentPositionFt().getValue());

        elevator.setCurrentPositionFt(10);
        assertEquals("10 ft", elevator.getCurrentPositionFt().getValue());

        // test invalid input
        elevator.setCurrentPositionFt(-1);
        assertEquals("0 ft", elevator.getCurrentPositionFt().getValue());
    }

    @Test
    public void testCurrentSpeedFtPerSec() {
        // test normal use
        assertEquals("0 ft/s", elevator.getCurrentSpeedFtPerSec().getValue());

        elevator.setCurrentSpeedFtPerSec(0);
        assertEquals("0 ft/s", elevator.getCurrentSpeedFtPerSec().getValue());

        elevator.setCurrentSpeedFtPerSec(10);
        assertEquals("10 ft/s", elevator.getCurrentSpeedFtPerSec().getValue());

        // test invalid input
        elevator.setCurrentSpeedFtPerSec(-1);
        assertEquals("0 ft/s", elevator.getCurrentSpeedFtPerSec().getValue());
    }

    @Test
    public void testWeight() {
        // test normal use
        assertEquals("0 lbs", elevator.getWeight().getValue());

        elevator.setWeight(0);
        assertEquals("0 lbs", elevator.getWeight().getValue());

        elevator.setWeight(10);
        assertEquals("10 lbs", elevator.getWeight().getValue());

        // test invalid input
        elevator.setWeight(-1);
        assertEquals("0 lbs", elevator.getWeight().getValue());
    }

    @Test
    public void testServicedFloor() {
        // test normal use
        assertEquals(Boolean.FALSE, elevator.getServicedFloor(0));
        assertEquals(Boolean.FALSE, elevator.getServicedFloor(1));
        assertEquals(Boolean.FALSE, elevator.getServicedFloor(2));

        elevator.setServicedFloor(0, Boolean.TRUE);
        elevator.setServicedFloor(2, Boolean.TRUE);
        assertEquals(Boolean.TRUE, elevator.getServicedFloor(0));
        assertEquals(Boolean.FALSE, elevator.getServicedFloor(1));
        assertEquals(Boolean.TRUE, elevator.getServicedFloor(2));

        // test invalid input
        elevator.setServicedFloor(-1, Boolean.TRUE);
        elevator.setServicedFloor(5, Boolean.TRUE);
        assertEquals(Boolean.FALSE, elevator.getServicedFloor(-1));
        assertEquals(Boolean.FALSE, elevator.getServicedFloor(5));
    }

    @Test
    public void testFloorTarget() {
        // test normal use
        assertEquals(Boolean.FALSE, elevator.getFloorTarget(0).getValue());
        assertEquals(Boolean.FALSE, elevator.getFloorTarget(1).getValue());
        assertEquals(Boolean.FALSE, elevator.getFloorTarget(2).getValue());

        elevator.setFloorTarget(0);
        assertEquals(Boolean.TRUE, elevator.getFloorTarget(0).getValue());
        assertEquals(Boolean.FALSE, elevator.getFloorTarget(1).getValue());
        assertEquals(Boolean.FALSE, elevator.getFloorTarget(2).getValue());

        elevator.setFloorTarget(2);
        assertEquals(Boolean.FALSE, elevator.getFloorTarget(0).getValue());
        assertEquals(Boolean.FALSE, elevator.getFloorTarget(1).getValue());
        assertEquals(Boolean.TRUE, elevator.getFloorTarget(2).getValue());

        // test invalid input
        elevator.setFloorTarget(-1);
        assertEquals(Boolean.FALSE, elevator.getFloorTarget(0).getValue());
        assertEquals(Boolean.FALSE, elevator.getFloorTarget(1).getValue());
        assertEquals(Boolean.FALSE, elevator.getFloorTarget(2).getValue());

        elevator.setFloorTarget(5);
        assertEquals(Boolean.FALSE, elevator.getFloorTarget(0).getValue());
        assertEquals(Boolean.FALSE, elevator.getFloorTarget(1).getValue());
        assertEquals(Boolean.FALSE, elevator.getFloorTarget(2).getValue());

        assertEquals(Boolean.FALSE, elevator.getFloorTarget(-1).getValue());
        assertEquals(Boolean.FALSE, elevator.getFloorTarget(5).getValue());
    }

    @Test
    public void testMaxPayload() {
        assertEquals(100, elevator.getMaxPayload());
    }

    @Test
    public void testAutomaticMode() {
        assertEquals(Boolean.FALSE, elevator.getAutomaticMode());

        elevator.setAutomaticMode(Boolean.FALSE);
        assertEquals(Boolean.FALSE, elevator.getAutomaticMode());

        elevator.setAutomaticMode(Boolean.TRUE);
        assertEquals(Boolean.TRUE, elevator.getAutomaticMode());
    }
}
