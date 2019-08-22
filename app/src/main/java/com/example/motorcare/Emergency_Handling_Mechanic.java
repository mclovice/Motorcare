package com.example.motorcare;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.Toolbar;

public class Emergency_Handling_Mechanic extends AppCompatActivity {

    private TabLayout tabLayout;
    //private AppBarLayout app_Bar;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency__handling__mechanic);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Emergencies");

        tabLayout = findViewById(R.id.emergency_tab);
       // app_Bar = findViewById(R.id.my_app_bar);
        viewPager= findViewById(R.id.view_pager_emergency);

        //Fragments addition
        EmergenciesAdapter_mechanic adapter = new EmergenciesAdapter_mechanic(getSupportFragmentManager());
        adapter.addFragment(new My_Pending_emergencies(),"Pending");
        adapter.addFragment(new My_Active_Emergencies(), "Active");
        adapter.addFragment(new My_Handled_Emergencies(), "Handled");

       // Adapter setup
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
