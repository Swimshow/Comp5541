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
public class Monitor extends Electronics {

    private String size;        // varibles declaration
    private String panelType;
    private String resolution;

    public Monitor(String na, double pr, String ser, String unit, double wt, Date pd) {  //constructor
        super(na, pr, ser, unit, wt, pd);
    }

    public void setSize(String size) {  // setters
        this.size = size;
    }

    public void setPanelType(String panelType) {
        this.panelType = panelType;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public String getSize() {   // getters
        return size;
    }

    public String getPanelType() {
        return panelType;
    }

    public String getResolution() {
        return resolution;
    }

}
