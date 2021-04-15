package com.example.covid_dash.fragments;

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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.covid_dash.BuildConfig;
import com.example.covid_dash.R;
import com.example.covid_dash.states.StateDetail;
import com.example.covid_dash.states.StateDetailGetter;

import org.json.JSONObject;
import org.parceler.Parcels;

/**
 * A simple {@link Fragment} subclass.
 */
public class Search_State extends Fragment {
    private String[] states;
    private AutoCompleteTextView etStateSearch;
    private Button btn_State_Search;
    private String COVIDACTNOW_KEY = BuildConfig.COVIDACTNOW_KEY;

    public Search_State() {
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_search__state, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        etStateSearch = view.findViewById(R.id.etCountrySearch);
        btn_State_Search = view.findViewById(R.id.btn_Country_Search);
        states = getResources().getStringArray(R.array.states);

        ArrayAdapter<String> adpater = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, states);
        etStateSearch.setAdapter(adpater);

        etStateSearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                btn_State_Search.setEnabled(false);
                onEnter();

            }
        });

        btn_State_Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onEnter();
            }
        });
    }

    //call a api get method
    private void viewStateDetail(String state) {
        RequestQueue queue = Volley.newRequestQueue(getContext());
        String url = "https://api.covidactnow.org/v2/state/"+state+".json?apiKey="+COVIDACTNOW_KEY;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
            (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    StateDetailGetter details = new StateDetailGetter(response);
                    //start the fragment and call it with the detail instance in the args
                    Fragment fragment = new StateDetail();
                    Bundle args = new Bundle();
                    args.putParcelable("details", Parcels.wrap(details));
                    fragment.setArguments(args);
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.flContainer, fragment).commit();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getContext(), "Invalid State", Toast.LENGTH_SHORT).show();
                    Log.e("Search_State", "error: " + error.toString());
                }
            });
        queue.add(jsonObjectRequest);
    }

    //When the user presses enter or presses the submit button
    private void onEnter(){
        //get the text
        String state = etStateSearch.getText().toString();
        //if the text is empty
        if(state.isEmpty()){
            Toast.makeText(getContext(), "State cannot be empty", Toast.LENGTH_SHORT).show();
            return;
        }
        //if the length of the state is <=1 or if the length is > 2 AND the text doesn't have '-'
        if((state.length() <= 1) || ((state.length() > 2) && (state.charAt(2) != '-'))){
            Toast.makeText(getContext(), "State must be in Abbreviations/Select from the menu" , Toast.LENGTH_SHORT).show();
            return;
        }
        //get the first 2 char and make it uppercase
        state = state.substring(0,2).toUpperCase();
        viewStateDetail(state);
    }
}

