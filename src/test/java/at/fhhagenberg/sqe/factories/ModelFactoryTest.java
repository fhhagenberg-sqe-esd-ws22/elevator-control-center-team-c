package at.fhhagenberg.sqe.factories;


import at.fhhagenberg.sqe.model.Building;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ModelFactoryTest {

    @Test
    void TestGetBuilding() {
        ModelFactory modelFactory = new ModelFactory();
        assertTrue(modelFactory.getBuilding() instanceof Building);
    }
}
