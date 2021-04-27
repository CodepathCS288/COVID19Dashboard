package com.example.covid_dash;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Parcel
public class NYT {
    String headline;
    String description;
    String timeStamp;
    String link;

    public NYT() {}

    public NYT (JSONObject jsonObject) throws JSONException {
        headline = jsonObject.getJSONObject("headline").getString("main");
        description = jsonObject.getString("abstract");
        link = jsonObject.getString("web_url");
        timeStamp = jsonObject.getString("pub_date");
    }

    public static List<NYT> fromJsonArray(JSONArray nytJsonArray) throws JSONException {
        List<NYT> nyts = new ArrayList<>();
        for (int i = 0; i < nytJsonArray.length(); i++) {
            nyts.add(new NYT(nytJsonArray.getJSONObject(i)));
        }
        return nyts;
    }

    public String getHeadline() {
        return headline;
    }

    public String getDescription() {
        return description;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public String getLink() {
        return link;
    }
}
