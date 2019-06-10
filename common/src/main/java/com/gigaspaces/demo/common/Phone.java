package com.gigaspaces.demo.common;

import java.util.Objects;


public class Phone {

    private String countryCode;

    private String areaCode;

    private String phoneNum;

    public Phone() {
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    @Override
    public String toString() {
        return ( countryCode == null || countryCode.equals("") ? "" : "+" + countryCode + "-") + areaCode + "-" + phoneNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Phone)) return false;
        Phone phone = (Phone) o;
        return Objects.equals(getCountryCode(), phone.getCountryCode()) &&
                Objects.equals(getAreaCode(), phone.getAreaCode()) &&
                Objects.equals(getPhoneNum(), phone.getPhoneNum());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCountryCode(), getAreaCode(), getPhoneNum());
    }
}
