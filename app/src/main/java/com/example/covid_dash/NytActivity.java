package com.example.covid_dash;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Headers;
public class NytActivity extends Fragment {

    List<NYT> nyts;
    public static final String NOW_PLAYING_URL ="https://api.nytimes.com/svc/search/v2/articlesearch.json?q=covid&api-key="+BuildConfig.NYT_KEY;


    public NytActivity() {
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_nyt, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView rvHeadlines = view.findViewById(R.id.rvHeadlines);
        nyts = new ArrayList<>();
        NYTAdpater nytAdpater = new NYTAdpater(getContext(), nyts);
        rvHeadlines.setAdapter(nytAdpater);
        rvHeadlines.setLayoutManager(new LinearLayoutManager(getContext()));

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(NOW_PLAYING_URL, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Headers headers, JSON json) {
                Log.d("NYTActivity", "onSuccess");
                JSONObject jsonObject = json.jsonObject;
                try {
                    JSONArray results = jsonObject.getJSONObject("response").getJSONArray("docs");
                    Log.i("NYTActivity", "Results:" + results.toString());
                    nyts.addAll(NYT.fromJsonArray(results));
                    nytAdpater.notifyDataSetChanged();
                    Log.i("NYTActivity", "Movies:" + nyts.size());
                } catch (JSONException e) {
                    Log.e("NYTActivity", "Hit json exception");
                }

            }

            @Override
            public void onFailure(int i, Headers headers, String s, Throwable throwable) {
                Log.d("NYTActivity", "onFailure");
            }
        });
    }
//
//    //call a api get method
//    private void viewStateDetail(String state) {
//        RequestQueue queue = Volley.newRequestQueue(getContext());
//        String url = "https://api.nytimes.com/svc/search/v2/articlesearch.json?q=covid&api-key="+NYT_KEY;
//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
//                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        NYT details = new NYT();
//                        //start the fragment and call it with the detail instance in the args
//                        Fragment fragment = new StateDetail();
//                        Bundle args = new Bundle();
//                        args.putParcelable("details", Parcels.wrap(details));
//                        fragment.setArguments(args);
//                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.flContainer, fragment).commit();
//                    }
//                }, new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(getContext(), "Invalid State", Toast.LENGTH_SHORT).show();
//                        Log.e("Search_State", "error: " + error.toString());
//                    }
//                });
//        queue.add(jsonObjectRequest);
//    }
//
//    //When the user presses enter or presses the submit button
//    private void onEnter(){
//        //get the text
//        String state = etStateSearch.getText().toString();
//        //if the text is empty
//        if(state.isEmpty()){
//            Toast.makeText(getContext(), "State cannot be empty", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        //if the length of the state is <=1 or if the length is > 2 AND the text doesn't have '-'
//        if((state.length() <= 1) || ((state.length() > 2) && (state.charAt(2) != '-'))){
//            Toast.makeText(getContext(), "State must be in Abbreviations/Select from the menu" , Toast.LENGTH_SHORT).show();
//            return;
//        }
//        //get the first 2 char and make it uppercase
//        state = state.substring(0,2).toUpperCase();
//        viewStateDetail(state);
//    }
}