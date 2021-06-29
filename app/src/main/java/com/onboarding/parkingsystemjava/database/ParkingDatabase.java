package com.onboarding.parkingsystemjava.database;

import com.onboarding.parkingsystemjava.entity.Reservation;
import java.util.ArrayList;

public class ParkingDatabase {
    private static ParkingDatabase singleInstance = null;
    private final ArrayList<Reservation> reservations = new ArrayList<>();
    private int parkingLots;

    private ParkingDatabase() {
    }

    public static ParkingDatabase getInstance() {
        if (singleInstance == null) {
            singleInstance = new ParkingDatabase();
        }
        return singleInstance;
    }

    public ArrayList<Reservation> getReservations() {
        return reservations;
    }

    public void addReservations(Reservation reservation) {
        this.reservations.add(reservation);
    }

    public int getParkingLots() {
        return parkingLots;
    }

    public void setParkingLots(int parkingLots) {
        this.parkingLots = parkingLots;
    }
}
