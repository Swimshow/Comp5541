/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.concordia.cejv416.dao;


/**
 *
 * @author afshin
 */
public interface ProductWriter {
    void addProduct(String code, String description, double price);

    void updateProduct(String code, String description, double price);

    void deleteProduct(String code);
    
}
