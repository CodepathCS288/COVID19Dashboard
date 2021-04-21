package com.example.covid_dash.country;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

@Parcel
public class CountryDetailGetter {
    String country;
    int population;
    int tests;
    int cases;
    int todayCases;
    int deaths;
    int todayDeaths;
    int recovered;
    int todayRecovered;

    public CountryDetailGetter() {}

    public CountryDetailGetter(JSONObject response) {
        try {
            country = new JSONObject(response.toString()).getString("country");
            population = new JSONObject(response.toString()).getInt("population");
            tests = new JSONObject(response.toString()).getInt("tests");
            cases = new JSONObject(response.toString()).getInt("cases");
            todayCases = new JSONObject(response.toString()).getInt("todayCases");
            deaths = new JSONObject(response.toString()).getInt("deaths");
            todayDeaths = new JSONObject(response.toString()).getInt("todayDeaths");
            recovered = new JSONObject(response.toString()).getInt("recovered");
            todayRecovered = new JSONObject(response.toString()).getInt("todayRecovered");

            Log.d("CountryDetailGetter", "CountryDetailGetter: "+ country+tests);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getCountry() {
        return country;
    }

    public int getPopulation() {
        return population;
    }

    public int getTests() {
        return tests;
    }

    public int getCases() {
        return cases;
    }

    public int getTodayCases() {
        return todayCases;
    }

    public int getDeaths() {
        return deaths;
    }

    public int getTodayDeaths() {
        return todayDeaths;
    }

    public int getRecovered() {
        return recovered;
    }

    public int getTodayRecovered() {
        return todayRecovered;
    }
}