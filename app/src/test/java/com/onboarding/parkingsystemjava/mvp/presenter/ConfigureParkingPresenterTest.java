package com.onboarding.parkingsystemjava.mvp.presenter;

import com.onboarding.parkingsystemjava.listener.OnInputListener;
import com.onboarding.parkingsystemjava.mvp.contract.ConfigureParkingLotsContract;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.StrictStubs.class)
public class ConfigureParkingPresenterTest {

    public static String PARKING_LOT_STRING = "4";
    public static int PARKING_LOT = 4;
    public static String EMPTY_STRING_LOT = "";
    ConfigureParkingLotsContract.ConfigureParkingPresenter presenter;
    @Mock
    ConfigureParkingLotsContract.ConfigureParkingView view;
    @Mock
    OnInputListener listener;

    @Before
    public void setUp() {
        presenter = new ConfigureParkingPresenter(listener, view);
    }

    @Test
    public void onCancelButtonPressTest() {
        presenter.OnCancelButtonPress();
        verify(view).closeDialog();
    }

    @Test
    public void onOkButtonPressTestPass() {
        Mockito.when(view.getLots()).thenReturn(PARKING_LOT_STRING);
        presenter.OnOkButtonPress();
        verify(view).showConfirmToast(PARKING_LOT);
        verify(view).closeDialog();
    }

    @Test
    public void onOkButtonPressTestEmpty() {
        Mockito.when(view.getLots()).thenReturn(EMPTY_STRING_LOT);
        presenter.OnOkButtonPress();
        verify(view).showEmptyInputToast();
    }
}
