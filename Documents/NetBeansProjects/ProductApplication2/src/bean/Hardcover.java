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
public class Hardcover extends Book {

    private int pages;
    private String printingLocation;
    
    public Hardcover(String na, double pr, String ser, String unit, double wt, Date pd) {
        super(na, pr, ser, unit, wt, pd);
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public void setPrintingLocation(String printingLocation) {
        this.printingLocation = printingLocation;
    }

    public int getPages() {
        return pages;
    }

    public String getPrintingLocation() {
        return printingLocation;
    }
    
}
