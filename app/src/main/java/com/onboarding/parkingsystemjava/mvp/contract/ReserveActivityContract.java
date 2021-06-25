package com.onboarding.parkingsystemjava.mvp.contract;

public interface ReserveActivityContract {
    interface ReservePresenter {
        void onStartDateButtonPress();
        void onEndDateButtonPress();
        void onOkButtonPress();
        void onCancelButtonPress();
        void setStartDateTextView(String date);
        void setEndDateTextView(String date);
    }

    interface ReserveView {
        void showDateTimePicker(boolean isStartDate);
        void setStartDateTextView(String date);
        void setEndDateTextView(String date);
        void returnToMainActivity();
    }
}
