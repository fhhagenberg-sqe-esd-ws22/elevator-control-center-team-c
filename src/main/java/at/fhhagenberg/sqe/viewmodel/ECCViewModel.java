package at.fhhagenberg.sqe.viewmodel;

import at.fhhagenberg.sqe.model.Building;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ECCViewModel {

    // TODO create all properties you need to show
    private StringProperty testLabel;

    /**
     * Reference of the data structure.
     */
    private Building building;

    /**
     * CTor.
     * @param building
     */
    public ECCViewModel(Building building) {
        this.building = building;

        testLabel = new SimpleStringProperty();
        testLabel.setValue("Testoutput stands here!");
    }



    public StringProperty getTestLabel() { return testLabel; }

    public void init() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                // TODO do the initialization of the structure
            }
        });
    }

    private int cnt = 0;    // TODO delete (just for testing

    public void update() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                testLabel.setValue("called: " + cnt++);
            }
        });
    }
}
