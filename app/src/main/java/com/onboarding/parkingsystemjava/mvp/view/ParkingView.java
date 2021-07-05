package com.onboarding.parkingsystemjava.mvp.view;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.onboarding.parkingsystemjava.R;
import com.onboarding.parkingsystemjava.activity.ReserveActivity;
import com.onboarding.parkingsystemjava.fragment.ConfigureParkingLotsDialog;
import com.onboarding.parkingsystemjava.mvp.contract.MainActivityContract;
import com.onboarding.parkingsystemjava.mvp.view.base.ActivityView;
import com.onboarding.parkingsystemjava.utils.ConstantUtils;

public class ParkingView extends ActivityView implements MainActivityContract.MainActivityView {

    public ParkingView(Activity activity) {
        super(activity);
    }

    @Override
    public void showConfigureParkingLotsDialogFragment() {
        Activity activity = getActivity();
        if (activity != null) {
            ConfigureParkingLotsDialog dialog = ConfigureParkingLotsDialog.newInstance();
            dialog.show(((AppCompatActivity) activity).getSupportFragmentManager(), ConstantUtils.PARKING_VIEW_TAG);
        }
    }

    @Override
    public void showNewReservationActivity() {
        Activity activity = getActivity();
        if (activity != null) {
            Intent intent = new Intent(activity, ReserveActivity.class);
            activity.startActivity(intent);
        }
    }

    @Override
    public void showReservationsRemovedToast(int deleted) {
        Activity activity = getActivity();
        if (activity != null) {
            Toast.makeText(activity, activity.getString(R.string.main_activity_reservations_removed_toast, deleted), Toast.LENGTH_SHORT).show();
        }
    }
}
