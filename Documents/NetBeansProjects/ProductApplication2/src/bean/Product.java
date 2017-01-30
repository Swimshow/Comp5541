/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.util.Date;

/**
 *
 * @author c_hung
 */
public class Product {
    private String name;
    private double price;
    private String serial;
    private String unit_measure;
    private double weight;
    private Date productionDate;
    
    
    
    public Product (String na,double pr,String ser,String unit,double wt,Date pd){ // constractor
        this.name=na;
        this.price=pr;
        this.serial=ser;
        this.unit_measure=unit;
        this.weight=wt;
        this.productionDate=pd;
    }

    
    public double calculatePriceTaxIncluded(){
        return this.price*(1.15);
    }
    
    public String toString(){
        return "Name = "+this.name+", price = "+this.price;
    }
    
    // setors
    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public void setUnit_measure(String unit_measure) {
        this.unit_measure = unit_measure;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setProductionDate(Date productionDate) {
        this.productionDate = productionDate;
    }

    
    // getors
    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getSerial() {
        return serial;
    }

    public String getUnit_measure() {
        return unit_measure;
    }

    public double getWeight() {
        return weight;
    }

    public Date getProductionDate() {
        return productionDate;
    }
    
       
    
   
}
