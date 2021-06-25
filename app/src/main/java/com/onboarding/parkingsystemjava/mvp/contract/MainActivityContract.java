package com.onboarding.parkingsystemjava.mvp.contract;

public interface MainActivityContract {

    interface MainActivityPresenter {
        void onSetParkingButtonPressed();
        void setParkingLots(int lots);
        void onNewReservationButtonPressed();
    }

    interface MainActivityModel {
        void setParkingLots(int setLots);
    }

    interface MainActivityView {
        void showConfigureParkingLotsDialogFragment();
        void showNewReservationActivity();
    }
}
