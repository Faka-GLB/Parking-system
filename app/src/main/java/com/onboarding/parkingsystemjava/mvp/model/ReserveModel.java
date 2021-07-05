package com.onboarding.parkingsystemjava.mvp.model;

import com.onboarding.parkingsystemjava.database.ParkingDatabase;
import com.onboarding.parkingsystemjava.entity.Reservation;
import com.onboarding.parkingsystemjava.mvp.contract.ReserveActivityContract;
import com.onboarding.parkingsystemjava.utils.ConstantUtils;
import com.onboarding.parkingsystemjava.utils.ReservationVerifier;
import com.onboarding.parkingsystemjava.utils.ReserveComprobation;
import java.util.Calendar;
import static com.onboarding.parkingsystemjava.utils.ReserveComprobation.COMPROBATION_OK;
import static com.onboarding.parkingsystemjava.utils.ReserveComprobation.MISSING_END;
import static com.onboarding.parkingsystemjava.utils.ReserveComprobation.MISSING_LOT;
import static com.onboarding.parkingsystemjava.utils.ReserveComprobation.MISSING_PASSWORD;
import static com.onboarding.parkingsystemjava.utils.ReserveComprobation.MISSING_START;
import static com.onboarding.parkingsystemjava.utils.ReserveComprobation.RESERVATION_OVERLAP;

public class ReserveModel implements ReserveActivityContract.ReserveModel {
    private final ParkingDatabase database;
    private final Reservation reservation;
    private ReservationVerifier verifier;

    public ReserveModel(ParkingDatabase database, ReservationVerifier verifier) {
        this.database = database;
        this.reservation = new Reservation();
        this.verifier = verifier;
    }

    @Override
    public void addReservation(Reservation reservation) {
        database.addReservation(reservation);
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
        return verifier.verifyReservation(reservation);
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
