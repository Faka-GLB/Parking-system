package com.onboarding.parkingsystemjava.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import com.onboarding.parkingsystemjava.databinding.ConfigureParkingLotsDialogFragmentMainActivityBinding;
import com.onboarding.parkingsystemjava.listener.OnInputListener;
import com.onboarding.parkingsystemjava.mvp.contract.ConfigureParkingLotsContract;
import com.onboarding.parkingsystemjava.mvp.presenter.ConfigureParkingPresenter;
import com.onboarding.parkingsystemjava.mvp.view.ConfigureParkingView;
import com.onboarding.parkingsystemjava.utils.ConstantUtils;

public class ConfigureParkingLotsDialog extends DialogFragment {

    private ConfigureParkingLotsContract.SetParkingPresenter presenter;
    private ConfigureParkingLotsDialogFragmentMainActivityBinding binding;
    private OnInputListener inputListener;

    public static ConfigureParkingLotsDialog newInstance() {
        return new ConfigureParkingLotsDialog();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        binding = ConfigureParkingLotsDialogFragmentMainActivityBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        this.presenter = new ConfigureParkingPresenter(this.inputListener, new ConfigureParkingView((DialogFragment)this, binding));
        setListeners();

        return view;
    }

    public void setListeners() {
        binding.btnDialogFragmentOk.setOnClickListener(view -> presenter.OnOkButtonPress());
        binding.btnDialogFragmentCancel.setOnClickListener(view -> presenter.OnCancelButtonPress());
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            inputListener = (OnInputListener) getActivity();
        } catch (ClassCastException e) {
            Log.e(ConstantUtils.CONFIGURE_PARKING_LOTS_DIALOG_TAG, e.getClass().getCanonicalName() + e.getMessage());
        }
    }
}
