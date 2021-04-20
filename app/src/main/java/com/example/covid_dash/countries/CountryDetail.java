package com.example.covid_dash.countries;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.covid_dash.R;
import com.example.covid_dash.states.StateDetailGetter;

import org.parceler.Parcels;

public class CountryDetail extends Fragment {

    TextView tvCountryDetails;
    TextView tvPopulation;
    TextView tvTests;
    TextView tvCasesCountry;
    CountryDetailGetter details;


    public CountryDetail() {}

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        details = (CountryDetailGetter) Parcels.unwrap(getArguments().getParcelable("details"));
        return inflater.inflate(R.layout.activity_country_detail, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //setContentView(R.layout.activity_country_detail);

        tvCountryDetails = view.findViewById(R.id.tvCountryDetails);
        tvPopulation = view.findViewById(R.id.tvPopulation);
        tvTests = view.findViewById(R.id.tvTests);
        tvCasesCountry = view.findViewById(R.id.tvCasesCountry);

        tvCountryDetails.setText(details.getCountry());
        tvPopulation.setText(details.getPopulation());
        tvTests.setText(details.getTests());
        String cases = "cases: " + details.getCases() +
                "\n Today's Cases:" + details.getTodayCases() +
                "\n Deaths: " + details.getDeaths() +
                "\n Today's Death " + details.getTodayDeaths() +
                "\n Recovered " + details.getRecovered() +
                "\n Total Recovered" + details.getTodayRecovered();
        tvCountryDetails.setText(cases);
    }
}