package com.onboarding.parkingsystemjava.activity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.onboarding.parkingsystemjava.database.ParkingDatabase;
import com.onboarding.parkingsystemjava.databinding.ActivityMainBinding;
import com.onboarding.parkingsystemjava.listener.OnInputListener;
import com.onboarding.parkingsystemjava.mvp.contract.MainActivityContract;
import com.onboarding.parkingsystemjava.mvp.model.ParkingModel;
import com.onboarding.parkingsystemjava.mvp.presenter.ParkingPresenter;
import com.onboarding.parkingsystemjava.mvp.view.ParkingView;

public class MainActivity extends AppCompatActivity implements OnInputListener {
    private ActivityMainBinding binding;
    private MainActivityContract.MainActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        presenter = new ParkingPresenter(new ParkingModel(ParkingDatabase.getInstance()), new ParkingView(this));
        setListeners();
    }

    private void setListeners() {
        binding.buttonMainSetParkingSpaces.setOnClickListener(view -> presenter.onSetParkingButtonPressed());
        binding.buttonMainNewReservation.setOnClickListener(view -> presenter.onNewReservationButtonPressed());
    }

    @Override
    public void sendInput(int input) {
        presenter.setParkingLots(input);
    }
}
