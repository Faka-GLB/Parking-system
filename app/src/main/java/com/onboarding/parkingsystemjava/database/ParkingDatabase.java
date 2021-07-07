package com.onboarding.parkingsystemjava.database;

import com.onboarding.parkingsystemjava.entity.Reservation;
import com.onboarding.parkingsystemjava.utils.ConstantUtils;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
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

    public int deleteOldReservations() {
        Calendar currentTime = new GregorianCalendar();
        HashMap<Integer, ArrayList<Reservation>> reservations = this.getReservations();
        int deleted = ConstantUtils.INT_ZERO;
        for (int i = ConstantUtils.INT_ZERO; i <= this.parkingLots; i++) {
            if (reservations.containsKey(i)) {
                deleted += searchLotForOldReservation(currentTime, this.reservations.get(i));
                if (reservations.get(i).isEmpty()) {
                    reservations.remove(i);
                }
            }
        }
        return deleted;
    }

    private int searchLotForOldReservation(Calendar current, ArrayList<Reservation> reservations) {
        int deletedCurrentLot = ConstantUtils.INT_ZERO;
        for (int i = ConstantUtils.INT_ZERO; i < reservations.size(); i++) {
            Reservation reserve = reservations.get(i);
            if (current.after(reserve.getEndDate())) {
                reservations.remove(i); //this line resizes the arrayList in execution
                i--; //reposition the index so the arrayList iterates correctly
                deletedCurrentLot++;
            }
        }
        return deletedCurrentLot;
    }
}
