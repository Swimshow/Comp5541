/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.concordia.cejv416.dao;

import com.concordia.cejv416.bean.Product;
import java.util.Arrays;

/**
 *
 * @author afshin
 */
public class ProductDBArrayImpl implements ProductDao {

    Product[] productCatalog = new Product[100];
    int size = 0;

    
    public ProductDBArrayImpl() {
        addProduct("coffee", "Regular Coffee", 2.5);
        addProduct("espsng", "Single Espresso", 2.5);
        addProduct("espdbl", "Double Espresso", 2.85);
        addProduct("latsng", "Single Shot Latte", 4);
        addProduct("latdbl", "DOuble Shot Latte", 2.5);

    }
    @Override
    public void sortCatalog() {
        Arrays.sort(productCatalog, 0, size);
    }

    @Override
    public void shuffleCatalog() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Product fetchProduct(String code) {
       return new Product(getProduct(code));
    }

    @Override
    public int getProductsCount() {
        return size;
    }

    @Override
    public String listProducts() {
        StringBuilder sb = new StringBuilder();
        String prefix = "";
        for (int i = 0; i < size; i++) {
            Product p = productCatalog[i];
            sb.append(prefix);
            sb.append(p.toString());
            prefix = "\r\n";
        }
        return sb.toString();

    }

    @Override
    public void addProduct(String code, String description, double price) {
        Product p = new Product(code, description, price);
        if (validate(p)) {
            productCatalog[size] = p;
            sortCatalog();
            size++;

        }
    }

    @Override
    public void updateProduct(String code, String description, double price) {
        Product p = getProduct(code);
        p.setDescription(description);
        p.setPrice(price);
    }

    @Override
    public void deleteProduct(String code) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

    }

    private boolean validate(Product p) {
        if (p.getDescription().length() > MAX_LENGTH_FOR_PRODUCT_DESCRIPTION
                || p.getCode().length() > MAX_LENGTH_FOR_PRODUCT_CODE
                || p.getPrice() < 0) {
            return false;
        }
        return true;
    }

    private Product getProduct(String code) {
        int index = Arrays.binarySearch(productCatalog,0, size, new Product(code));
        if (index >  0)
            return productCatalog[index];
        else
           return null;
    }

}
