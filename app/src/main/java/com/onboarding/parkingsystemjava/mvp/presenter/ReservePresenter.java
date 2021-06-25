package com.onboarding.parkingsystemjava.mvp.presenter;

import com.onboarding.parkingsystemjava.mvp.contract.ReserveActivityContract;

public class ReservePresenter implements ReserveActivityContract.ReservePresenter {

    private final ReserveActivityContract.ReserveView view;

    public ReservePresenter(ReserveActivityContract.ReserveView view) {
        this.view = view;
    }

    @Override
    public void onStartDateButtonPress() {
        view.showDateTimePicker(true);
    }

    @Override
    public void onEndDateButtonPress() {
        view.showDateTimePicker(false);
    }

    @Override
    public void onOkButtonPress() {
        view.returnToMainActivity();
    }

    @Override
    public void onCancelButtonPress() {
        view.returnToMainActivity();
    }

    public void setStartDateTextView(String date) {
        view.setStartDateTextView(date);
    }

    public void setEndDateTextView(String date) {
        view.setEndDateTextView(date);
    }
}
