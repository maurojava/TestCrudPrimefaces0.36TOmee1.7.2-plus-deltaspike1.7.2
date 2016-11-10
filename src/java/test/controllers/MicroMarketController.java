package test.controllers;

import test.controllers.util.MobilePageController;
import test.entities.MicroMarket;
import javax.inject.Named;
import org.apache.deltaspike.core.api.scope.ViewAccessScoped;
import javax.inject.Inject;

@Named(value = "microMarketController")
@ViewAccessScoped
public class MicroMarketController extends AbstractController<MicroMarket> {

    @Inject
    private CustomerController customerListController;
    @Inject
    private MobilePageController mobilePageController;

    public MicroMarketController() {
        // Inform the Abstract parent controller of the concrete MicroMarket Entity
        super(MicroMarket.class);
    }

    /**
     * Passes collection of Customer entities that are retrieved from
     * MicroMarket?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for Customer page
     */
    public String navigateCustomerList() {
        if (this.getSelected() != null) {
            customerListController.setItems(this.getSelected().getCustomerList());
            customerListController.setLazyItems(this.getSelected().getCustomerList());
        }
        return this.mobilePageController.getMobilePagesPrefix() + "/crud/customer/index?faces-redirect=true";
    }

}
