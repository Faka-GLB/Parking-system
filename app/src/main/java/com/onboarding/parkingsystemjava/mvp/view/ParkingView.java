package com.onboarding.parkingsystemjava.mvp.view;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.onboarding.parkingsystemjava.R;
import com.onboarding.parkingsystemjava.databinding.ActivityMainBinding;
import com.onboarding.parkingsystemjava.mvp.contract.MainActivityContract;
import com.onboarding.parkingsystemjava.mvp.view.base.ActivityView;

public class ParkingView extends ActivityView implements MainActivityContract.MainActivityView {

    private final ActivityMainBinding binding;

    public ParkingView(Activity activity, ActivityMainBinding binding) {
        super(activity);
        this.binding = binding;
    }

    @Override
    public void showParkingLotsAvailable(int parkingLots) {
        Context context = getContext();
        if (context != null) {
            Toast.makeText(context, context.getString(R.string.main_activity_toast_set_parking_confirmed, parkingLots), Toast.LENGTH_LONG).show();
        }
    }
}
