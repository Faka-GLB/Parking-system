package com.onboarding.parkingsystemjava.mvp.contract;

import com.onboarding.parkingsystemjava.listener.DateTimeListener;

public interface DateTimePickerContract {

    interface DateTimePickerPresenter{
        void onOkButtonPress(DateTimeListener dateTimeListener);
        void onCancelButtonPress();
    }

    interface DateTimePickerView{
        void dismissDateTimePicker();
        void sendStartDateTime(DateTimeListener dateTimeListener);
        void sendEndDateTime(DateTimeListener dateTimeListener);
        String getDate();
    }
}
