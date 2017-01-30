/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.concordia.cejv416.dao;

import com.concordia.cejv416.bean.Product;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author afshin
 */
public class ProductDBListImpl implements ProductDao {

    private ArrayList<Product> productCatalog = new ArrayList<Product>();

    /**
     * Initializing the catalog
     */
    public ProductDBListImpl() {
        addProduct("coffee", "Regular Coffee", 2.5);
        addProduct("espsng", "Single Espresso", 2.5);
        addProduct("espdbl", "Double Espresso", 2.85);
        addProduct("latsng", "Single Shot Latte", 4);
        addProduct("latdbl", "DOuble Shot Latte", 2.5);

    }

    /**
     * Find the product and return a copy of that
     * if invalid returns null
     * @param code
     * @return 
     */
    @Override
    public Product fetchProduct(String code) {
        Product p = getProduct(code);
        if (p != null){
            return new Product(p);
        }
        return null;
    }

    /**
     * returns the size of the catalog
     * @return 
     */
    @Override
    public int getProductsCount() {
        return productCatalog.size();
    }

    /**
     * creates a new product with given arguments and add it to the catalog
     * @param code
     * @param description
     * @param price 
     */
    @Override
    public void addProduct(String code, String description, double price) {
        Product p = new Product(code, description, price);
        if (validate(p)) {
            productCatalog.add(p);
            sortCatalog();
        }
    }

    /**
     * Update a product of the given code with new price and description if exists
     * 
     * @param code
     * @param description
     * @param price 
     */
    @Override
    public void updateProduct(String code, String description, double price) {
        //ToAsk: Why I didn't use fetchProduct Method
        Product p = getProduct(code);
        if (p != null) {
        p.setDescription(description);
        p.setPrice(price);
        }

    }

    /**
     * Deletes a product with the given code if exists
     * @param code 
     */
    @Override
    public void deleteProduct(String code) {
        Product p = getProduct(code);
        if (p!= null)
            deleteProduct(p);
    }

    /**
     * A String representation of the catalog
     * @return 
     */
    @Override
    public String listProducts() {
        StringBuilder sb = new StringBuilder();
        String prefix = "";
        for (Product p : productCatalog) {
            sb.append(prefix);
            sb.append(p.toString());
            prefix = "\r\n";
        }
        return sb.toString();

    }

    /**
     * Sorts the catalog
     */
    @Override
    public void sortCatalog() {
        Collections.sort(productCatalog);
    }

    /**
     * Shuffles the Catalog
     */
    @Override
    public void shuffleCatalog() {
        Collections.shuffle(productCatalog);
    }

    /**
     * Deletes a product (used internally)
     * @param product 
     */
    private void deleteProduct(Product product) {
        productCatalog.remove(product);
    }

    /**
     * Validates a product before adding
     * @param p
     * @return 
     */
    private boolean validate(Product p) {
        if (p.getDescription().length() > MAX_LENGTH_FOR_PRODUCT_DESCRIPTION
                || p.getCode().length() > MAX_LENGTH_FOR_PRODUCT_CODE
                || p.getPrice() < 0) {
            return false;
        }
        if (productCatalog.contains(p)) {
            return false;
        }
        return true;
    }

    /**
     * Search for a product using binary search
     * @param code
     * @return 
     */
    private Product getProduct(String code)  {
        int index = Collections.binarySearch(productCatalog, new Product(code));
        if (index >  0)
            return productCatalog.get(index);
        else
           return null;
    }

}
