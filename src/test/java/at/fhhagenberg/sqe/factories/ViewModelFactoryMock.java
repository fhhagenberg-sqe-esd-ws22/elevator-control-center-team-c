package at.fhhagenberg.sqe.factories;

import at.fhhagenberg.sqe.mockobjects.IElevatorMock;
import at.fhhagenberg.sqe.viewmodel.ECCViewModelMock;

public class ViewModelFactoryMock extends ViewModelFactory {
    /**
     * CTor.
     *
     * @param modelFactory
     */
    public ViewModelFactoryMock(ModelFactory modelFactory) {
        super(modelFactory);
        this.eccViewModel = new ECCViewModelMock(modelFactory.getBuilding());
    }

    public IElevatorMock getElevatorService() {return ((ECCViewModelMock)this.eccViewModel).getElevatorService();}
}
