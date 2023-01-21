package at.fhhagenberg.sqe;

import at.fhhagenberg.sqe.factories.ModelFactory;
import at.fhhagenberg.sqe.factories.ViewHandler;
import at.fhhagenberg.sqe.factories.ViewModelFactoryMock;
import at.fhhagenberg.sqe.mockobjects.IElevatorMock;
import at.fhhagenberg.sqe.model.Building;

public class AppMock extends App{

    public AppMock() {
        this.mf = new ModelFactory();
        this.vmf = new ViewModelFactoryMock(mf);
        this.vh = new ViewHandler(vmf);
    }

    public Building getBuilding() {

        return vmf.getEccViewModel().getBuilding();
    }

    public IElevatorMock getService() {return ((ViewModelFactoryMock)vmf).getElevatorService();}
}
