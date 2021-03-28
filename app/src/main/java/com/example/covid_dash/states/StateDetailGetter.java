package com.example.covid_dash.states;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

@Parcel
public class StateDetailGetter{
    String state;
    int population;
    int overall;
    int infectionRate;
    int cases;
    int newCases;
    int deaths;
    int newDeaths;
    int positiveTests;
    int negativeTests;
    int vaccinesDistributed;
    int vaccinationsInitiated;
    int vaccinationsCompleted;
    int vaccinesAdministered;

    public StateDetailGetter(){}

    public StateDetailGetter(JSONObject response){
        try {
            state = new JSONObject(response.toString()).getString("state");
            population = new JSONObject(response.toString()).getInt("population");
            JSONObject risklevel = new JSONObject(response.toString()).getJSONObject("riskLevels");
            overall = risklevel.getInt("overall");
            infectionRate = risklevel.getInt("infectionRate");
            JSONObject actuals = new JSONObject(response.toString()).getJSONObject("actuals");
            cases = actuals.getInt("cases");
            newCases = actuals.getInt("newCases");
            deaths = actuals.getInt("deaths");
            newDeaths = actuals.getInt("newDeaths");
            positiveTests = actuals.getInt("positiveTests");
            negativeTests = actuals.getInt("negativeTests");
            vaccinesDistributed = actuals.getInt("vaccinesDistributed");
            vaccinationsInitiated = actuals.getInt("vaccinationsInitiated");
            vaccinationsCompleted = actuals.getInt("vaccinationsCompleted");
            vaccinesAdministered = actuals.getInt("vaccinesAdministered");

            Log.d("StateDetailGetter", "StateDetailGetter: "+ state+overall);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getState() {
        return state;
    }

    public int getPopulation() {
        return population;
    }

    public int getOverall() {
        return overall;
    }

    public int getInfectionRate() {
        return infectionRate;
    }

    public int getCases() {
        return cases;
    }

    public int getNewCases() {
        return newCases;
    }

    public int getDeaths() {
        return deaths;
    }

    public int getNewDeaths() {
        return newDeaths;
    }

    public int getPositiveTests() {
        return positiveTests;
    }

    public int getNegativeTests() {
        return negativeTests;
    }

    public int getVaccinesDistributed() {
        return vaccinesDistributed;
    }

    public int getVaccinationsInitiated() {
        return vaccinationsInitiated;
    }

    public int getVaccinationsCompleted() {
        return vaccinationsCompleted;
    }

    public int getVaccinesAdministered() {
        return vaccinesAdministered;
    }

}
