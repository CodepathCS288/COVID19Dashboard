package com.example.covid_dash.states;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.covid_dash.R;

import org.parceler.Parcels;

public class CasesDetail extends AppCompatActivity {

    TextView tvTotalCases;
    TextView tvTotalDeaths;
    TextView tvNewCases;
    TextView tvNewDeaths;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StateDetailGetter details = (StateDetailGetter) Parcels.unwrap(getIntent().getParcelableExtra("details"));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cases_detail);

        tvTotalCases = findViewById(R.id.tvTotalCases);
        tvTotalDeaths = findViewById(R.id.tvTotalDeaths);
        tvNewCases = findViewById(R.id.tvNewCases);
        tvNewDeaths = findViewById(R.id.tvNewDeaths);

        tvTotalCases.setText("Total Cases\n" + details.getCases());
        tvTotalDeaths.setText("Total Deaths\n" + details.getDeaths());
        tvNewCases.setText("New Cases\n" + details.getNewCases());
        tvNewDeaths.setText("New Deaths\n" + details.getNewDeaths());
    }
}