package com.example.covid_dash.states;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.covid_dash.R;

import org.parceler.Parcels;

public class VaccinationsDetail extends AppCompatActivity {

    TextView tvVaccinationsInitiated;
    TextView tvVaccinationsDistributed;
    TextView tvVaccinationsCompleted;
    TextView tvVaccinationsAdministered;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        StateDetailGetter details = (StateDetailGetter) Parcels.unwrap(getIntent().getParcelableExtra("details"));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccinations_detail);

        tvVaccinationsInitiated = findViewById(R.id.tvVaccinationsInitiated);
        tvVaccinationsDistributed = findViewById(R.id.tvVaccinationsDistributed);
        tvVaccinationsCompleted = findViewById(R.id.tvVaccinationsCompleted);
        tvVaccinationsAdministered = findViewById(R.id.tvVaccinationsAdministered);

        tvVaccinationsInitiated.setText("Vaccines\nInitiated\n" + details.getVaccinationsInitiated());
        tvVaccinationsDistributed.setText("Vaccines\nDistributed\n" + details.getVaccinesDistributed());
        tvVaccinationsCompleted.setText("Vaccines\nCompleted\n" + details.getVaccinationsCompleted());
        tvVaccinationsAdministered.setText("Vaccines\nAdministered\n" + details.getVaccinesAdministered());
    }
}