package com.onboarding.parkingsystemjava.mvp.model;

import com.onboarding.parkingsystemjava.database.ParkingDatabase;
import com.onboarding.parkingsystemjava.entity.Reservation;
import com.onboarding.parkingsystemjava.mvp.contract.ReserveActivityContract;
import com.onboarding.parkingsystemjava.utils.ConstantUtils;
import com.onboarding.parkingsystemjava.utils.ReserveComprobation;
import java.util.Calendar;
import static com.onboarding.parkingsystemjava.utils.ReserveComprobation.COMPROBATION_OK;
import static com.onboarding.parkingsystemjava.utils.ReserveComprobation.MISSING_END;
import static com.onboarding.parkingsystemjava.utils.ReserveComprobation.MISSING_LOT;
import static com.onboarding.parkingsystemjava.utils.ReserveComprobation.MISSING_PASSWORD;
import static com.onboarding.parkingsystemjava.utils.ReserveComprobation.MISSING_START;

public class ReserveModel implements ReserveActivityContract.ReserveModel {
    private final ParkingDatabase database;
    private final Reservation reservation;

    public ReserveModel(ParkingDatabase database) {
        this.database = database;
        this.reservation = new Reservation();
    }

    @Override
    public void addReservation(Reservation reservation) {
        database.addReservations(reservation);
    }

    public Reservation getReservation() {
        return reservation;
    }

    @Override
    public void setStartDate(Calendar startDate) {
        this.reservation.setStartDate(startDate);
    }

    @Override
    public void setEndDate(Calendar endDate) {
        this.reservation.setEndDate(endDate);
    }

    public ReserveComprobation reserveFieldsCheck(Reservation reservation) {
        if (reservation.getStartDate() == null)
            return MISSING_START;
        else if (reservation.getEndDate() == null)
            return MISSING_END;
        else if (reservation.getParkingLot() == ConstantUtils.INT_MINUS_ONE)
            return MISSING_LOT;
        else if (reservation.getUserPassword().isEmpty())
            return MISSING_PASSWORD;
        else
            return COMPROBATION_OK;
    }

    @Override
    public void setReservationInfo(int parkingLot, String userPassword) {
        this.reservation.setParkingLot(parkingLot);
        this.reservation.setUserPassword(userPassword);
    }

    @Override
    public Calendar getStartDate() {
        return this.reservation.getStartDate();
    }

    @Override
    public Calendar getEndDate() {
        return this.reservation.getEndDate();
    }
}
