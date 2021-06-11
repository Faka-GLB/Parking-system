package com.onboarding.parkingsystemjava.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.onboarding.parkingsystemjava.databinding.ActivityMainBinding;
import com.onboarding.parkingsystemjava.mvp.contract.MainActivityContract;
import com.onboarding.parkingsystemjava.mvp.model.ParkingModel;
import com.onboarding.parkingsystemjava.mvp.presenter.ParkingPresenter;
import com.onboarding.parkingsystemjava.mvp.view.ParkingView;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private MainActivityContract.MainActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        presenter = new ParkingPresenter(new ParkingModel(), new ParkingView(this, binding));

        setListeners();
    }

    private void setListeners() {
        binding.buttonMainSetParkingSpaces.setOnClickListener(view -> presenter.onSetParkingButtonPressed());
    }
}
