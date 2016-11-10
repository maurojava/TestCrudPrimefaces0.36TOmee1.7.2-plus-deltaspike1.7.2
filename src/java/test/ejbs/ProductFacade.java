/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.ejbs;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import test.entities.Product;

/**
 *
 * @author mauronew
 */
@Stateless
public class ProductFacade extends AbstractFacade<Product> {

    @PersistenceContext(unitName = "TestCrudPrimefaces0.36TOmee1.7.2-plus-deltaspike1.7.2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductFacade() {
        super(Product.class);
    }
    
}