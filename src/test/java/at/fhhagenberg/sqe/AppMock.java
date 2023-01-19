package at.fhhagenberg.sqe;

import at.fhhagenberg.sqe.factories.ModelFactory;
import at.fhhagenberg.sqe.factories.ViewHandler;
import at.fhhagenberg.sqe.factories.ViewModelFactoryMock;

public class AppMock extends App{

    public AppMock() {
        this.mf = new ModelFactory();
        this.vmf = new ViewModelFactoryMock(mf);
        this.vh = new ViewHandler(vmf);
    }
}
