package com.onboarding.parkingsystemjava.mvp.presenter;

import com.onboarding.parkingsystemjava.database.ParkingDatabase;
import com.onboarding.parkingsystemjava.entity.Reservation;
import com.onboarding.parkingsystemjava.mvp.contract.ReserveActivityContract;
import com.onboarding.parkingsystemjava.mvp.model.ReserveModel;
import com.onboarding.parkingsystemjava.utils.ConstantUtils;
import com.onboarding.parkingsystemjava.utils.ReservationVerifier;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.Calendar;
import java.util.GregorianCalendar;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.StrictStubs.class)
public class ReservePresenterTest {
    public static String USER_PASSWORD = "password";
    public static int YEAR = 2021;
    public static int MONTH = 7;
    public static int START_DAY = 12;
    public static int END_DAY = 15;
    public static int END_DAY_OVERLAP = 17;
    public static int HOUR = 12;
    public static int MINUTE = 16;
    public static int ZERO = 0;
    public static int PARKING_LOT = 4;
    public static int LOT_NOT_SET = -1;
    public static String EMPTY_STRING_PASSWORD = "";
    ReserveActivityContract.ReservePresenter presenter;
    ReserveActivityContract.ReserveModel model;
    @Mock
    ReserveActivityContract.ReserveView view;

    @Before
    public void setUp() {
        model = new ReserveModel(ParkingDatabase.getInstance(), new ReservationVerifier(ParkingDatabase.getInstance()));
        presenter = new ReservePresenter(view, model);
    }

    private Calendar getStartDateCalendar() {
        Calendar calendar = new GregorianCalendar();
        calendar.set(YEAR, MONTH, START_DAY, HOUR, MINUTE);
        calendar.set(Calendar.SECOND, ZERO);
        calendar.set(Calendar.MILLISECOND, ZERO);
        return calendar;
    }

    private Calendar getEndDateCalendar() {
        Calendar calendar = new GregorianCalendar();
        calendar.set(YEAR, MONTH, END_DAY, HOUR, MINUTE);
        calendar.set(Calendar.SECOND, ZERO);
        calendar.set(Calendar.MILLISECOND, ZERO);
        return calendar;
    }

    @Test
    public void onOkButtonPressMissingStart() {
        when(view.getParkingLot()).thenReturn(PARKING_LOT);
        when(view.getUserPassword()).thenReturn(EMPTY_STRING_PASSWORD);
        presenter.onOkButtonPress();
        verify(view).showMissingStartDateToast();
    }

    @Test
    public void onOkButtonPressMissingEnd() {
        when(view.getParkingLot()).thenReturn(PARKING_LOT);
        when(view.getUserPassword()).thenReturn(EMPTY_STRING_PASSWORD);
        presenter.setReservationStartDate(getStartDateCalendar());
        assertEquals(getStartDateCalendar(), model.getStartDate());
        presenter.onOkButtonPress();
        verify(view).showMissingEndDateToast();
    }

    @Test
    public void onOkButtonPressMissingLot() {
        when(view.getParkingLot()).thenReturn(LOT_NOT_SET);
        when(view.getUserPassword()).thenReturn(EMPTY_STRING_PASSWORD);
        presenter.setReservationStartDate(getStartDateCalendar());
        presenter.setReservationEndDate(getEndDateCalendar());
        assertEquals(getEndDateCalendar(), model.getEndDate());
        presenter.onOkButtonPress();
        verify(view).showMissingParkingLotToast();
    }

    @Test
    public void onOkButtonPressMissingPassword() {
        when(view.getParkingLot()).thenReturn(PARKING_LOT);
        when(view.getUserPassword()).thenReturn(EMPTY_STRING_PASSWORD);
        presenter.setReservationStartDate(getStartDateCalendar());
        presenter.setReservationEndDate(getEndDateCalendar());
        presenter.onOkButtonPress();
        verify(view).showMissingUserPasswordToast();
    }

    @Test
    public void onOkButtonPressComprobationOk() {
        when(view.getParkingLot()).thenReturn(PARKING_LOT);
        when(view.getUserPassword()).thenReturn(USER_PASSWORD);
        presenter.setReservationStartDate(getStartDateCalendar());
        presenter.setReservationEndDate(getEndDateCalendar());
        presenter.onOkButtonPress();
        verify(view).showReserveSavedToast();
        Reservation testReservation = model.getReservation();
        assertEquals(getStartDateCalendar(), testReservation.getStartDate());
        assertEquals(getEndDateCalendar(), testReservation.getEndDate());
        assertEquals(PARKING_LOT, testReservation.getParkingLot());
        assertEquals(USER_PASSWORD, testReservation.getUserPassword());
        verify(view).returnToMainActivity();
    }

    @Test
    public void onOkButtonPressReservationOverlap() {
        model.addReservation(getOverlapReservation());
        when(view.getParkingLot()).thenReturn(PARKING_LOT);
        when(view.getUserPassword()).thenReturn(USER_PASSWORD);
        presenter.setReservationStartDate(getStartDateCalendar());
        presenter.setReservationEndDate(getEndDateCalendar());
        presenter.onOkButtonPress();
        verify(view).showReservationOverlapToast();
    }

    private Reservation getOverlapReservation() {
        Reservation reservation = new Reservation();
        reservation.setStartDate(getStartDateCalendar());
        Calendar endDate = getEndDateCalendar();
        endDate.set(Calendar.DAY_OF_MONTH, END_DAY_OVERLAP);
        reservation.setEndDate(endDate);
        reservation.setParkingLot(PARKING_LOT);
        reservation.setUserPassword(USER_PASSWORD);
        return reservation;
    }

    @Test
    public void onCancelButtonPressTest() {
        presenter.onCancelButtonPress();
        verify(view).returnToMainActivity();
    }

    @Test
    public void setReservationStartDateTest() {
        presenter.setReservationStartDate(getStartDateCalendar());
        assertEquals(getStartDateCalendar(), model.getReservation().getStartDate());
        verify(view).setStartDateTextView(getDateString(getStartDateCalendar()));
    }

    @Test
    public void setReservationEndDate() {
        presenter.setReservationEndDate(getEndDateCalendar());
        assertEquals(getEndDateCalendar(), model.getReservation().getEndDate());
        verify(view).setEndDateTextView(getDateString(getEndDateCalendar()));
    }

    private String getDateString(Calendar date) {
        return (date.get(Calendar.DAY_OF_MONTH) + ConstantUtils.SLASH +
                (date.get(Calendar.MONTH) + ConstantUtils.MONTH_ADJUSTMENT) + ConstantUtils.SLASH +
                date.get(Calendar.YEAR) + ConstantUtils.SPACE +
                date.get(Calendar.HOUR) + ConstantUtils.COLON +
                date.get(Calendar.MINUTE));
    }

    @Test
    public void onStartDateButtonPressTest() {
        presenter.onStartDateButtonPress();
        verify(view).showDateTimePicker(true);
    }

    @Test
    public void onEndDateButtonPressTest() {
        presenter.onEndDateButtonPress();
        verify(view).showDateTimePicker(false);
    }
}
