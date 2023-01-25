package at.fhhagenberg.sqe.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BuildingTest {

    private Building building;

    @BeforeEach
    public void Setup() {
        building = new Building();
    }

    @Test
    void testAddGetElevator() {
        assertNull(building.getElevator(0));
        assertNull(building.getElevator(-1));
        assertNull(building.getElevator(1));
        assertEquals("0", building.getElevatorNum().getValue());
        assertEquals(0, building.getElevatorNumINT());

        Elevator elevator1 = new Elevator(10, 1);
        Elevator elevator2 = new Elevator(10, 1);
        building.addElevator(elevator1);
        building.addElevator(elevator2);
        assertEquals(elevator1, building.getElevator(0));
        assertEquals(elevator2, building.getElevator(1));
        assertEquals("2", building.getElevatorNum().getValue());
        assertEquals(2, building.getElevatorNumINT());

        assertNull(building.getElevator(-1));
        assertNull(building.getElevator(2));
        assertEquals("2", building.getElevatorNum().getValue());
        assertEquals(2, building.getElevatorNumINT());
    }

    @Test
    void testFloorHeight() {
        // test normal use
        assertEquals("0 ft", building.getFloorHeight().getValue());

        building.setFloorHeight(0);
        assertEquals("0 ft", building.getFloorHeight().getValue());

        building.setFloorHeight(10);
        assertEquals("10 ft", building.getFloorHeight().getValue());

        // test invalid input
        building.setFloorHeight(-1);
        assertEquals("0 ft", building.getFloorHeight().getValue());

        building.setFloorHeight(-100);
        assertEquals("0 ft", building.getFloorHeight().getValue());
    }

    @Test
    void testFloorButtons() {
        assertNull(building.getFloorButton(0));
        assertNull(building.getFloorButton(-1));
        assertNull(building.getFloorButton(1));
        assertEquals(0, building.getFloorNum());

        FloorButton floorButton1 = new FloorButton();
        FloorButton floorButton2 = new FloorButton();
        building.addFloorButton(floorButton1);
        building.addFloorButton(floorButton2);
        assertEquals(floorButton1, building.getFloorButton(0));
        assertEquals(floorButton2, building.getFloorButton(1));
        assertEquals(2, building.getFloorNum());

        assertNull(building.getFloorButton(-1));
        assertNull(building.getFloorButton(2));
        assertEquals(2, building.getFloorNum());
    }
}
