package com.onboarding.parkingsystemjava.mvp.view;

import androidx.fragment.app.DialogFragment;
import com.onboarding.parkingsystemjava.databinding.DateTimePickerReservationFragmentBinding;
import com.onboarding.parkingsystemjava.listener.DateTimeListener;
import com.onboarding.parkingsystemjava.mvp.contract.DateTimePickerContract;
import com.onboarding.parkingsystemjava.mvp.view.base.FragmentView;
import com.onboarding.parkingsystemjava.utils.ConstantUtils;

public class DateTimePickerView extends FragmentView implements DateTimePickerContract.DateTimePickerView {

    private final DateTimePickerReservationFragmentBinding binding;

    public DateTimePickerView(DialogFragment dialogFragment, DateTimePickerReservationFragmentBinding binding) {
        super(dialogFragment);
        this.binding = binding;
    }

    @Override
    public void dismissDateTimePicker() {
        DialogFragment fragment = getFragment();
        if (fragment != null) {
            fragment.dismiss();
        }
    }

    @Override
    public void sendStartDateTime(DateTimeListener dateTimeListener) {
        dateTimeListener.sendStartDateTime(getDate());
    }

    @Override
    public void sendEndDateTime(DateTimeListener dateTimeListener) {
        dateTimeListener.sendEndDateTime(getDate());
    }

    @Override
    public String getDate() {
        return (binding.datePikerReservationFragment.getDayOfMonth() + ConstantUtils.SLASH +
                binding.datePikerReservationFragment.getMonth() + ConstantUtils.SLASH +
                binding.datePikerReservationFragment.getYear() + ConstantUtils.SPACE +
                binding.timePickerReservationFragment.getHour() + ConstantUtils.COLON +
                binding.timePickerReservationFragment.getMinute());
    }
}
