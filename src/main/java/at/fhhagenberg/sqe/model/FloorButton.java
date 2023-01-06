package at.fhhagenberg.sqe.model;

public class FloorButton {

    /**
     * Upwards call request.
     */
    private boolean buttonUp;

    /**
     * Downwards call request.
     */
    private boolean buttonDown;


    public boolean isButtonUp() { return buttonUp; }
    public void setButtonUp(boolean buttonUp) { this.buttonUp = buttonUp; }


    public boolean isButtonDown() { return buttonDown; }
    public void setButtonDown(boolean buttonDown) { this.buttonDown = buttonDown; }
}
