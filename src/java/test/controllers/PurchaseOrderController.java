package test.controllers;

import test.controllers.util.MobilePageController;
import test.entities.PurchaseOrder;
import test.ejbs.PurchaseOrderFacade;
import javax.inject.Named;
import org.apache.deltaspike.core.api.scope.ViewAccessScoped;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Named(value = "purchaseOrderController")
@ViewAccessScoped
public class PurchaseOrderController extends AbstractController<PurchaseOrder> {

    @Inject
    private PurchaseOrderFacade ejbFacade;
    @Inject
    private CustomerController customerIdController;
    @Inject
    private ProductController productIdController;
    @Inject
    private MobilePageController mobilePageController;

    /**
     * Initialize the concrete PurchaseOrder controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public PurchaseOrderController() {
        // Inform the Abstract parent controller of the concrete PurchaseOrder Entity
        super(PurchaseOrder.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        customerIdController.setSelected(null);
        productIdController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Customer controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareCustomerId(ActionEvent event) {
        if (this.getSelected() != null && customerIdController.getSelected() == null) {
            customerIdController.setSelected(this.getSelected().getCustomerId());
        }
    }

    /**
     * Sets the "selected" attribute of the Product controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareProductId(ActionEvent event) {
        if (this.getSelected() != null && productIdController.getSelected() == null) {
            productIdController.setSelected(this.getSelected().getProductId());
        }
    }
}
