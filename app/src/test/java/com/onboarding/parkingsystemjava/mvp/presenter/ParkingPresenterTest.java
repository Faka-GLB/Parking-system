package com.onboarding.parkingsystemjava.mvp.presenter;

import com.onboarding.parkingsystemjava.database.ParkingDatabase;
import com.onboarding.parkingsystemjava.mvp.contract.MainActivityContract;
import com.onboarding.parkingsystemjava.mvp.model.ParkingModel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.StrictStubs.class)
public class ParkingPresenterTest {
    public static int PARKING_LOT = 4;
    public static int ZERO = 0;
    private MainActivityContract.MainActivityPresenter parkingPresenter;
    private MainActivityContract.MainActivityModel parkingModel;
    @Mock
    private MainActivityContract.MainActivityView parkingView;

    @Before
    public void setUp() {
        parkingModel = new ParkingModel(ParkingDatabase.getInstance());
        parkingPresenter = new ParkingPresenter(parkingModel, parkingView);
    }

    @Test
    public void onSetParkingButtonPressedTest() {
        parkingPresenter.onSetParkingButtonPressed();
        verify(parkingView).showConfigureParkingLotsDialogFragment();
    }

    @Test
    public void setParkingLotsTest() {
        parkingPresenter.setParkingLots(PARKING_LOT);
        assertEquals(PARKING_LOT, ParkingDatabase.getInstance().getParkingLots());
    }

    @Test
    public void onNewReservationButtonPressedTest() {
        parkingPresenter.onNewReservationButtonPressed();
        assertEquals(ZERO, parkingModel.removeOldReservations());
        verify(parkingView).showNewReservationActivity();
    }

    @Test
    public void removeOldReservationsTest() {
        parkingPresenter.removeOldReservations();
        assertEquals(ZERO, parkingModel.removeOldReservations());
        verify(parkingView).showReservationsRemovedToast(parkingModel.removeOldReservations());
    }
}
