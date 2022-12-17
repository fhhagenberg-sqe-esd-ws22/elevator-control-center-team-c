package at.fhhagenberg.sqe.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.rmi.RemoteException;

import static org.junit.jupiter.api.Assertions.*;

class BuildingTest {

    private Building building;
    @BeforeEach
    public void Start() throws RemoteException {
        building = new Building();
    }
    @Test
    void testConstructor()
    {
        assertEquals(4, building.getElevatorCnt());
        assertEquals(160, building.getFloorHeight());
        assertEquals(30, building.getFloorCnt());
        assertEquals(building.getElevatorCnt(),building.getElevators().size());
    }
    @Test
    void testUpdate() throws RemoteException {
        FloorButton button = new FloorButton();
        button.setButtonDown(true);
        button.setButtonUp(false);
        FloorButton[] buttonArr = new FloorButton[building.getFloorCnt()];
        for(int i=0; i< building.getFloorCnt(); i++)
        {
            buttonArr[i] = new FloorButton();
        }
        buttonArr[0] = button;
        building.setFloorButtons(buttonArr);
        assertTrue(building.getFloorButtons()[0].isButtonDown());
        assertFalse(building.getFloorButtons()[0].isButtonUp());
        building.Update();
        assertFalse(building.getFloorButtons()[0].isButtonDown());
        assertTrue(building.getFloorButtons()[0].isButtonUp());
    }
    @Test
    void testFloorCnt()
    {
        building.setFloorCnt(2);
        assertEquals(2, building.getFloorCnt());
    }
    @Test
    void testFloorHeight()
    {
        building.setFloorHeight(100);
        assertEquals(100, building.getFloorHeight());
    }

}