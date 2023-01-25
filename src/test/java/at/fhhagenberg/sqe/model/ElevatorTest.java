package at.fhhagenberg.sqe.model;

import sqelevator.IElevator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class ElevatorTest {

    private Elevator elevator;

    @BeforeEach
    void Setup(){
        elevator = new Elevator(100, 3);
    }

    @Test
    void testDirection() {
        // test normal use
        assertFalse(elevator.getDirection().getUpProperty().getValue());
        assertFalse(elevator.getDirection().getDownProperty().getValue());
        assertTrue(elevator.getDirection().getUncommittedProperty().getValue());

        elevator.setDirection(IElevator.ELEVATOR_DIRECTION_UP);
        assertTrue(elevator.getDirection().getUpProperty().getValue());
        assertFalse(elevator.getDirection().getDownProperty().getValue());
        assertFalse(elevator.getDirection().getUncommittedProperty().getValue());

        elevator.setDirection(IElevator.ELEVATOR_DIRECTION_DOWN);
        assertFalse(elevator.getDirection().getUpProperty().getValue());
        assertTrue(elevator.getDirection().getDownProperty().getValue());
        assertFalse(elevator.getDirection().getUncommittedProperty().getValue());

        elevator.setDirection(IElevator.ELEVATOR_DIRECTION_UNCOMMITTED);
        assertFalse(elevator.getDirection().getUpProperty().getValue());
        assertFalse(elevator.getDirection().getDownProperty().getValue());
        assertTrue(elevator.getDirection().getUncommittedProperty().getValue());

        // test invalid input
        elevator.setDirection(-1);
        assertFalse(elevator.getDirection().getUpProperty().getValue());
        assertFalse(elevator.getDirection().getDownProperty().getValue());
        assertTrue(elevator.getDirection().getUncommittedProperty().getValue());

        elevator.setDirection(3);
        assertFalse(elevator.getDirection().getUpProperty().getValue());
        assertFalse(elevator.getDirection().getDownProperty().getValue());
        assertTrue(elevator.getDirection().getUncommittedProperty().getValue());
    }

    @Test
    void testAcceleration() {
        assertEquals(0, elevator.getAcceleration());

        elevator.setAcceleration(-10);
        assertEquals(-10, elevator.getAcceleration());

        elevator.setAcceleration(10);
        assertEquals(10, elevator.getAcceleration());
    }

    @Test
    void testStopRequestButton() {
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
    void testDoorStatusNormal() {
        // test normal use
        assertFalse(elevator.getDoorStatus().getOpenedProperty().getValue());
        assertTrue(elevator.getDoorStatus().getClosedProperty().getValue());
        assertFalse(elevator.getDoorStatus().getOpeningProperty().getValue());
        assertFalse(elevator.getDoorStatus().getClosingProperty().getValue());
    }
    @Test
    void testDoorStatusOpened() {
        elevator.setDoorStatus(IElevator.ELEVATOR_DOORS_OPEN);
        assertTrue(elevator.getDoorStatus().getOpenedProperty().getValue());
        assertFalse(elevator.getDoorStatus().getClosedProperty().getValue());
        assertFalse(elevator.getDoorStatus().getOpeningProperty().getValue());
        assertFalse(elevator.getDoorStatus().getClosingProperty().getValue());
    }
    @Test
    void testDoorStatusClosed() {
        elevator.setDoorStatus(IElevator.ELEVATOR_DOORS_CLOSED);
        assertFalse(elevator.getDoorStatus().getOpenedProperty().getValue());
        assertTrue(elevator.getDoorStatus().getClosedProperty().getValue());
        assertFalse(elevator.getDoorStatus().getOpeningProperty().getValue());
        assertFalse(elevator.getDoorStatus().getClosingProperty().getValue());
    }
    @Test
    void testDoorStatusOpening() {
        elevator.setDoorStatus(IElevator.ELEVATOR_DOORS_OPENING);
        assertFalse(elevator.getDoorStatus().getOpenedProperty().getValue());
        assertFalse(elevator.getDoorStatus().getClosedProperty().getValue());
        assertTrue(elevator.getDoorStatus().getOpeningProperty().getValue());
        assertFalse(elevator.getDoorStatus().getClosingProperty().getValue());
    }
    @Test
    void testDoorStatusClosing() {
        elevator.setDoorStatus(IElevator.ELEVATOR_DOORS_CLOSING);
        assertFalse(elevator.getDoorStatus().getOpenedProperty().getValue());
        assertFalse(elevator.getDoorStatus().getClosedProperty().getValue());
        assertFalse(elevator.getDoorStatus().getOpeningProperty().getValue());
        assertTrue(elevator.getDoorStatus().getClosingProperty().getValue());
    }
    @Test
    void testInvalidDoorStatus() {
        elevator.setDoorStatus(0);
        assertFalse(elevator.getDoorStatus().getOpenedProperty().getValue());
        assertTrue(elevator.getDoorStatus().getClosedProperty().getValue());
        assertFalse(elevator.getDoorStatus().getOpeningProperty().getValue());
        assertFalse(elevator.getDoorStatus().getClosingProperty().getValue());
        elevator.setDoorStatus(5);
        assertFalse(elevator.getDoorStatus().getOpenedProperty().getValue());
        assertTrue(elevator.getDoorStatus().getClosedProperty().getValue());
        assertFalse(elevator.getDoorStatus().getOpeningProperty().getValue());
        assertFalse(elevator.getDoorStatus().getClosingProperty().getValue());
    }

    @Test
    void testCurrentFloor() {
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
    void testCurrentPositionFt() {
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
    void testCurrentSpeedFtPerSec() {
        // test normal use
        assertEquals("0 ft/s", elevator.getCurrentSpeedFtPerSec().getValue());

        elevator.setCurrentSpeedFtPerSec(0);
        assertEquals("0 ft/s", elevator.getCurrentSpeedFtPerSec().getValue());

        elevator.setCurrentSpeedFtPerSec(10);
        assertEquals("10 ft/s", elevator.getCurrentSpeedFtPerSec().getValue());

        elevator.setCurrentSpeedFtPerSec(-1);
        assertEquals("-1 ft/s", elevator.getCurrentSpeedFtPerSec().getValue());
    }

    @Test
    void testWeight() {
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
    void testServicedFloor() {
        // test normal use
        assertEquals(Boolean.FALSE, elevator.getServicedFloor(0).getValue());
        assertEquals(Boolean.FALSE, elevator.getServicedFloor(1).getValue());
        assertEquals(Boolean.FALSE, elevator.getServicedFloor(2).getValue());

        elevator.setServicedFloor(0, Boolean.TRUE);
        elevator.setServicedFloor(2, Boolean.TRUE);
        assertEquals(Boolean.TRUE, elevator.getServicedFloor(0).getValue());
        assertEquals(Boolean.FALSE, elevator.getServicedFloor(1).getValue());
        assertEquals(Boolean.TRUE, elevator.getServicedFloor(2).getValue());

        // test invalid input
        elevator.setServicedFloor(-1, Boolean.TRUE);
        elevator.setServicedFloor(5, Boolean.TRUE);
        assertEquals(Boolean.FALSE, elevator.getServicedFloor(-1).getValue());
        assertEquals(Boolean.FALSE, elevator.getServicedFloor(5).getValue());
    }

    @Test
    void testFloorTarget() {
        // test normal use
        assertEquals(Boolean.FALSE, elevator.getFloorTarget(0).getValue());
        assertEquals(Boolean.FALSE, elevator.getFloorTarget(1).getValue());
        assertEquals(Boolean.FALSE, elevator.getFloorTarget(2).getValue());
        assertEquals("", elevator.getFloorTargetStringProp().getValue());

        elevator.setFloorTarget(0);
        assertEquals(Boolean.TRUE, elevator.getFloorTarget(0).getValue());
        assertEquals(Boolean.FALSE, elevator.getFloorTarget(1).getValue());
        assertEquals(Boolean.FALSE, elevator.getFloorTarget(2).getValue());
        assertEquals("0", elevator.getFloorTargetStringProp().getValue());

        elevator.setFloorTarget(2);
        assertEquals(Boolean.FALSE, elevator.getFloorTarget(0).getValue());
        assertEquals(Boolean.FALSE, elevator.getFloorTarget(1).getValue());
        assertEquals(Boolean.TRUE, elevator.getFloorTarget(2).getValue());
        assertEquals("2", elevator.getFloorTargetStringProp().getValue());

        // test invalid input
        elevator.setFloorTarget(-1);
        assertEquals(Boolean.FALSE, elevator.getFloorTarget(0).getValue());
        assertEquals(Boolean.FALSE, elevator.getFloorTarget(1).getValue());
        assertEquals(Boolean.FALSE, elevator.getFloorTarget(2).getValue());
        assertEquals("", elevator.getFloorTargetStringProp().getValue());

        elevator.setFloorTarget(5);
        assertEquals(Boolean.FALSE, elevator.getFloorTarget(0).getValue());
        assertEquals(Boolean.FALSE, elevator.getFloorTarget(1).getValue());
        assertEquals(Boolean.FALSE, elevator.getFloorTarget(2).getValue());
        assertEquals("", elevator.getFloorTargetStringProp().getValue());

        assertEquals(Boolean.FALSE, elevator.getFloorTarget(-1).getValue());
        assertEquals(Boolean.FALSE, elevator.getFloorTarget(5).getValue());
    }

    @Test
    void testMaxPayload() {
        assertEquals(100, elevator.getMaxPayload());
    }

    @Test
    void testAutomaticMode() {
        assertEquals("OFF", elevator.getAutomaticMode().getValue());
        assertFalse(elevator.getAutomaticModeBool().getValue());

        elevator.setAutomaticMode(Boolean.FALSE);
        assertEquals("OFF", elevator.getAutomaticMode().getValue());
        assertFalse(elevator.getAutomaticModeBool().getValue());

        elevator.setAutomaticMode(Boolean.TRUE);
        assertEquals("ON", elevator.getAutomaticMode().getValue());
        assertTrue(elevator.getAutomaticModeBool().getValue());
    }
}
