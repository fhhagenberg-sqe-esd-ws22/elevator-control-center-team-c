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
    public boolean createElevatorService() {
        this.elevatorService = new RMIElevatorService(new IElevatorMock(3, 8));
        return true;
    }
}
