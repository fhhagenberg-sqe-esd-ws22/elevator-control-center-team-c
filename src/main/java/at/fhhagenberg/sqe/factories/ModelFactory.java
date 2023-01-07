package at.fhhagenberg.sqe.factories;

import at.fhhagenberg.sqe.model.Building;

/**
 * Factory to create all data models.
 */
public class ModelFactory {

    /**
     * Create the Building for an ECC.
     */
    private Building building;
    public Building getBuilding() {
        if (building == null) {
            building = new Building();
        }
        return building;
    }
}
