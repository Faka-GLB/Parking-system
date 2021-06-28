package com.onboarding.parkingsystemjava.mvp.contract;

public interface ConfigureParkingLotsContract {

    interface ConfigureParkingPresenter {
        void OnOkButtonPress();
        void OnCancelButtonPress();
    }

    interface ConfigureParkingView {
        String getLots();
        void closeDialog();
        void showEmptyInputToast();
        void showConfirmToast(int lots);
    }
}
