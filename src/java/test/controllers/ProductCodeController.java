package test.controllers;

import test.controllers.util.MobilePageController;
import test.entities.ProductCode;
import javax.inject.Named;
import org.apache.deltaspike.core.api.scope.ViewAccessScoped;
import javax.inject.Inject;

@Named(value = "productCodeController")
@ViewAccessScoped
public class ProductCodeController extends AbstractController<ProductCode> {

    @Inject
    private ProductController productListController;
    @Inject
    private MobilePageController mobilePageController;

    public ProductCodeController() {
        // Inform the Abstract parent controller of the concrete ProductCode Entity
        super(ProductCode.class);
    }

    /**
     * Passes collection of Product entities that are retrieved from
     * ProductCode?cap_first and returns the navigation outcome.
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
