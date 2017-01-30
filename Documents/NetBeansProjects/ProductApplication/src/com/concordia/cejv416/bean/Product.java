/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.concordia.cejv416.bean;

import java.util.Date;

/**
 *
 * @author afshin
 */
public class Product implements Comparable<Product> {

    private String description;
    private String code;
    private Double price;
    private Address productionLocation;
    private Date productionDate;

    /**
     * Full constructor
     * @param code
     * @param description
     * @param price
     * @param productionLocation
     * @param productionDate 
     */
    public Product(String code, String description, double price, Address productionLocation, Date productionDate) {
        this.description = description;
        this.code = code;
        this.price = price;
        this.productionLocation = productionLocation;
        this.productionDate = productionDate;
    }

    /**
     * Partial Constructor
     * @param code
     * @param description
     * @param price 
     */
    public Product(String code, String description, double price) {
        this.description = description;
        this.code = code;
        this.price = price;
        this.productionLocation = new Address();
        this.productionDate = new Date();
    }

    /**
     * Constructor for Binary Search
     * @param code 
     */
    public Product(String code) {
        this.code = code;
    }
    
    /**
     * Copy Constructor
     * @param p 
     */

    public Product(Product p) {
        this.description = p.getDescription();
        this.code = p.getCode();
        this.price = p.getPrice();
        this.productionLocation = new Address(p.getProductionLocation());
        this.productionDate = p.getProductionDate();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Address getProductionLocation() {
        return productionLocation;
    }

    public void setProductionLocation(Address productionLocation) {
        this.productionLocation = productionLocation;
    }

    public Date getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(Date productionDate) {
        this.productionDate = productionDate;
    }

    /**
     * We assume two product equal if they have the same code
     * @param other
     * @return 
     */
    public boolean equals(Product other) {
        if (other == null) {
            return false;
        }
        return other.getCode().equals(getCode());
    }

    //Returns a String representation of the Product
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Product Code = ");
        sb.append(getCode());
        sb.append(", Product Name = ");
        sb.append(getDescription());
        sb.append(", Price = ");
        sb.append(getPrice());
        return sb.toString();
    }

    /**
     * this method is used in sort and binary search
     * @param other
     * @return 
     */
    @Override
    public int compareTo(Product other) {
        return this.getCode().compareTo(other.getCode());
    }

}
