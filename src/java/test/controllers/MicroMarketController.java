package test.controllers;

import test.controllers.util.MobilePageController;
import test.entities.MicroMarket;
import test.ejbs.MicroMarketFacade;
import javax.inject.Named;
import org.apache.deltaspike.core.api.scope.ViewAccessScoped;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Named(value = "microMarketController")
@ViewAccessScoped
public class MicroMarketController extends AbstractController<MicroMarket> {

    @Inject
    private MicroMarketFacade ejbFacade;
    @Inject
    private CustomerController customerListController;
    @Inject
    private MobilePageController mobilePageController;

    /**
     * Initialize the concrete MicroMarket controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

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
        MicroMarket attachedSelected = this.getAttachedSelected();

        if (attachedSelected != null) {
            customerListController.setItems(this.getAttachedSelected().getCustomerList());
            customerListController.setLazyItems(this.getAttachedSelected().getCustomerList());
        }
        return this.mobilePageController.getMobilePagesPrefix() + "/crud/customer/index?faces-redirect=true";
    }

}
