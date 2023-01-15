package at.fhhagenberg.sqe.factories;

import at.fhhagenberg.sqe.model.Building;
import at.fhhagenberg.sqe.viewmodel.ECCViewModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ViewModelFactoryTest {

    @Test
    void TestGetEccViewModel() {
        ModelFactory modelFactory = new ModelFactory();
        modelFactory.getBuilding();
        ViewModelFactory viewModelFactory = new ViewModelFactory(modelFactory);

        assertTrue(viewModelFactory.getEccViewModel() instanceof ECCViewModel);
    }

}
