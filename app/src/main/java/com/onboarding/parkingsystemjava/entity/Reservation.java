package com.onboarding.parkingsystemjava.entity;

import com.onboarding.parkingsystemjava.utils.ConstantUtils;
import java.util.Calendar;

public class Reservation {
    private Calendar startDate;
    private Calendar endDate;
    private int parkingLot;
    private String userPassword;

    public Reservation() {
        this.parkingLot = ConstantUtils.LOT_NOT_SET_VALUE;
        this.startDate = null;
        this.endDate = null;
    }

    public Calendar getStartDate() {
        return startDate;
    }

    public void setStartDate(Calendar startDate) {
        this.startDate = startDate;
    }

    public Calendar getEndDate() {
        return endDate;
    }

    public void setEndDate(Calendar endDate) {
        this.endDate = endDate;
    }

    public int getParkingLot() {
        return parkingLot;
    }

    public void setParkingLot(int parkingLot) {
        this.parkingLot = parkingLot;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
