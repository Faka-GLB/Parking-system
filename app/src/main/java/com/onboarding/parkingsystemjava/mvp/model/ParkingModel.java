package com.onboarding.parkingsystemjava.mvp.model;

import com.onboarding.parkingsystemjava.mvp.contract.MainActivityContract;

public class ParkingModel implements MainActivityContract.MainActivityModel {
    private int parkingLots;

    public ParkingModel() {
        this.parkingLots = 0;
    }

    @Override
    public void setParkingLots(int setLots) {
        this.parkingLots = setLots;
    }
}
