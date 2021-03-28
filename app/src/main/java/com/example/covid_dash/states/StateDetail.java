package com.example.covid_dash.states;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.covid_dash.MainActivity;
import com.example.covid_dash.R;
import com.example.covid_dash.fragments.Search_State;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class StateDetail extends AppCompatActivity{

    TextView tvState;
    TextView tvPopulation;
    RatingBar rbRiskLevel;
    TextView tvCases;
    TextView tvPositiveTests;
    TextView tvNegativeTests;
    TextView tvInfectionRate;
    TextView tvVaccinations;

    StateDetailGetter details = new StateDetailGetter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_state_detail);
        Log.d("StateDetail", "onCreate: "+ details.getState());

        tvState = findViewById(R.id.tvState);
        tvPopulation = findViewById(R.id.tvPopulation);
        rbRiskLevel = findViewById(R.id.rbRiskLevel);
        tvCases = findViewById(R.id.tvCases);
        tvPositiveTests = findViewById(R.id.tvPositiveTests);
        tvNegativeTests = findViewById(R.id.tvNegativeTests);
        tvInfectionRate = findViewById(R.id.tvInfectionRate);
        tvVaccinations = findViewById(R.id.tvVaccinations);

        tvState.setText("TX");
        tvPopulation.setText("Population\n1234567890");
        rbRiskLevel.setRating(3);
        tvCases.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(StateDetail.this, "Cases clicked", Toast.LENGTH_SHORT).show();
            }
        });
        tvPositiveTests.setText("Positive\nTests\n1234567890");
        tvNegativeTests.setText("Negative\nTests\n1234567890");
        tvInfectionRate.setText("Infection\nRate\n1234567890");
        tvVaccinations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(StateDetail.this, "Vaccination clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }
}