package com.onboarding.parkingsystemjava.utils;

import com.onboarding.parkingsystemjava.database.ParkingDatabase;
import com.onboarding.parkingsystemjava.entity.Reservation;
import java.util.ArrayList;
import java.util.Calendar;
import static com.onboarding.parkingsystemjava.utils.ReserveComprobation.COMPROBATION_OK;
import static com.onboarding.parkingsystemjava.utils.ReserveComprobation.MISSING_END;
import static com.onboarding.parkingsystemjava.utils.ReserveComprobation.MISSING_LOT;
import static com.onboarding.parkingsystemjava.utils.ReserveComprobation.MISSING_PASSWORD;
import static com.onboarding.parkingsystemjava.utils.ReserveComprobation.MISSING_START;
import static com.onboarding.parkingsystemjava.utils.ReserveComprobation.RESERVATION_OVERLAP;

public class ReservationVerifier {
    private final ParkingDatabase database;

    public ReservationVerifier(ParkingDatabase database) {
        this.database = database;
    }

    public ReserveComprobation verifyReservation(Reservation reservation) {
        if (reservation.getStartDate() == null) return MISSING_START;
        if (reservation.getEndDate() == null) return MISSING_END;
        if (reservation.getParkingLot() == ConstantUtils.LOT_NOT_SET_VALUE) return MISSING_LOT;
        if (reservation.getUserPassword().isEmpty()) return MISSING_PASSWORD;
        if (!isReservationOK(reservation)) return RESERVATION_OVERLAP;
        return COMPROBATION_OK;
    }

    public boolean isReservationOK(Reservation reserve) {
        Integer lot = reserve.getParkingLot();
        if (database.getReservations().containsKey(lot)) {
            ArrayList<Reservation> lotReservations = database.getReservations().get(lot);
            return checkArrayList(lotReservations, reserve);
        } else {
            return true;
        }
    }

    private boolean isOverlapping(Reservation reservation, Reservation anotherReservation) {
        Calendar reservationStartDate = reservation.getStartDate();
        Calendar reservationEndDate = reservation.getEndDate();
        Calendar anotherReservationStartDate = anotherReservation.getStartDate();
        Calendar anotherReservationEndDate = anotherReservation.getEndDate();

        //start or full overlap
        if (reservationStartDate.before(anotherReservationStartDate) &&
                reservationEndDate.after(anotherReservationStartDate)) {
            return true;
        }
        //end overlap
        if (reservationStartDate.before(anotherReservationEndDate) &&
                reservationEndDate.after(anotherReservationEndDate)) {
            return true;
        } else {
            //stored reservation full overlaps new reservation
            return reservationStartDate.after(anotherReservationStartDate) &&
                    reservationEndDate.before(anotherReservationEndDate);
        }
    }

    private boolean checkArrayList(ArrayList<Reservation> arrayList, Reservation reservation) {
        Reservation storedReservation;
        for (int i = ConstantUtils.INT_ZERO; i < arrayList.size(); i++) {
            storedReservation = arrayList.get(i);
            if (isOverlapping(storedReservation, reservation)) {
                return false;
            }
        }
        return true;
    }
}
