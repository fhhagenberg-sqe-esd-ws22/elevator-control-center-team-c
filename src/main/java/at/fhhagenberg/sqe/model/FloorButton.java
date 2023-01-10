package at.fhhagenberg.sqe.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class FloorButton {

    /**
     * Upwards call request.
     */
    private BooleanProperty buttonUp = new SimpleBooleanProperty();

    /**
     * Downwards call request.
     */
    private BooleanProperty buttonDown = new SimpleBooleanProperty();


    public BooleanProperty isButtonUp() { return buttonUp; }
    public void setButtonUp(boolean buttonUp) { this.buttonUp.setValue(buttonUp); }


    public BooleanProperty isButtonDown() { return buttonDown; }
    public void setButtonDown(boolean buttonDown) { this.buttonDown.setValue(buttonDown); }
}
