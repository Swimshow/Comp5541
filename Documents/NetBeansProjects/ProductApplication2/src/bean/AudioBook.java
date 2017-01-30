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
public class AudioBook extends Book{
    private int fileSize;
    private int length;
    
    public AudioBook(String na, double pr, String ser, String unit, double wt, Date pd) {
        super(na, pr, ser, unit, wt, pd);
    }

    public void setFileSize(int fileSize) {
        this.fileSize = fileSize;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getFileSize() {
        return fileSize;
    }

    public int getLength() {
        return length;
    }
    
}
