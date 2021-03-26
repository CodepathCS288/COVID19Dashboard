package com.example.covid_dash;

import org.json.JSONException;
import org.json.JSONObject;

public class StateDetailGetter {
    JSONObject response;
    private String state;
    private int population;
    private int overall;
    private int infectionRate;
    private int cases;
    private int newCases;
    private int deaths;
    private int newDeaths;
    private int positiveTests;
    private int negativeTests;
    private int vaccinesDistributed;
    private int vaccinationsInitiated;
    private int vaccinationsCompleted;
    private int vaccinesAdministered;

    public StateDetailGetter(JSONObject response){
        this.response = response;
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
