package com.onboarding.parkingsystemjava.mvp.presenter;

import com.onboarding.parkingsystemjava.mvp.contract.MainActivityContract;

public class ParkingPresenter implements MainActivityContract.MainActivityPresenter {

    private MainActivityContract.MainActivityModel model;
    private MainActivityContract.MainActivityView view;

    public ParkingPresenter(MainActivityContract.MainActivityModel model, MainActivityContract.MainActivityView view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void onSetParkingButtonPressed() {
        model.setParkingLots();
        view.showParkingLotsAvailable(model.getParkingLots());
    }
}
