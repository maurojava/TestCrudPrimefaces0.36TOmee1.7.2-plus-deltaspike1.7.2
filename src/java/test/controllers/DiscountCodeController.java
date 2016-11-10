package test.controllers;

import test.controllers.util.MobilePageController;
import test.entities.DiscountCode;
import test.ejbs.DiscountCodeFacade;
import javax.inject.Named;
import org.apache.deltaspike.core.api.scope.ViewAccessScoped;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Named(value = "discountCodeController")
@ViewAccessScoped
public class DiscountCodeController extends AbstractController<DiscountCode> {

    @Inject
    private DiscountCodeFacade ejbFacade;
    @Inject
    private CustomerController customerListController;
    @Inject
    private MobilePageController mobilePageController;

    /**
     * Initialize the concrete DiscountCode controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public DiscountCodeController() {
        // Inform the Abstract parent controller of the concrete DiscountCode Entity
        super(DiscountCode.class);
    }

    /**
     * Passes collection of Customer entities that are retrieved from
     * DiscountCode?cap_first and returns the navigation outcome.
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
