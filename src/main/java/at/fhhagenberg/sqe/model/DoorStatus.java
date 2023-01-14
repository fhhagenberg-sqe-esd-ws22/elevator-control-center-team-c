package at.fhhagenberg.sqe.model;

import at.fhhagenberg.sqe.IElevator;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class DoorStatus {

    private BooleanProperty opened;
    private BooleanProperty closed;
    private BooleanProperty opening;
    private BooleanProperty closing;

    private void setDefault() {
        setClosed();
    }

    private void setOpened() {
        opened.setValue(Boolean.TRUE);
        closed.setValue(Boolean.FALSE);
        opening.setValue(Boolean.FALSE);
        closing.setValue(Boolean.FALSE);
    }

    private void setClosed() {
        opened.setValue(Boolean.FALSE);
        closed.setValue(Boolean.TRUE);
        opening.setValue(Boolean.FALSE);
        closing.setValue(Boolean.FALSE);
    }

    private void setOpening() {
        opened.setValue(Boolean.FALSE);
        closed.setValue(Boolean.FALSE);
        opening.setValue(Boolean.TRUE);
        closing.setValue(Boolean.FALSE);
    }

    private void setClosing() {
        opened.setValue(Boolean.FALSE);
        closed.setValue(Boolean.FALSE);
        opening.setValue(Boolean.FALSE);
        closing.setValue(Boolean.TRUE);
    }

    public DoorStatus() {
        opened = new SimpleBooleanProperty();
        closed = new SimpleBooleanProperty();
        opening = new SimpleBooleanProperty();
        closing = new SimpleBooleanProperty();
        setDefault();
    }

    public void setDoorStatus(int status) {
        switch (status) {
            case IElevator.ELEVATOR_DOORS_OPEN:
                setOpened();
                break;
            case IElevator.ELEVATOR_DOORS_CLOSED:
                setClosed();
                break;
            case IElevator.ELEVATOR_DOORS_OPENING:
                setOpening();
                break;
            case IElevator.ELEVATOR_DOORS_CLOSING:
                setClosing();
                break;
            default:
                setDefault();
        }
    }

    public BooleanProperty getOpenedProperty() {
        return opened;
    }

    public BooleanProperty getClosedProperty() {
        return closed;
    }

    public BooleanProperty getOpeningProperty() {
        return opening;
    }

    public BooleanProperty getClosingProperty() {
        return closing;
    }
}
