package com.onboarding.parkingsystemjava.mvp.view;

import android.content.Context;
import android.widget.Toast;
import androidx.fragment.app.DialogFragment;
import com.onboarding.parkingsystemjava.R;
import com.onboarding.parkingsystemjava.databinding.ConfigureParkingLotsDialogFragmentMainActivityBinding;
import com.onboarding.parkingsystemjava.mvp.contract.ConfigureParkingLotsContract;
import com.onboarding.parkingsystemjava.mvp.view.base.FragmentView;

public class ConfigureParkingView extends FragmentView implements ConfigureParkingLotsContract.ConfigureParkingView {
    private final ConfigureParkingLotsDialogFragmentMainActivityBinding binding;

    public ConfigureParkingView(DialogFragment fragment, ConfigureParkingLotsDialogFragmentMainActivityBinding binding) {
        super(fragment);
        this.binding = binding;
    }

    @Override
    public String getLots() {
        return binding.editTextDialogFragmentParkingInput.getText().toString();
    }

    @Override
    public void closeDialog() {
        DialogFragment fragment = (DialogFragment) getFragment();
        if (fragment != null) {
            fragment.dismiss();
        }
    }

    @Override
    public void showEmptyInputToast() {
        Context context = getContext();
        if (context != null) {
            Toast.makeText(context, R.string.fragment_configure_parking_lots_toast_empty_input, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showConfirmToast(int lots) {
        Context context = getContext();
        if (context != null) {
            Toast.makeText(context, context.getString(R.string.fragment_configure_parking_lots_toast_confirm, lots), Toast.LENGTH_LONG).show();
        }
    }
}
