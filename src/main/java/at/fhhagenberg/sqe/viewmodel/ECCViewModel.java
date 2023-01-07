package at.fhhagenberg.sqe.viewmodel;

import at.fhhagenberg.sqe.model.Building;
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

    }

    public void update() {
        testLabel.setValue("changed to this");
    }
}
