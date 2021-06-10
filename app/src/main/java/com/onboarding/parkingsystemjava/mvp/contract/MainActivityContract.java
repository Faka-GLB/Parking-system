package com.onboarding.parkingsystemjava.mvp.contract;

public interface MainActivityContract {

    interface MainActivityPresenter {
        void onSetParkingButtonPressed();
    }

    interface MainActivityModel {
        void setParkingLots();
        int getParkingLots();
    }

    interface MainActivityView {
        void showParkingLotsAvailable(int parkingLots);
    }
}
