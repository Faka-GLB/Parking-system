package com.onboarding.parkingsystemjava.mvp.view;

import androidx.fragment.app.DialogFragment;
import com.onboarding.parkingsystemjava.databinding.DateTimePickerReservationFragmentBinding;
import com.onboarding.parkingsystemjava.listener.DateTimeListener;
import com.onboarding.parkingsystemjava.mvp.contract.DateTimePickerContract;
import com.onboarding.parkingsystemjava.mvp.view.base.FragmentView;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class DateTimePickerView extends FragmentView implements DateTimePickerContract.DateTimePickerView {

    private final DateTimePickerReservationFragmentBinding binding;

    public DateTimePickerView(DialogFragment dialogFragment, DateTimePickerReservationFragmentBinding binding) {
        super(dialogFragment);
        this.binding = binding;
    }

    @Override
    public void dismissDateTimePicker() {
        DialogFragment fragment = (DialogFragment) getFragment();
        if (fragment != null) {
            fragment.dismiss();
        }
    }

    @Override
    public void sendStartDateTime(DateTimeListener dateTimeListener) {
        dateTimeListener.sendStartDateTime(getCalendar());
    }

    @Override
    public void sendEndDateTime(DateTimeListener dateTimeListener) {
        dateTimeListener.sendEndDateTime(getCalendar());
    }

    private Calendar getCalendar() {
        Calendar dateTime = new GregorianCalendar();
        dateTime.set(binding.datePikerReservationFragment.getYear(),
                binding.datePikerReservationFragment.getMonth(),
                binding.datePikerReservationFragment.getDayOfMonth(),
                binding.timePickerReservationFragment.getHour(),
                binding.timePickerReservationFragment.getMinute());
        return dateTime;
    }
}
