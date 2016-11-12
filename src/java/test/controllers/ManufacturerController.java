package test.controllers;

import test.controllers.util.MobilePageController;
import test.entities.Manufacturer;
import test.ejbs.ManufacturerFacade;
import javax.inject.Named;
import org.apache.deltaspike.core.api.scope.ViewAccessScoped;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Named(value = "manufacturerController")
@ViewAccessScoped
public class ManufacturerController extends AbstractController<Manufacturer> {

    @Inject
    private ManufacturerFacade ejbFacade;
    @Inject
    private ProductController productListController;
    @Inject
    private MobilePageController mobilePageController;

    /**
     * Initialize the concrete Manufacturer controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

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
        Manufacturer attachedSelected = this.getAttachedSelected();

        if (attachedSelected != null) {
            productListController.setItems(this.getAttachedSelected().getProductList());
            productListController.setLazyItems(this.getAttachedSelected().getProductList());
        }
        return this.mobilePageController.getMobilePagesPrefix() + "/crud/product/index?faces-redirect=true";
    }

}
