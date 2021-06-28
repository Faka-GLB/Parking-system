package com.onboarding.parkingsystemjava.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import com.onboarding.parkingsystemjava.databinding.DateTimePickerReservationFragmentBinding;
import com.onboarding.parkingsystemjava.listener.DateTimeListener;
import com.onboarding.parkingsystemjava.mvp.contract.DateTimePickerContract;
import com.onboarding.parkingsystemjava.mvp.presenter.DateTimePickerPresenter;
import com.onboarding.parkingsystemjava.mvp.view.DateTimePickerView;
import com.onboarding.parkingsystemjava.utils.ConstantUtils;

public class DateTimePickerDialog extends DialogFragment {
    private DateTimePickerContract.DateTimePickerPresenter presenter;
    private DateTimePickerReservationFragmentBinding binding;
    private DateTimeListener dateTimeListener;

    private static final String START_DATE_FLAG_TAG = "START_DATE_FLAG";

    public static DateTimePickerDialog newInstance(boolean isStartDate) {
        DateTimePickerDialog dialog = new DateTimePickerDialog();
        Bundle bundle = new Bundle();
        bundle.putBoolean(START_DATE_FLAG_TAG, isStartDate);
        dialog.setArguments(bundle);
        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.binding = DateTimePickerReservationFragmentBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        try {
            this.dateTimeListener = (DateTimeListener) getActivity();
        } catch (ClassCastException e) {
            Log.e(ConstantUtils.DATE_TIME_PICKER_DIALOG_TAG, e.getClass().getCanonicalName() + e.getMessage());
        }
        if (getArguments() != null) {
            this.presenter = new DateTimePickerPresenter(new DateTimePickerView(this, binding), getArguments().getBoolean(START_DATE_FLAG_TAG));
        }
        setListeners();
    }

    public void setListeners() {
        binding.btnReservationFragmentOk.setOnClickListener(view -> this.presenter.onOkButtonPress(this.dateTimeListener));
        binding.btnReservationFragmentCancel.setOnClickListener(view -> this.presenter.onCancelButtonPress());
    }
}
