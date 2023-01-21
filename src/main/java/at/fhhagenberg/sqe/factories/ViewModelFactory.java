package at.fhhagenberg.sqe.factories;

import at.fhhagenberg.sqe.viewmodel.ECCViewModel;

/**
 * Factory to create the view models.
 */
public class ViewModelFactory {

    /**
     * Reference to the ModelFactory.
     */
    private final ModelFactory modelFactory;

    /**
     * CTor.
     * @param modelFactory mf
     */
    public ViewModelFactory(ModelFactory modelFactory) {
        this.modelFactory = modelFactory;
    }

    /**
     * Create the ECCViewModel.
     */
    protected ECCViewModel eccViewModel;
    public ECCViewModel getEccViewModel() {
        if (eccViewModel == null) {
            eccViewModel = new ECCViewModel(modelFactory.getBuilding());
        }
        return eccViewModel;
    }
}
