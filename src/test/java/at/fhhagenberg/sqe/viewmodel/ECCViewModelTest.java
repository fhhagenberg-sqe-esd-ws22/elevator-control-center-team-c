package at.fhhagenberg.sqe.viewmodel;

import at.fhhagenberg.sqe.mockobjects.IElevatorMock;
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
        IElevatorMock elevatorMock = new IElevatorMock(5, 7);
        eccViewModel.elevatorService = new RMIElevatorService(elevatorMock);
    }

    @Test
    void TestInit() {
        ECCViewModel.initMethod init = eccViewModel.new initMethod();
        init.run();

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
        ECCViewModel.updateMethod update = eccViewModel.new updateMethod();

        eccViewModel.getBuilding().getElevator(1).setAcceleration(2);
        assertEquals(eccViewModel.getBuilding().getElevator(1).getAcceleration(),2);

        //run method to be tested
        update.run();

        //check results of tested method
        assertEquals(eccViewModel.getBuilding().getElevator(1).getAcceleration(),0);
    }

    @Test
    void TestGetBuilding(){
        assertEquals(eccViewModel.getBuilding(), building);
    }

    @Test
    void TestCommittedDirection(){
        DirectionStatus direction = new DirectionStatus();
        direction.setDirectionStatus(IElevator.ELEVATOR_DIRECTION_DOWN);

        MockInit();

        eccViewModel.setCommittedDirection(1,IElevator.ELEVATOR_DIRECTION_DOWN);
        assertEquals(eccViewModel.getBuilding().getElevator(1).getDirection().getDownProperty().get(), direction.getDownProperty().asObject().get());
    }
    @Test
    void TestSetServicesFloors(){
        MockInit();

        eccViewModel.setServicesFloors(1,5,true);
        assertTrue(eccViewModel.getBuilding().getElevator(1).getServicedFloor(5));
    }

    @Test
    void TestSetTarget(){
        MockInit();

        eccViewModel.setTarget(2,4);
        assertTrue(eccViewModel.getBuilding().getElevator(2).getFloorTarget(4).get());
    }





}
