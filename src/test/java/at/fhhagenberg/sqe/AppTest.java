package at.fhhagenberg.sqe;

import at.fhhagenberg.sqe.model.Elevator;
import at.fhhagenberg.sqe.model.FloorButton;
import at.fhhagenberg.sqe.viewmodel.ECCViewModel;
import at.fhhagenberg.sqe.viewmodel.ECCViewModelMock;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxAssert;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.testfx.matcher.control.LabeledMatchers;

import javafx.stage.Stage;
import org.testfx.util.WaitForAsyncUtils;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.concurrent.TimeUnit;

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

/*
    @Test
    public void testFirstApplication() throws InterruptedException {


        var building = app.getBuilding();
    }*/

    @Test
    public void testElevatorUp(FxRobot robot) {


        //robot.clickOn()
        //app.vmf.getEccViewModel().getBuilding().getElevator(0).getButton(3);
        //app.vmf.getEccViewModel().update();


        FxAssert.verifyThat("#services_floors", LabeledMatchers.hasText("All floors"));
        /*
        FxAssert.verifyThat("#elevator_count", LabeledMatchers.hasText("All floors"));
        FxAssert.verifyThat("#floor_height", LabeledMatchers.hasText("All floors"));
        FxAssert.verifyThat("#services_floors", LabeledMatchers.hasText("All floors"));
        FxAssert.verifyThat("#target_floor", LabeledMatchers.hasText("All floors"));

        */
    }


    /**
     * @param robot - Will be injected by the test runner.
     */
    /*@Test
    public void testButtonClick(FxRobot robot) {
        // when:
        robot.clickOn(".button");

        // or (lookup by css class):
        FxAssert.verifyThat(".button", LabeledMatchers.hasText("Clicked!"));
    }
     */
}