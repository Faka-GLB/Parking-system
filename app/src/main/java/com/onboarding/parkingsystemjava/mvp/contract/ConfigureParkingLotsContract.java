package com.onboarding.parkingsystemjava.mvp.contract;

public interface ConfigureParkingLotsContract {

    interface SetParkingPresenter {
        void OnOkButtonPress();
        void OnCancelButtonPress();
    }

    interface SetParkingView {
        String getLots();
        void closeDialog();
        void showEmptyInputToast();
        void showConfirmToast(int lots);
    }
}
