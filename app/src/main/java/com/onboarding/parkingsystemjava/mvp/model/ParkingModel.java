package com.onboarding.parkingsystemjava.mvp.model;

import com.onboarding.parkingsystemjava.database.ParkingDatabase;
import com.onboarding.parkingsystemjava.mvp.contract.MainActivityContract;

public class ParkingModel implements MainActivityContract.MainActivityModel {
    private final ParkingDatabase database;

    public ParkingModel(ParkingDatabase database) {
        this.database = database;
    }

    @Override
    public void setParkingLots(int setLots) {
        this.database.setParkingLots(setLots);
    }

    @Override
    public int removeOldReservations() {
        return database.deleteOldReservations();
    }
}
