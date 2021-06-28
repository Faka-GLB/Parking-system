package com.onboarding.parkingsystemjava.mvp.presenter;

import com.onboarding.parkingsystemjava.listener.DateTimeListener;
import com.onboarding.parkingsystemjava.mvp.contract.DateTimePickerContract;

public class DateTimePickerPresenter implements DateTimePickerContract.DateTimePickerPresenter {
    private final DateTimePickerContract.DateTimePickerView view;
    private final boolean isStartButton;

    public DateTimePickerPresenter(DateTimePickerContract.DateTimePickerView view, boolean isStartButton) {
        this.view = view;
        this.isStartButton = isStartButton;
    }

    @Override
    public void onOkButtonPress(DateTimeListener dateTimeListener) {
        if (isStartButton) {
            view.sendStartDateTime(dateTimeListener);
        } else {
            view.sendEndDateTime(dateTimeListener);
        }
        view.dismissDateTimePicker();
    }

    @Override
    public void onCancelButtonPress() {
        view.dismissDateTimePicker();
    }
}
