package at.fhhagenberg.sqe.factories;

import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ViewHandlerTest {

    private ViewHandler viewHandler;

    private Stage stage;
    private ViewModelFactory viewModelFactory;
    private ModelFactory modelFactory;

    @BeforeEach
    void Setup() {
        stage = new Stage();
        modelFactory = new ModelFactory();
        modelFactory.getBuilding();
        viewModelFactory = new ViewModelFactory(modelFactory);

        viewHandler = new ViewHandler(stage, viewModelFactory);
    }

    //TODO: Test start and openView ????
    /*
    @Test
    void TestStart() throws Exception{
        viewHandler.start();
    }*/
}
