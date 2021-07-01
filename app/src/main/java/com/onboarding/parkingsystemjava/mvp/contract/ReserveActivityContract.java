package com.onboarding.parkingsystemjava.mvp.contract;

import com.onboarding.parkingsystemjava.entity.Reservation;
import com.onboarding.parkingsystemjava.utils.ReserveComprobation;
import java.util.Calendar;

public interface ReserveActivityContract {
    interface ReservePresenter {
        void onStartDateButtonPress();
        void onEndDateButtonPress();
        void onOkButtonPress();
        void onCancelButtonPress();
        void setReservationStartDate(Calendar startDate);
        void setReservationEndDate(Calendar endDate);
    }

    interface ReserveView {
        void showDateTimePicker(boolean isStartDate);
        void setStartDateTextView(String date);
        void setEndDateTextView(String date);
        void returnToMainActivity();
        int getParkingLot();
        String getUserPassword();
        void showMissingStartDateToast();
        void showMissingEndDateToast();
        void showMissingParkingLotToast();
        void showMissingUserPasswordToast();
        void showReserveSavedToast();
        void showReservationOverlapToast();
    }

    interface ReserveModel {
        void addReservation(Reservation reservation);
        Reservation getReservation();
        void setStartDate(Calendar startDate);
        void setEndDate(Calendar calendar);
        ReserveComprobation reserveFieldsCheck(Reservation reservation);
        void setReservationInfo(int parkingLot, String userPassword);
        Calendar getStartDate();
        Calendar getEndDate();
    }
}
