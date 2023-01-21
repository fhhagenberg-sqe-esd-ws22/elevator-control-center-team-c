package at.fhhagenberg.sqe.viewmodel;

import at.fhhagenberg.sqe.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sqelevator.IElevator;


import static org.junit.jupiter.api.Assertions.*;

public class ECCViewModelTest {

    private ECCViewModel eccViewModel;
    private Building building;

    @BeforeEach
    void Setup() {
        building = new Building();
        eccViewModel = new ECCViewModelMock(building);
        eccViewModel.createElevatorService();
    }

    @Test
    void TestInit() {
        ECCViewModel.InitMethod init = eccViewModel.new InitMethod();
        init.run();

        assertEquals("3", eccViewModel.getBuilding().getElevatorNum().get());
        assertEquals(8, eccViewModel.getBuilding().getFloorNum());
        assertEquals("0 ft", eccViewModel.getBuilding().getFloorHeight().get());
        assertFalse(eccViewModel.getBuilding().getFloorButton(7).isButtonDown().get());
        assertTrue(eccViewModel.isInitialized());
    }

    void MockInit(){
        int elevatorNum = eccViewModel.elevatorService.getElevatorNum();
        int floorHeight = eccViewModel.elevatorService.getFloorHeight();
        int floorNum = eccViewModel.elevatorService.getFloorNum();

        int maxPayload = 10;    // TODO find correct payload
        long clockTick = eccViewModel.elevatorService.getClockTick();

        building.setFloorHeight(floorHeight);

        // fill structure
        for (int idx = 0; idx < elevatorNum; idx++) {
            Elevator elevator = new Elevator(maxPayload,floorNum);
            building.addElevator(elevator);
        }
        for (int idx = 0; idx < floorNum; idx++) {
            FloorButton floorButton = new FloorButton();
            building.addFloorButton(floorButton);
        }
    }

    @Test
    void TestUpdate(){
        MockInit();
        ECCViewModel.UpdateMethod update = eccViewModel.new UpdateMethod();

        eccViewModel.getBuilding().getElevator(1).setAcceleration(2);
        assertEquals(2, eccViewModel.getBuilding().getElevator(1).getAcceleration());

        //run method to be tested
        update.run();

        //check results of tested method
        assertEquals(0, eccViewModel.getBuilding().getElevator(1).getAcceleration());
    }

    @Test
    void TestGetBuilding(){
        assertEquals(building, eccViewModel.getBuilding());
    }

    @Test
    void TestCommittedDirection(){
        DirectionStatus direction = new DirectionStatus();
        direction.setDirectionStatus(IElevator.ELEVATOR_DIRECTION_DOWN);

        MockInit();

        eccViewModel.setCommittedDirection(1,IElevator.ELEVATOR_DIRECTION_DOWN);
        assertEquals( direction.getDownProperty().asObject().get(), eccViewModel.getBuilding().getElevator(1).getDirection().getDownProperty().get());
    }
    @Test
    void TestSetServicesFloors(){
        MockInit();

        eccViewModel.setServicesFloors(1,5,true);
        assertTrue(eccViewModel.getBuilding().getElevator(1).getServicedFloor(5).getValue());
    }

    @Test
    void TestSetTarget(){
        MockInit();

        eccViewModel.setTarget(2,4);
        assertTrue(eccViewModel.getBuilding().getElevator(2).getFloorTarget(4).get());
    }
}
