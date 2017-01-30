/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.concordia.cejv416.bean;

/**
 *
 * @author afshin
 */
class Address {

    private int streetNumber;
    private String streetName;
    private String aptNo;
    private String postalCode;
    private String city;
    private String province;
    private final static String COUNTRY = "Canada";

    /**
     * Copy Constructor
    */
    Address(Address productionLocation) {
        this.streetNumber = productionLocation.streetNumber;
        this.streetName = productionLocation.streetName;
        this.aptNo = productionLocation.aptNo;
        this.postalCode = productionLocation.postalCode;
        this.city = productionLocation.city;
        this.province = productionLocation.province;

    }

    Address() {
        this.streetNumber = 0;
        this.streetName = "";
        this.aptNo = "";
        this.postalCode = "";
        this.city = "";
        this.province = "";
    }

    public int getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(int streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getAptNo() {
        return aptNo;
    }

    public void setAptNo(String aptNo) {
        this.aptNo = aptNo;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

}
