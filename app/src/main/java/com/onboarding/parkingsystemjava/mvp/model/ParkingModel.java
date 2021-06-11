package com.onboarding.parkingsystemjava.mvp.model;

import com.onboarding.parkingsystemjava.mvp.contract.MainActivityContract;

public class ParkingModel implements MainActivityContract.MainActivityModel {

    private int parkingLots;

    public ParkingModel() {
        this.parkingLots = 0;
    }

    @Override
    public void setParkingLots() {
        this.parkingLots = 10; //hardcode on purpose
    }

    @Override
    public int getParkingLots() {
        return this.parkingLots;
    }
}
