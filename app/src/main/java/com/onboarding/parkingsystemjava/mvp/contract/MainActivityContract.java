package com.onboarding.parkingsystemjava.mvp.contract;

public interface MainActivityContract {

    interface MainActivityPresenter {
        void onSetParkingButtonPressed();
        void setParkingLots(int lots);
        void onNewReservationButtonPressed();
        void removeOldReservations();
    }

    interface MainActivityModel {
        void setParkingLots(int setLots);
        int removeOldReservations();
    }

    interface MainActivityView {
        void showConfigureParkingLotsDialogFragment();
        void showNewReservationActivity();
        void showReservationsRemovedToast(int deleted);
    }
}
