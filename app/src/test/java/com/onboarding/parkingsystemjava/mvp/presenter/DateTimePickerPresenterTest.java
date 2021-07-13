package com.onboarding.parkingsystemjava.mvp.presenter;

import com.onboarding.parkingsystemjava.listener.DateTimeListener;
import com.onboarding.parkingsystemjava.mvp.contract.DateTimePickerContract;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.StrictStubs.class)
public class DateTimePickerPresenterTest {
    DateTimePickerContract.DateTimePickerPresenter presenter;
    @Mock
    DateTimePickerContract.DateTimePickerView view;
    @Mock
    DateTimeListener dateTimeListener;

    @Before
    public void setUp() {
        presenter = new DateTimePickerPresenter(view, true);
    }

    @Test
    public void onCancelButtonPressTest() {
        presenter.onCancelButtonPress();
        verify(view).dismissDateTimePicker();
    }

    @Test
    public void onOkButtonPressTestTrue() {
        presenter.onOkButtonPress(dateTimeListener);
        verify(view).sendStartDateTime(dateTimeListener);
        verify(view).dismissDateTimePicker();
    }

    @Test
    public void onOkButtonPressTestFalse() {
        DateTimePickerContract.DateTimePickerPresenter presenterFalse = new DateTimePickerPresenter(view, false);
        presenterFalse.onOkButtonPress(dateTimeListener);
        verify(view).sendEndDateTime(dateTimeListener);
        verify(view).dismissDateTimePicker();
    }
}
