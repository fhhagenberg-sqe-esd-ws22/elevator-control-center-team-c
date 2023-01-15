package at.fhhagenberg.sqe.model;

import sqelevator.IElevator;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class DirectionStatus {

    private BooleanProperty up;
    private BooleanProperty down;
    private BooleanProperty uncommitted;

    private void setDefault() {
        setUncommitted();
    }

    private void setUp() {
        up.setValue(Boolean.TRUE);
        down.setValue(Boolean.FALSE);
        uncommitted.setValue(Boolean.FALSE);
    }

    private void setDown() {
        up.setValue(Boolean.FALSE);
        down.setValue(Boolean.TRUE);
        uncommitted.setValue(Boolean.FALSE);
    }

    private void setUncommitted() {
        up.setValue(Boolean.FALSE);
        down.setValue(Boolean.FALSE);
        uncommitted.setValue(Boolean.TRUE);
    }

    public DirectionStatus() {
        up = new SimpleBooleanProperty();
        down = new SimpleBooleanProperty();
        uncommitted = new SimpleBooleanProperty();
        setDefault();
    }

    public void setDirectionStatus(int status) {
        switch (status) {
            case IElevator.ELEVATOR_DIRECTION_UP:
                setUp();
                break;
            case IElevator.ELEVATOR_DIRECTION_DOWN:
                setDown();
                break;
            case IElevator.ELEVATOR_DIRECTION_UNCOMMITTED:
                setUncommitted();
                break;
            default:
                setUncommitted();
        }
    }

    public BooleanProperty getUpProperty() {
        return up;
    }

    public BooleanProperty getDownProperty() {
        return down;
    }

    public BooleanProperty getUncommittedProperty() {
        return uncommitted;
    }
}
