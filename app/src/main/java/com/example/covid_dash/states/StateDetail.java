package com.example.covid_dash.states;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.covid_dash.R;
import com.example.covid_dash.fragments.Search_State;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.parceler.Parcels;

public class StateDetail extends AppCompatActivity{

    TextView tvState;
    TextView tvPopulation;
    RatingBar rbRiskLevel;
    TextView tvCases;
    TextView tvPositiveTests;
    TextView tvNegativeTests;
    TextView tvInfectionRate;
    TextView tvVaccinations;
    final FragmentManager fragmentManager = getSupportFragmentManager();
    private BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StateDetailGetter details = (StateDetailGetter) Parcels.unwrap(getIntent().getParcelableExtra("details"));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_state_detail);
        Log.d("StateDetail", "onCreate: "+ details.getState());

        tvState = findViewById(R.id.tvCasesDetails);
        tvPopulation = findViewById(R.id.tvPopulation);
        rbRiskLevel = findViewById(R.id.rbRiskLevel);
        tvCases = findViewById(R.id.tvCases);
        tvPositiveTests = findViewById(R.id.tvPositiveTests);
        tvNegativeTests = findViewById(R.id.tvNegativeTests);
        tvInfectionRate = findViewById(R.id.tvInfectionRate);
        tvVaccinations = findViewById(R.id.tvVaccinations);

        tvState.setText(details.getState());
        tvPopulation.setText("Population\n"+details.getPopulation());
        rbRiskLevel.setRating(details.getOverall());
        tvCases.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(StateDetail.this, "Cases clicked", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(StateDetail.this, CasesDetail.class);
                i.putExtra("details", Parcels.wrap(details));
                startActivity(i);
            }
        });
        tvPositiveTests.setText("Positive\nTests\n"+details.positiveTests);
        tvNegativeTests.setText("Negative\nTests\n"+details.negativeTests);
        tvInfectionRate.setText("Infection\nRate\n"+details.infectionRate);
        tvVaccinations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(StateDetail.this, "Vaccination clicked", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(StateDetail.this, CasesDetail.class);
                i.putExtra("details", Parcels.wrap(details));
                startActivity(i);
            }
        });

        bottomNavigationView = findViewById(R.id.bottomNavigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment;
                switch (menuItem.getItemId()) {
                    case R.id.action_states:
                        Toast.makeText(StateDetail.this, "In state", Toast.LENGTH_SHORT).show();
                        fragment = new Search_State();
                        break;
                    case R.id.action_countries:
                        Toast.makeText(StateDetail.this, "In countries", Toast.LENGTH_SHORT).show();
                        fragment = new Search_State();
                        break;
                    case R.id.action_news:
                    default:
                        Toast.makeText(StateDetail.this, "in news", Toast.LENGTH_SHORT).show();
                        fragment = new Search_State();
                        break;
                }
                fragmentManager.beginTransaction().replace(R.id.flContainer, fragment).commit();
                return true;
            }
        });

    }
}