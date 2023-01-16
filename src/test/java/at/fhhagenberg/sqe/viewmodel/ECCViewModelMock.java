package at.fhhagenberg.sqe.viewmodel;

import at.fhhagenberg.sqe.mockobjects.IElevatorMock;
import at.fhhagenberg.sqe.model.Building;
import at.fhhagenberg.sqe.model.RMIElevatorService;

public class ECCViewModelMock extends ECCViewModel{

    /**
     * CTor.
     *
     * @param building
     */
    public ECCViewModelMock(Building building) {
        super(building);
    }

    @Override
    protected boolean createElevatorService() {
        int elevatorCount = 5;
        int floorCount = 10;
        this.elevatorService = new RMIElevatorService(new IElevatorMock(elevatorCount, floorCount));
        return true;
    }
}
