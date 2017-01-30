/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.util.Date;

/**
 *
 * @author aiken
 */
public class Electronics extends Product {

    private String brand;
    private String productionCountry;

    public Electronics(String na, double pr, String ser, String unit, double wt, Date pd) {
        super(na, pr, ser, unit, wt, pd);
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setProductionCountry(String productionCountry) {
        this.productionCountry = productionCountry;
    }

    public String getBrand() {
        return brand;
    }

    public String getProductionCountry() {
        return productionCountry;
    }

}
