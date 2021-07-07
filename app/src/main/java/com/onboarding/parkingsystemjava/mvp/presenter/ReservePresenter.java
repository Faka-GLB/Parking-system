package com.onboarding.parkingsystemjava.mvp.presenter;

import com.onboarding.parkingsystemjava.entity.Reservation;
import com.onboarding.parkingsystemjava.mvp.contract.ReserveActivityContract;
import com.onboarding.parkingsystemjava.utils.ConstantUtils;
import com.onboarding.parkingsystemjava.utils.ReserveComprobation;
import java.util.Calendar;

public class ReservePresenter implements ReserveActivityContract.ReservePresenter {

    private final ReserveActivityContract.ReserveView view;
    private final ReserveActivityContract.ReserveModel model;


    public ReservePresenter(ReserveActivityContract.ReserveView view, ReserveActivityContract.ReserveModel model) {
        this.view = view;
        this.model = model;
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
        model.setReservationInfo(view.getParkingLot(), view.getUserPassword());
        Reservation reservation = model.getReservation();
        ReserveComprobation comprobation = model.reserveFieldsCheck(reservation);
        switch (comprobation) {
            case MISSING_START:
                view.showMissingStartDateToast();
                break;
            case MISSING_END:
                view.showMissingEndDateToast();
                break;
            case MISSING_LOT:
                view.showMissingParkingLotToast();
                break;
            case MISSING_PASSWORD:
                view.showMissingUserPasswordToast();
                break;
            case COMPROBATION_OK:
                view.showReserveSavedToast();
                break;
            case RESERVATION_OVERLAP:
                view.showReservationOverlapToast();
        }
        if (comprobation == ReserveComprobation.COMPROBATION_OK) {
            model.addReservation(reservation);
            view.returnToMainActivity();
        }
    }

    @Override
    public void onCancelButtonPress() {
        view.returnToMainActivity();
    }

    public void setReservationStartDate(Calendar startDate) {
        model.setStartDate(startDate);
        view.setStartDateTextView(getDateString(model.getStartDate()));
    }

    public void setReservationEndDate(Calendar endDate) {
        model.setEndDate(endDate);
        view.setEndDateTextView(getDateString(model.getEndDate()));
    }

    private String getDateString(Calendar date) {
        return (date.get(Calendar.DAY_OF_MONTH) + ConstantUtils.SLASH +
                (date.get(Calendar.MONTH) + ConstantUtils.MONTH_ADJUSTMENT) + ConstantUtils.SLASH +
                date.get(Calendar.YEAR) + ConstantUtils.SPACE +
                date.get(Calendar.HOUR) + ConstantUtils.COLON +
                date.get(Calendar.MINUTE));
    }
}
