package test.controllers;

import test.controllers.util.MobilePageController;
import test.entities.Manufacturer;
import javax.inject.Named;
import org.apache.deltaspike.core.api.scope.ViewAccessScoped;
import javax.inject.Inject;

@Named(value = "manufacturerController")
@ViewAccessScoped
public class ManufacturerController extends AbstractController<Manufacturer> {

    @Inject
    private ProductController productListController;
    @Inject
    private MobilePageController mobilePageController;

    public ManufacturerController() {
        // Inform the Abstract parent controller of the concrete Manufacturer Entity
        super(Manufacturer.class);
    }

    /**
     * Passes collection of Product entities that are retrieved from
     * Manufacturer?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for Product page
     */
    public String navigateProductList() {
        if (this.getSelected() != null) {
            productListController.setItems(this.getSelected().getProductList());
            productListController.setLazyItems(this.getSelected().getProductList());
        }
        return this.mobilePageController.getMobilePagesPrefix() + "/crud/product/index?faces-redirect=true";
    }

}
