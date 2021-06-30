package com.onboarding.parkingsystemjava.mvp.view;

import android.app.Activity;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.onboarding.parkingsystemjava.R;
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
            dialog.show(((AppCompatActivity) activity).getSupportFragmentManager(), ConstantUtils.RESERVATION_ACTIVITY_TAG);
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
        Activity activity = getActivity();
        if (activity != null)
            getActivity().finish();
    }

    @Override
    public int getParkingLot() {
        String lot = binding.editTextNewReservationActivityLotNumber.getText().toString();
        if (lot.isEmpty()) {
            return ConstantUtils.INT_MINUS_ONE; //-1 used in model to check if user has set a parking lot
        } else
            return Integer.parseInt(lot);
    }

    @Override
    public String getUserPassword() {
        return binding.editTextNewReservationActivityPassword.getText().toString();
    }

    @Override
    public void showMissingStartDateToast() {
        showComprobationToast(R.string.reservation_view_missing_start_date_toast);
    }

    @Override
    public void showMissingEndDateToast() {
        showComprobationToast(R.string.reservation_view_missing_end_date_toast);
    }

    @Override
    public void showMissingParkingLotToast() {
        showComprobationToast(R.string.reservation_view_missing_parking_lot_toast);
    }

    @Override
    public void showMissingUserPasswordToast() {
        showComprobationToast(R.string.reservation_view_missing_user_password_toast);
    }

    @Override
    public void showReserveSavedToast() {
        showComprobationToast(R.string.reservation_view_reservation_saved_toast);
    }

    private void showComprobationToast(int messageId) {
        Activity activity = getActivity();
        if (activity != null) {
            Toast.makeText(activity, activity.getString(messageId), Toast.LENGTH_SHORT).show();
        }
    }
}
