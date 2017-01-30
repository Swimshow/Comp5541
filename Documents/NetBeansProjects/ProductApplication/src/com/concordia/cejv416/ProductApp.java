/**
 * Includes two test for product DAO Implementations (array and arrayList)
 */
package com.concordia.cejv416;

import com.concordia.cejv416.bean.Product;
import com.concordia.cejv416.dao.ProductDBArrayImpl;
import com.concordia.cejv416.dao.ProductDBListImpl;
import com.concordia.cejv416.dao.ProductDao;

/**
 *
 * @author afshin
 */
public class ProductApp {

    ProductDao productDao = new ProductDBListImpl();
    ProductDao productDao2 = new ProductDBArrayImpl();

    private void perform() {
        testListImplementation();
        testArrayImplementation();

    }

    private void testListImplementation() {
        productDao.addProduct("test", "Test", 123);
        System.out.println(productDao.listProducts());
        productDao.deleteProduct("test");
        System.out.println(productDao.listProducts());

        productDao.addProduct("test2", "test2", 123);
        productDao.updateProduct("test2", "test3", 241);
        System.out.println(productDao.listProducts());
        Product p = productDao.fetchProduct("test2");
        System.out.println(p.getPrice());
        productDao.shuffleCatalog();
        System.out.println(productDao.listProducts());

        System.out.println("SORTED:");
        productDao.sortCatalog();

        System.out.println(productDao.listProducts());
    }

    private void testArrayImplementation() {
        productDao2.addProduct("test", "Test", 123);
        System.out.println(productDao2.listProducts());

        productDao2.addProduct("test2", "test2", 123);
        productDao2.updateProduct("test2", "test3", 241);
        System.out.println(productDao2.listProducts());
        Product p = productDao2.fetchProduct("test2");
        System.out.println(p.getPrice());
        System.out.println(productDao2.listProducts());

        System.out.println("SORTED:");
        productDao2.sortCatalog();

        System.out.println(productDao2.listProducts());
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ProductApp application = new ProductApp();
        application.perform();
    }

}
