package com.example.covid_dash.fragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.covid_dash.BuildConfig;
import com.example.covid_dash.R;

import org.json.JSONObject;
import org.parceler.Parcels;

public class Search_Country extends Fragment {
    private String[] countries;
    private AutoCompleteTextView etCountrySearch;
    private Button btn_Country_Search;

    public Search_Country() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_search__country, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        etCountrySearch = view.findViewById(R.id.etCountrySearch);
        btn_Country_Search = view.findViewById(R.id.btn_Country_Search);
        countries = getResources().getStringArray(R.array.countries);

        ArrayAdapter<String> adpater = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, countries);
        etCountrySearch.setAdapter(adpater);

        etCountrySearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                btn_Country_Search.setEnabled(false);
                onEnter();

            }
        });

        btn_Country_Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onEnter();
            }
        });
    }

    //call a api get method
    private void viewStateDetail(String country) {
        RequestQueue queue = Volley.newRequestQueue(getContext());
        String url = "https://corona.lmao.ninja/v2/countries/"+country;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        CountryDetailGetter details = new CountryDetailGetter(response);
                        //start the fragment and call it with the detail instance in the args
                        Fragment fragment = new CountryDetail();
                        Bundle args = new Bundle();
                        args.putParcelable("details", Parcels.wrap(details));
                        fragment.setArguments(args);
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.flContainer, fragment).commit();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(), "Invalid Country", Toast.LENGTH_SHORT).show();
                        Log.e("Search_Country", "error: " + error.toString());
                    }
                });
        queue.add(jsonObjectRequest);
    }

    //When the user presses enter or presses the submit button
    private void onEnter(){
        //get the text
        String country = etCountrySearch.getText().toString();
        //if the text is empty
        if(country.isEmpty()){
            Toast.makeText(getContext(), "Country cannot be empty", Toast.LENGTH_SHORT).show();
            return;
        }

        //get the first 2 char and make it uppercase
        viewStateDetail(country);
    }

}