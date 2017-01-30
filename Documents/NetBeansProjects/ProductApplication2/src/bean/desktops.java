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
public class desktops extends Computers {

    private String machineSize;
    private double machineWeight;

    public desktops(String na, double pr, String ser, String unit, double wt, Date pd) { // constructor
        super(na, pr, ser, unit, wt, pd);
    }

    public void setMachineSize(String machineSize) {   // setters
        this.machineSize = machineSize;
    }

    public void setMachineWeight(double machineWeight) {
        this.machineWeight = machineWeight;
    }

    public String getMachineSize() {  // getters
        return machineSize;
    }

    public double getMachineWeight() {
        return machineWeight;
    }

}
