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
public interface ProductDao extends ProductConstants, ProductReader, ProductWriter {
    public void sortCatalog();
    public void shuffleCatalog();
}
