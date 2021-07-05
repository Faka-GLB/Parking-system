package com.onboarding.parkingsystemjava.database;

import com.onboarding.parkingsystemjava.entity.Reservation;
import java.util.ArrayList;
import java.util.HashMap;

public class ParkingDatabase {
    private static ParkingDatabase singleInstance = null;
    private final HashMap<Integer, ArrayList<Reservation>> reservations = new HashMap<>();
    private int parkingLots;

    private ParkingDatabase() {
    }

    public static ParkingDatabase getInstance() {
        if (singleInstance == null) {
            singleInstance = new ParkingDatabase();
        }
        return singleInstance;
    }

    public HashMap<Integer, ArrayList<Reservation>> getReservations() {
        return reservations;
    }

    public void addReservation(Reservation reserve) {
        int lot = reserve.getParkingLot();
        if (reservations.containsKey(lot)) {
            this.reservations.get(lot).add(reserve);
        } else {
            ArrayList<Reservation> arrayList = new ArrayList<>();
            arrayList.add(reserve);
            this.reservations.put(lot, arrayList);
        }
    }

    public int getParkingLots() {
        return parkingLots;
    }

    public void setParkingLots(int parkingLots) {
        this.parkingLots = parkingLots;
    }
}
