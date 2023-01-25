package at.fhhagenberg.sqe.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FloorButtonTest {

    private FloorButton floorButton;

    @BeforeEach
    void Setup() {
        floorButton = new FloorButton();
    }

    @Test
    void testUp() {
        assertEquals(Boolean.FALSE, floorButton.isButtonUp().getValue());

        floorButton.setButtonUp(Boolean.TRUE);
        assertEquals(Boolean.TRUE, floorButton.isButtonUp().getValue());

        floorButton.setButtonUp(Boolean.FALSE);
        assertEquals(Boolean.FALSE, floorButton.isButtonUp().getValue());
    }

    @Test
    void testDown() {
        assertEquals(Boolean.FALSE, floorButton.isButtonDown().getValue());

        floorButton.setButtonDown(Boolean.TRUE);
        assertEquals(Boolean.TRUE, floorButton.isButtonDown().getValue());

        floorButton.setButtonDown(Boolean.FALSE);
        assertEquals(Boolean.FALSE, floorButton.isButtonDown().getValue());
    }
}
