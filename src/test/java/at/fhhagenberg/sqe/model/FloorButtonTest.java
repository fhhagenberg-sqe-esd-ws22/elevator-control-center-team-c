package at.fhhagenberg.sqe.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FloorButtonTest {
    private FloorButton floorButton;

    @BeforeEach
    public void Start()
    {
        floorButton = new FloorButton();
    }

    @Test
    void testButtonUp() {
        floorButton.setButtonUp(true);
        assertTrue(floorButton.isButtonUp());
    }

    @Test
    void testButtonDown() {
        floorButton.setButtonDown(false);
        assertFalse(floorButton.isButtonDown());
    }
}