package com.example.covid_dash.country;

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

    TextView tvCountryName;
    TextView tvCountryPopulation;
    TextView tvCountryTests;
    TextView tvCountryCases;
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

        tvCountryName = view.findViewById(R.id.tvCountryName);
        tvCountryPopulation = view.findViewById(R.id.tvCountryPopulation);
        tvCountryTests = view.findViewById(R.id.tvCountryTests);
        tvCountryCases = view.findViewById(R.id.tvCountryCases);

        tvCountryName.setText(details.getCountry());
        tvCountryPopulation.setText("Population\n" + details.getPopulation());
        tvCountryTests.setText("Tests\n" + details.getTests());
        String cases = " Cases: " + details.getCases() +
                "\n Today's Cases: " + details.getTodayCases() +
                "\n Deaths: " + details.getDeaths() +
                "\n Today's Death: " + details.getTodayDeaths() +
                "\n Recovered: " + details.getRecovered() +
                "\n Total Recovered: " + details.getTodayRecovered();
        tvCountryCases.setText(cases);
    }
}
