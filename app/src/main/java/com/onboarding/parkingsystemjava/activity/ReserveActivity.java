package com.onboarding.parkingsystemjava.activity;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.onboarding.parkingsystemjava.database.ParkingDatabase;
import com.onboarding.parkingsystemjava.databinding.ActivityNewReservationBinding;
import com.onboarding.parkingsystemjava.listener.DateTimeListener;
import com.onboarding.parkingsystemjava.mvp.contract.ReserveActivityContract;
import com.onboarding.parkingsystemjava.mvp.model.ReserveModel;
import com.onboarding.parkingsystemjava.mvp.presenter.ReservePresenter;
import com.onboarding.parkingsystemjava.mvp.view.ReserveView;
import com.onboarding.parkingsystemjava.utils.ReservationVerifier;
import java.util.Calendar;

public class ReserveActivity extends AppCompatActivity implements DateTimeListener {
    private ReserveActivityContract.ReservePresenter presenter;
    private ActivityNewReservationBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = ActivityNewReservationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        this.presenter = new ReservePresenter(new ReserveView(this, binding), new ReserveModel(ParkingDatabase.getInstance(), new ReservationVerifier(ParkingDatabase.getInstance())));
        setListeners();
    }

    public void setListeners() {
        binding.btnNewReservationActivityStartDate.setOnClickListener(view -> presenter.onStartDateButtonPress());
        binding.btnNewReservationActivityEndDate.setOnClickListener(view -> presenter.onEndDateButtonPress());
        binding.btnNewReservationActivityOk.setOnClickListener(view -> presenter.onOkButtonPress());
        binding.btnNewReservationActivityCancel.setOnClickListener(view -> presenter.onCancelButtonPress());
    }

    @Override
    public void sendStartDateTime(Calendar dateTimeCalendar) {
        presenter.setReservationStartDate(dateTimeCalendar);
    }

    @Override
    public void sendEndDateTime(Calendar dateTimeCalendar) {
        presenter.setReservationEndDate(dateTimeCalendar);
    }
}
