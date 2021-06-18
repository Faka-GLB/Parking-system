package com.onboarding.parkingsystemjava.mvp.view;

import android.app.Activity;
import androidx.appcompat.app.AppCompatActivity;
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
            dialog.show(((AppCompatActivity)activity).getSupportFragmentManager(), ConstantUtils.PARKING_VIEW_TAG);
        }
    }
}