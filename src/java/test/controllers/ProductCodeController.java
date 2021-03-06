package test.controllers;

import test.controllers.util.MobilePageController;
import test.entities.ProductCode;
import test.ejbs.ProductCodeFacade;
import javax.inject.Named;
import org.apache.deltaspike.core.api.scope.ViewAccessScoped;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Named(value = "productCodeController")
@ViewAccessScoped
public class ProductCodeController extends AbstractController<ProductCode> {

    @Inject
    private ProductCodeFacade ejbFacade;
    @Inject
    private ProductController productListController;
    @Inject
    private MobilePageController mobilePageController;

    /**
     * Initialize the concrete ProductCode controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

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
        ProductCode attachedSelected = this.getAttachedSelected();

        if (attachedSelected != null) {
            productListController.setItems(this.getAttachedSelected().getProductList());
            productListController.setLazyItems(this.getAttachedSelected().getProductList());
        }
        return this.mobilePageController.getMobilePagesPrefix() + "/crud/product/index?faces-redirect=true";
    }

}
