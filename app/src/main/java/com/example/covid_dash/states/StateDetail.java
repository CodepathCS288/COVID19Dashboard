package com.example.covid_dash.states;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.covid_dash.R;
import com.example.covid_dash.fragments.Search_State;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.parceler.Parcels;

public class StateDetail extends Fragment {

    TextView tvState;
    TextView tvPopulation;
    RatingBar rbRiskLevel;
    TextView tvCases;
    TextView tvPositiveTests;
    TextView tvNegativeTests;
    TextView tvInfectionRate;
    TextView tvVaccinations;
    StateDetailGetter details;

    public StateDetail() {}

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        details = (StateDetailGetter) Parcels.unwrap(getArguments().getParcelable("details"));
        return inflater.inflate(R.layout.activity_state_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("StateDetail", "onViewCreated: " + details.getState());
        tvState = view.findViewById(R.id.tvCasesDetails);
        tvPopulation = view.findViewById(R.id.tvPopulation);
        rbRiskLevel = view.findViewById(R.id.rbRiskLevel);
        tvCases = view.findViewById(R.id.tvCases);
        tvPositiveTests = view.findViewById(R.id.tvPositiveTests);
        tvNegativeTests = view.findViewById(R.id.tvNegativeTests);
        tvInfectionRate = view.findViewById(R.id.tvInfectionRate);
        tvVaccinations = view.findViewById(R.id.tvVaccinations);

        tvState.setText(details.getState());
        tvPopulation.setText("Population\n" + details.getPopulation());
        rbRiskLevel.setRating(details.getOverall());
        tvCases.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Cases clicked", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getContext(), CasesDetail.class);
                i.putExtra("details", Parcels.wrap(details));
                startActivity(i);
            }
        });
        tvPositiveTests.setText("Positive\nTests\n" + details.getPositiveTests());
        tvNegativeTests.setText("Negative\nTests\n" + details.getNegativeTests());
        tvInfectionRate.setText("Infection\nRate\n" + String.format("%.2f", details.getInfectionRate()));
        tvVaccinations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Vaccination clicked", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getContext(), VaccinationsDetail.class);
                i.putExtra("details", Parcels.wrap(details));
                startActivity(i);
            }
        });
    }
}