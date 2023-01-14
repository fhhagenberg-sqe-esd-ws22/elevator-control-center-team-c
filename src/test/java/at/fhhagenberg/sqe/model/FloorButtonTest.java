package at.fhhagenberg.sqe.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FloorButtonTest {

    private FloorButton floorButton;

    @BeforeEach
    public void Setup() {
        floorButton = new FloorButton();
    }

    @Test
    public void testUp() {
        assertEquals(Boolean.FALSE, floorButton.isButtonUp().getValue());

        floorButton.setButtonUp(Boolean.TRUE);
        assertEquals(Boolean.TRUE, floorButton.isButtonUp().getValue());

        floorButton.setButtonUp(Boolean.FALSE);
        assertEquals(Boolean.FALSE, floorButton.isButtonUp().getValue());
    }

    @Test
    public void testDown() {
        assertEquals(Boolean.FALSE, floorButton.isButtonDown().getValue());

        floorButton.setButtonDown(Boolean.TRUE);
        assertEquals(Boolean.TRUE, floorButton.isButtonDown().getValue());

        floorButton.setButtonDown(Boolean.FALSE);
        assertEquals(Boolean.FALSE, floorButton.isButtonDown().getValue());
    }
}
