package com.example.covid_dash.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.covid_dash.BuildConfig;
import com.example.covid_dash.R;
import com.example.covid_dash.StateDetailGetter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 */
public class Search_State extends Fragment {
    public static final String TAG = "PostsFragment";

    private RecyclerView rvPosts;
    private EditText etStateSearch;
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

        etStateSearch = view.findViewById(R.id.etStateSearch);
        btn_State_Search = view.findViewById(R.id.btn_State_Search);

        etStateSearch.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)){
                    onEnter();
                    return true;
                }
                return false;
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
                    Log.d("Search_State", "Response: " + details.getState());
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
        String state = etStateSearch.getText().toString().toUpperCase();
        if(state.isEmpty()){
            Toast.makeText(getContext(), "State cannot be empty", Toast.LENGTH_SHORT).show();
            return;
        }
        if((state.length() <= 1) || (state.length() > 2)){
            Toast.makeText(getContext(), "State must be initial letters", Toast.LENGTH_SHORT).show();
            return;
        }
        Toast.makeText(getContext(), "State"+state, Toast.LENGTH_SHORT).show();
        viewStateDetail(state);
    }
}

