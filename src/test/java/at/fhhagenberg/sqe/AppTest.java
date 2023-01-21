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
     * Will be called with {@code @Before} semantics, i. e. before each test method.
     *
     * @param stage - Will be injected by the test runner.
     */
    @Start
    public void start(Stage stage) throws Exception {
        app = new AppMock();
        ((ECCViewModelMock)app.vmf.getEccViewModel()).createElevatorService();
        app.start(stage);


    }

    @BeforeEach
    public void Setup(){
        WaitForAsyncUtils.sleep(2, TimeUnit.SECONDS);
        robot = new FxRobot();

        //click on selection and select first elevator
        robot.clickOn("#elevator_selection");
        robot.moveBy(0,30);
        robot.clickOn();
    }

    @Test
    public void testTargetFloorModel(FxRobot robot){
        app.getService().setElevatorDoorStatus(0,1);    //1 = door is open

        robot.clickOn("#stopRequest_4");
        //update gets called

        assertTrue(app.vmf.getEccViewModel().getBuilding().getElevator(0).getFloorTarget(4).getValue());
    }

    @Test
    public void testTargetFloorGui (){
        app.getService().setElevatorDoorStatus(0,1);    //1 = door is open

        robot.clickOn("#stopRequest_4");
        //update gets called

        FxAssert.verifyThat("#target_floor",  LabeledMatchers.hasText("4"));



    }

    @Test
    public void testServicesFloorsModel() throws RemoteException {
        app.getService().setElevatorDoorStatus(0,1);    //1 = door is open
        app.getService().setServicesFloors(0,5,false);

        WaitForAsyncUtils.sleep(200, TimeUnit.MILLISECONDS); // wait for update

        assertFalse(app.vmf.getEccViewModel().getBuilding().getElevator(0).getServicedFloor(5).get());
    }

    @Test
    public void testServicesFloorsGui() throws RemoteException {
        app.getService().setElevatorDoorStatus(0,1);    //1 = door is open
        app.getService().setServicesFloors(0,5,false);

        WaitForAsyncUtils.sleep(200, TimeUnit.MILLISECONDS); // wait for update

        FxAssert.verifyThat("#services_floors", LabeledMatchers.hasText("0, 1, 2, 3, 4, 6, 7"));
    }

    @Test
    public void testFloorHeightModel(){
        app.getService().setElevatorDoorStatus(0,1);    //1 = door is open
        app.getService().setFloorHeight(42);

        WaitForAsyncUtils.sleep(200, TimeUnit.MILLISECONDS);

        assertEquals(app.vmf.getEccViewModel().getBuilding().getFloorHeight().get(), "42 ft");
    }

    @Test
    public void testFloorHeightGui(){
        app.getService().setElevatorDoorStatus(0,1);    //1 = door is open
        app.getService().setFloorHeight(42);

        WaitForAsyncUtils.sleep(200, TimeUnit.MILLISECONDS);

        FxAssert.verifyThat("#floor_height", LabeledMatchers.hasText("42 ft"));
    }

    @Test
    public void testElevatorCountModel(){
        app.getService().setElevatorDoorStatus(0,1);    //1 = door is open

        WaitForAsyncUtils.sleep(200, TimeUnit.MILLISECONDS);

        assertEquals(app.vmf.getEccViewModel().getBuilding().getElevatorNumINT(), 3);
    }
    @Test
    public void testElevatorCountGui(){
        app.getService().setElevatorDoorStatus(0,1);    //1 = door is open

        WaitForAsyncUtils.sleep(200, TimeUnit.MILLISECONDS);

        FxAssert.verifyThat("#elevator_count", LabeledMatchers.hasText("3"));
    }
    

    @Test
    public void testCallRequestModel(){
        app.getService().setFloorButtonUp(4,true);

        WaitForAsyncUtils.sleep(200, TimeUnit.MILLISECONDS);

        assertTrue(app.vmf.getEccViewModel().getBuilding().getFloorButton(4).isButtonUp().get());
    }
    @Test
    //@ParameterizedTest
    public void testCallRequestGui() {
        app.getService().setFloorButtonUp(4,true);

        WaitForAsyncUtils.sleep(200, TimeUnit.MILLISECONDS);

        assertTrue(FxService.serviceContext().getNodeFinder().lookup("#callRequest_Up_4").query().isVisible());
    }


    @Test
    public void testSpeedModel(){
        app.getService().setElevatorSpeed(0, 13);

        WaitForAsyncUtils.sleep(200, TimeUnit.MILLISECONDS);

        assertEquals(app.vmf.getEccViewModel().getBuilding().getElevator(0).getCurrentSpeedFtPerSec().get(), "13 ft/s");
    }
    @Test
    public void testSpeedGui(){
        app.getService().setElevatorSpeed(0, 13);

        WaitForAsyncUtils.sleep(200, TimeUnit.MILLISECONDS);

        FxAssert.verifyThat("#speed", LabeledMatchers.hasText("13 ft/s"));
    }

    @Test
    public void testWeightModel(){
        app.getService().setElevatorWeight(0,666);

        WaitForAsyncUtils.sleep(200, TimeUnit.MILLISECONDS);

        assertEquals(app.vmf.getEccViewModel().getBuilding().getElevator(0).getWeight().get(), "666 lbs");

    }
    @Test
    public void testWeightGui(){
        app.getService().setElevatorWeight(0,666);

        WaitForAsyncUtils.sleep(200, TimeUnit.MILLISECONDS);

        FxAssert.verifyThat("#weight", LabeledMatchers.hasText("666 lbs"));
    }

    @Test
    public void testPositionModel(){
        app.getService().setElevatorPosition(0,4711);

        WaitForAsyncUtils.sleep(200, TimeUnit.MILLISECONDS);

        assertEquals(app.vmf.getEccViewModel().getBuilding().getElevator(0).getCurrentPositionFt().get(), "4711 ft");
    }
    @Test
    public void testPositionGui(){
        app.getService().setElevatorPosition(0,4711);

        WaitForAsyncUtils.sleep(200, TimeUnit.MILLISECONDS);

        FxAssert.verifyThat("#position", LabeledMatchers.hasText("4711 ft"));
    }

    @Test
    public void testStopRequestModel() {
        app.getService().setElevatorButton(0,5,true);

        WaitForAsyncUtils.sleep(200, TimeUnit.MILLISECONDS);

        assertTrue(app.vmf.getEccViewModel().getBuilding().getElevator(0).getButton(5).get());

    }
    @Test
    public void testStopRequestGui(){
        app.getService().setElevatorButton(0,5,true);

        WaitForAsyncUtils.sleep(200, TimeUnit.MILLISECONDS);

        assertTrue(FxService.serviceContext().getNodeFinder().lookup("#stopRequest_5").query().isVisible());
    }

    @Test
    public void testDoorStatusModel(){
        app.getService().setElevatorDoorStatus(0,4);

        WaitForAsyncUtils.sleep(200, TimeUnit.MILLISECONDS);

        assertTrue(app.vmf.getEccViewModel().getBuilding().getElevator(0).getDoorStatus().getClosingProperty().get());
        assertFalse(app.vmf.getEccViewModel().getBuilding().getElevator(0).getDoorStatus().getOpeningProperty().get());
        assertFalse(app.vmf.getEccViewModel().getBuilding().getElevator(0).getDoorStatus().getOpenedProperty().get());
        assertFalse(app.vmf.getEccViewModel().getBuilding().getElevator(0).getDoorStatus().getClosedProperty().get());
    }
    @Test
    public void testDoorStatusGui(){
        app.getService().setElevatorDoorStatus(0,4);

        WaitForAsyncUtils.sleep(200, TimeUnit.MILLISECONDS);

        assertTrue( FxService.serviceContext().getNodeFinder().lookup("#opening_closing_elevator").query().isVisible());
        assertFalse( FxService.serviceContext().getNodeFinder().lookup("#open_elevator").query().isVisible());
        assertFalse( FxService.serviceContext().getNodeFinder().lookup("#closed_elevator").query().isVisible());
    }

    @Test
    public void testElevatorDirectionModel() throws RemoteException {
        app.getService().setCommittedDirection(0, 2);

        WaitForAsyncUtils.sleep(200, TimeUnit.MILLISECONDS);

        assertTrue(app.vmf.getEccViewModel().getBuilding().getElevator(0).getDirection().getUncommittedProperty().get());
        assertFalse(app.vmf.getEccViewModel().getBuilding().getElevator(0).getDirection().getDownProperty().get());
        assertFalse(app.vmf.getEccViewModel().getBuilding().getElevator(0).getDirection().getUpProperty().get());
    }
    @Test
    public void testElevatorDirectionGui() throws RemoteException {
        app.getService().setCommittedDirection(0, 1);

        WaitForAsyncUtils.sleep(200, TimeUnit.MILLISECONDS);

        assertTrue( FxService.serviceContext().getNodeFinder().lookup("#elevator_downwards").query().isVisible());
        assertFalse( FxService.serviceContext().getNodeFinder().lookup("#elevator_upwards").query().isVisible());
    }

    @Test
    public void testCurrentFloorModel() {
        app.getService().setElevatorFloor(0,5);

        WaitForAsyncUtils.sleep(200, TimeUnit.MILLISECONDS);

        assertEquals(app.vmf.getEccViewModel().getBuilding().getElevator(0).getCurrentFloor().get(), "5");
    }
    @Test
    public void testCurrentFloorGui() {
        app.getService().setElevatorFloor(0,5);

        WaitForAsyncUtils.sleep(200, TimeUnit.MILLISECONDS);

        FxAssert.verifyThat("#current_floor", LabeledMatchers.hasText("5"));
    }

    @Test
    public void testAutoModeButtonModel(){
        robot.clickOn("#auto_mode_radio");

        WaitForAsyncUtils.sleep(200, TimeUnit.MILLISECONDS);

        assertTrue(app.vmf.getEccViewModel().getBuilding().getElevator(0).getAutomaticMode_bool().get());
    }
    @Test
    public void testAutoModeButtonGui(){
        robot.clickOn("#auto_mode_radio");

        WaitForAsyncUtils.sleep(200, TimeUnit.MILLISECONDS);

        FxAssert.verifyThat("#auto_mode_setting", LabeledMatchers.hasText("ON"));
        assertTrue( ((RadioButton)FxService.serviceContext().getNodeFinder().lookup("#auto_mode_radio").query()).isSelected());
    }
}