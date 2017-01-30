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
public class Computers extends Electronics {

    private String cpuInfo;
    private int ramSize;

    public Computers(String na, double pr, String ser, String unit, double wt, Date pd) { // constructors
        super(na, pr, ser, unit, wt, pd);
    }

    public void setCpuInfo(String cpuInfo) {  // setters
        this.cpuInfo = cpuInfo;
    }

    public void setRamSize(int ramSize) {
        this.ramSize = ramSize;
    }

    public String getCpuInfo() {  // getters
        return cpuInfo;
    }

    public int getRamSize() {
        return ramSize;
    }

}
