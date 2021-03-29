package com.example.covid_dash.states;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.covid_dash.R;

import org.parceler.Parcels;

public class CasesDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StateDetailGetter details = (StateDetailGetter) Parcels.unwrap(getIntent().getParcelableExtra("details"));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cases_detail);
    }
}