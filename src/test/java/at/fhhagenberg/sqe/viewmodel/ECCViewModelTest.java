package at.fhhagenberg.sqe.viewmodel;

import at.fhhagenberg.sqe.model.Building;
import javafx.application.Platform;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.Semaphore;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ECCViewModelTest {

    private ECCViewModel eccViewModel;

    @BeforeEach
    void Setup() {
        Building building = new Building();
        eccViewModel = new ECCViewModel(building);
    }

    @Test
    void TestInit() throws InterruptedException {
        ECCViewModel.initMethod init = eccViewModel.new initMethod();
        init.run();

        assertTrue(eccViewModel.isInitialized());
    }

    //TODO: further tests
}
