package com.onboarding.parkingsystemjava.mvp.view;

import android.app.Activity;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import com.onboarding.parkingsystemjava.activity.MainActivity;
import com.onboarding.parkingsystemjava.databinding.ActivityNewReservationBinding;
import com.onboarding.parkingsystemjava.fragment.DateTimePickerDialog;
import com.onboarding.parkingsystemjava.mvp.contract.ReserveActivityContract;
import com.onboarding.parkingsystemjava.mvp.view.base.ActivityView;
import com.onboarding.parkingsystemjava.utils.ConstantUtils;

public class ReserveView extends ActivityView implements ReserveActivityContract.ReserveView {

    private final ActivityNewReservationBinding binding;
    public ReserveView(Activity activity, ActivityNewReservationBinding binding) {
        super(activity);
        this.binding = binding;
    }

    @Override
    public void showDateTimePicker(boolean isStartDate) {
        Activity activity = getActivity();
        if (activity != null) {
            DateTimePickerDialog dialog = DateTimePickerDialog.newInstance(isStartDate);
            dialog.show(((AppCompatActivity)activity).getSupportFragmentManager(), ConstantUtils.RESERVATION_ACTIVITY_TAG);
        }
    }

    @Override
    public void setStartDateTextView(String date) {
        binding.tvNewReservationActivityStartDate.setText(date);
    }

    @Override
    public void setEndDateTextView(String date) {
        binding.tvNewReservationActivityEndDate.setText(date);
    }

    @Override
    public void returnToMainActivity() {
        Intent intent = new Intent(getActivity(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        getActivity().startActivity(intent);
    }
}
