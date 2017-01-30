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
public class Laptops extends Computers {

    private String screenSize;
    private double weight;
    private double thickness;

    public Laptops(String na, double pr, String ser, String unit, double wt, Date pd) { // constructor
        super(na, pr, ser, unit, wt, pd);
    }

    public void setScreenSize(String screenSize) { // setters
        this.screenSize = screenSize;
    }

    public void setThickness(double thickness) {
        this.thickness = thickness;
    }

    public String getScreenSize() {  // getters
        return screenSize;
    }

    public double getThickness() {
        return thickness;
    }

}
