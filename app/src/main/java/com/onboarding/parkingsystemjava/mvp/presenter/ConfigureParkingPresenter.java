package com.onboarding.parkingsystemjava.mvp.presenter;

import com.onboarding.parkingsystemjava.listener.OnInputListener;
import com.onboarding.parkingsystemjava.mvp.contract.ConfigureParkingLotsContract;

public class ConfigureParkingPresenter implements ConfigureParkingLotsContract.SetParkingPresenter {
    private final ConfigureParkingLotsContract.SetParkingView view;
    private final OnInputListener inputListener;

    public ConfigureParkingPresenter(OnInputListener inputListener, ConfigureParkingLotsContract.SetParkingView view) {
        this.view = view;
        this.inputListener = inputListener;
    }

    @Override
    public void OnOkButtonPress() {
        String lots = view.getLots();
        if (!lots.isEmpty()) {
            int lotsInt = Integer.parseInt(view.getLots());
            inputListener.sendInput(lotsInt);
            view.showConfirmToast(lotsInt);
            view.closeDialog();
        } else {
            view.showEmptyInputToast();
        }
    }

    @Override
    public void OnCancelButtonPress() {
        view.closeDialog();
    }
}
