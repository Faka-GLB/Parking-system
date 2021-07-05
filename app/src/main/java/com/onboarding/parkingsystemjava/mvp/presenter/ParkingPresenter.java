package com.onboarding.parkingsystemjava.mvp.presenter;

import com.onboarding.parkingsystemjava.mvp.contract.MainActivityContract;

public class ParkingPresenter implements MainActivityContract.MainActivityPresenter {

    private final MainActivityContract.MainActivityModel model;
    private final MainActivityContract.MainActivityView view;

    public ParkingPresenter(MainActivityContract.MainActivityModel model, MainActivityContract.MainActivityView view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void onSetParkingButtonPressed() {
        view.showConfigureParkingLotsDialogFragment();
    }

    @Override
    public void setParkingLots(int lots) {
        model.setParkingLots(lots);
    }

    @Override
    public void onNewReservationButtonPressed() {
        view.showNewReservationActivity();
    }

    @Override
    public void removeOldReservations() {
        view.showReservationsRemovedToast(model.removeOldReservations());
    }
}
