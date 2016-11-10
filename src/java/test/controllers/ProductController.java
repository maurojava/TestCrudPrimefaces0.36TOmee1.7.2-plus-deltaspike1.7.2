package test.controllers;

import test.controllers.util.MobilePageController;
import test.entities.Product;
import javax.inject.Named;
import org.apache.deltaspike.core.api.scope.ViewAccessScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "productController")
@ViewAccessScoped
public class ProductController extends AbstractController<Product> {

    @Inject
    private PurchaseOrderController purchaseOrderListController;
    @Inject
    private ManufacturerController manufacturerIdController;
    @Inject
    private ProductCodeController productCodeController;
    @Inject
    private MobilePageController mobilePageController;

    public ProductController() {
        // Inform the Abstract parent controller of the concrete Product Entity
        super(Product.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        manufacturerIdController.setSelected(null);
        productCodeController.setSelected(null);
    }

    /**
     * Passes collection of PurchaseOrder entities that are retrieved from
     * Product?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for PurchaseOrder page
     */
    public String navigatePurchaseOrderList() {
        if (this.getSelected() != null) {
            purchaseOrderListController.setItems(this.getSelected().getPurchaseOrderList());
            purchaseOrderListController.setLazyItems(this.getSelected().getPurchaseOrderList());
        }
        return this.mobilePageController.getMobilePagesPrefix() + "/crud/purchaseOrder/index?faces-redirect=true";
    }

    /**
     * Sets the "selected" attribute of the Manufacturer controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareManufacturerId(ActionEvent event) {
        if (this.getSelected() != null && manufacturerIdController.getSelected() == null) {
            manufacturerIdController.setSelected(this.getSelected().getManufacturerId());
        }
    }

    /**
     * Sets the "selected" attribute of the ProductCode controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareProductCode(ActionEvent event) {
        if (this.getSelected() != null && productCodeController.getSelected() == null) {
            productCodeController.setSelected(this.getSelected().getProductCode());
        }
    }
}
