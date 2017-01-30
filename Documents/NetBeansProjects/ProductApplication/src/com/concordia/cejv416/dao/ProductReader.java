/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.concordia.cejv416.dao;

import com.concordia.cejv416.bean.Product;

/**
 *
 * @author afshin
 */
public interface ProductReader {

    Product fetchProduct(String code);

    int getProductsCount();
    
    public String listProducts();

}
