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
public class Headphones extends Electronics {

    private String type;
    private String color;

    public Headphones(String na, double pr, String ser, String unit, double wt, Date pd) {  // constructors
        super(na, pr, ser, unit, wt, pd);
    }

    public void setType(String type) {  // setters
        this.type = type;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getType() {  // getters
        return type;
    }

    public String getColor() {
        return color;
    }

}
