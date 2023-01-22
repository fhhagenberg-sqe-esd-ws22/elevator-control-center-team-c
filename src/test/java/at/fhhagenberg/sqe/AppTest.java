package at.fhhagenberg.sqe;

import at.fhhagenberg.sqe.viewmodel.ECCViewModelMock;
import javafx.scene.control.RadioButton;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxAssert;
import org.testfx.api.FxRobot;
import org.testfx.api.FxService;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.testfx.matcher.control.LabeledMatchers;

import javafx.stage.Stage;
import org.testfx.util.WaitForAsyncUtils;

import java.rmi.RemoteException;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(ApplicationExtension.class)
public class AppTest {

    private AppMock app;

    private FxRobot robot;

    /**
     * Will be called with {@code @Before} semantics, i.e. before each test method.
     *
     * @param stage - Will be injected by the test runner.
     */
    @Start
    void start(Stage stage) throws Exception {
        app = new AppMock();
        ((ECCViewModelMock)app.vmf.getEccViewModel()).createElevatorService();
        app.start(stage);
    }

    @BeforeEach
    void Setup(){
        WaitForAsyncUtils.sleep(2, TimeUnit.SECONDS);
        robot = new FxRobot();

        //click on selection and select first elevator
        robot.clickOn("#Elevator_selection");
        robot.moveBy(0,30);
        robot.clickOn();
    }

    /*
    @Test
    void testTargetFloorModel(){
        app.getService().setElevatorDoorStatus(0,1);    //1 = door is open

        robot.clickOn("#stopRequest_4");
        WaitForAsyncUtils.sleep(200, TimeUnit.MILLISECONDS); // wait for update


        assertTrue(app.vmf.getEccViewModel().getBuilding().getElevator(0).getFloorTarget(4).getValue());
    }

    @Test
    void testTargetFloorGui (){
        app.getService().setElevatorDoorStatus(0,1);    //1 = door is open

        robot.clickOn("#stopRequest_4");
        WaitForAsyncUtils.sleep(200, TimeUnit.MILLISECONDS); // wait for update

        FxAssert.verifyThat("#target_floor",  LabeledMatchers.hasText("4"));
    }*/

    @Test
    void testServicesFloorsModel() throws RemoteException {
        app.getService().setElevatorDoorStatus(0,1);    //1 = door is open
        app.getService().setServicesFloors(0,5,false);

        WaitForAsyncUtils.sleep(200, TimeUnit.MILLISECONDS); // wait for update

        assertFalse(app.vmf.getEccViewModel().getBuilding().getElevator(0).getServicedFloor(5).get());
    }

    @Test
    void testServicesFloorsGui() throws RemoteException {
        app.getService().setElevatorDoorStatus(0,1);    //1 = door is open
        app.getService().setServicesFloors(0,5,false);

        WaitForAsyncUtils.sleep(200, TimeUnit.MILLISECONDS); // wait for update

        FxAssert.verifyThat("#Services_floors", LabeledMatchers.hasText("0, 1, 2, 3, 4, 6, 7"));
    }

    @Test
    void testFloorHeightModel(){
        app.getService().setElevatorDoorStatus(0,1);    //1 = door is open
        app.getService().setFloorHeight(42);

        WaitForAsyncUtils.sleep(200, TimeUnit.MILLISECONDS);

        assertEquals("42 ft", app.vmf.getEccViewModel().getBuilding().getFloorHeight().get());
    }

    @Test
    void testFloorHeightGui(){
        app.getService().setElevatorDoorStatus(0,1);    //1 = door is open
        app.getService().setFloorHeight(42);

        WaitForAsyncUtils.sleep(200, TimeUnit.MILLISECONDS);

        FxAssert.verifyThat("#Floor_height", LabeledMatchers.hasText("42 ft"));
    }

    @Test
    void testElevatorCountModel(){
        app.getService().setElevatorDoorStatus(0,1);    //1 = door is open

        WaitForAsyncUtils.sleep(200, TimeUnit.MILLISECONDS);

        assertEquals(3, app.vmf.getEccViewModel().getBuilding().getElevatorNumINT());
    }
    @Test
    void testElevatorCountGui(){
        app.getService().setElevatorDoorStatus(0,1);    //1 = door is open

        WaitForAsyncUtils.sleep(200, TimeUnit.MILLISECONDS);

        FxAssert.verifyThat("#Elevator_count", LabeledMatchers.hasText("3"));
    }
    

    @Test
    void testCallRequestModel(){
        app.getService().setFloorButtonUp(4,true);

        WaitForAsyncUtils.sleep(200, TimeUnit.MILLISECONDS);

        assertTrue(app.vmf.getEccViewModel().getBuilding().getFloorButton(4).isButtonUp().get());
    }
    @Test
    //@ParameterizedTest
    void testCallRequestGui() {
        app.getService().setFloorButtonUp(4,true);

        WaitForAsyncUtils.sleep(200, TimeUnit.MILLISECONDS);

        assertTrue(FxService.serviceContext().getNodeFinder().lookup("#callRequest_Up_4").query().isVisible());
    }


    @Test
    void testSpeedModel(){
        app.getService().setElevatorSpeed(0, 13);

        WaitForAsyncUtils.sleep(200, TimeUnit.MILLISECONDS);

        assertEquals("13 ft/s", app.vmf.getEccViewModel().getBuilding().getElevator(0).getCurrentSpeedFtPerSec().get());
    }
    @Test
    void testSpeedGui(){
        app.getService().setElevatorSpeed(0, 13);

        WaitForAsyncUtils.sleep(200, TimeUnit.MILLISECONDS);

        FxAssert.verifyThat("#Speed", LabeledMatchers.hasText("13 ft/s"));
    }

    @Test
    void testWeightModel(){
        app.getService().setElevatorWeight(0,666);

        WaitForAsyncUtils.sleep(200, TimeUnit.MILLISECONDS);

        assertEquals("666 lbs", app.vmf.getEccViewModel().getBuilding().getElevator(0).getWeight().get());

    }
    @Test
    void testWeightGui(){
        app.getService().setElevatorWeight(0,666);

        WaitForAsyncUtils.sleep(200, TimeUnit.MILLISECONDS);

        FxAssert.verifyThat("#Weight", LabeledMatchers.hasText("666 lbs"));
    }

    @Test
    void testPositionModel(){
        app.getService().setElevatorPosition(0,4711);

        WaitForAsyncUtils.sleep(200, TimeUnit.MILLISECONDS);

        assertEquals("4711 ft", app.vmf.getEccViewModel().getBuilding().getElevator(0).getCurrentPositionFt().get());
    }
    @Test
    void testPositionGui(){
        app.getService().setElevatorPosition(0,4711);

        WaitForAsyncUtils.sleep(200, TimeUnit.MILLISECONDS);

        FxAssert.verifyThat("#Position", LabeledMatchers.hasText("4711 ft"));
    }

    @Test
    void testStopRequestModel() {
        app.getService().setElevatorButton(0,5,true);

        WaitForAsyncUtils.sleep(200, TimeUnit.MILLISECONDS);

        assertTrue(app.vmf.getEccViewModel().getBuilding().getElevator(0).getButton(5).get());

    }
    @Test
    void testStopRequestGui(){
        app.getService().setElevatorButton(0,5,true);

        WaitForAsyncUtils.sleep(200, TimeUnit.MILLISECONDS);

        assertTrue(FxService.serviceContext().getNodeFinder().lookup("#stopRequest_5").query().isVisible());
    }

    @Test
    void testDoorStatusModel(){
        app.getService().setElevatorDoorStatus(0,4);

        WaitForAsyncUtils.sleep(200, TimeUnit.MILLISECONDS);

        assertTrue(app.vmf.getEccViewModel().getBuilding().getElevator(0).getDoorStatus().getClosingProperty().get());
        assertFalse(app.vmf.getEccViewModel().getBuilding().getElevator(0).getDoorStatus().getOpeningProperty().get());
        assertFalse(app.vmf.getEccViewModel().getBuilding().getElevator(0).getDoorStatus().getOpenedProperty().get());
        assertFalse(app.vmf.getEccViewModel().getBuilding().getElevator(0).getDoorStatus().getClosedProperty().get());
    }
    @Test
    void testDoorStatusGui(){
        app.getService().setElevatorDoorStatus(0,4);

        WaitForAsyncUtils.sleep(200, TimeUnit.MILLISECONDS);

        assertTrue( FxService.serviceContext().getNodeFinder().lookup("#Opening_closing_elevator").query().isVisible());
        assertFalse( FxService.serviceContext().getNodeFinder().lookup("#Open_elevator").query().isVisible());
        assertFalse( FxService.serviceContext().getNodeFinder().lookup("#Closed_elevator").query().isVisible());
    }

    @Test
    void testElevatorDirectionModel() throws RemoteException {
        app.getService().setCommittedDirection(0, 2);

        WaitForAsyncUtils.sleep(200, TimeUnit.MILLISECONDS);

        assertTrue(app.vmf.getEccViewModel().getBuilding().getElevator(0).getDirection().getUncommittedProperty().get());
        assertFalse(app.vmf.getEccViewModel().getBuilding().getElevator(0).getDirection().getDownProperty().get());
        assertFalse(app.vmf.getEccViewModel().getBuilding().getElevator(0).getDirection().getUpProperty().get());
    }
    @Test
    void testElevatorDirectionGui() throws RemoteException {
        app.getService().setCommittedDirection(0, 1);

        WaitForAsyncUtils.sleep(200, TimeUnit.MILLISECONDS);

        assertTrue( FxService.serviceContext().getNodeFinder().lookup("#Elevator_downwards").query().isVisible());
        assertFalse( FxService.serviceContext().getNodeFinder().lookup("#Elevator_upwards").query().isVisible());
    }

    @Test
    void testCurrentFloorModel() {
        app.getService().setElevatorFloor(0,5);

        WaitForAsyncUtils.sleep(200, TimeUnit.MILLISECONDS);

        assertEquals("5", app.vmf.getEccViewModel().getBuilding().getElevator(0).getCurrentFloor().get());
    }
    @Test
    void testCurrentFloorGui() {
        app.getService().setElevatorFloor(0,5);

        WaitForAsyncUtils.sleep(200, TimeUnit.MILLISECONDS);

        FxAssert.verifyThat("#current_floor", LabeledMatchers.hasText("5"));
    }

    @Test
    void testAutoModeButtonModel(){
        robot.clickOn("#Auto_mode_radio");

        WaitForAsyncUtils.sleep(200, TimeUnit.MILLISECONDS);

        assertTrue(app.vmf.getEccViewModel().getBuilding().getElevator(0).getAutomaticModeBool().get());
    }
    @Test
    void testAutoModeButtonGui(){
        robot.clickOn("#Auto_mode_radio");

        WaitForAsyncUtils.sleep(200, TimeUnit.MILLISECONDS);

        FxAssert.verifyThat("#Auto_mode_setting", LabeledMatchers.hasText("ON"));
        assertTrue( ((RadioButton)FxService.serviceContext().getNodeFinder().lookup("#Auto_mode_radio").query()).isSelected());
    }
}