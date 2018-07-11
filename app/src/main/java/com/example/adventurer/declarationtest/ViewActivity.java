package com.example.adventurer.declarationtest;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

public class ViewActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    //TAGS
    private static final String TAG = "View Activity";

    //Widgets
    private BottomNavigationViewEx bottomNavigationViewEx;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);
        bottomNavigationViewEx = findViewById(R.id.bottom_nav_view);

        initBottomNavigationView();
    }

    private void initBottomNavigationView () {
        Log.d(TAG, "initBottomNavigationView: initizialization of bottom navigation view.");
        bottomNavigationViewEx.enableAnimation(true);
        bottomNavigationViewEx.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.bottom_nav_home:
                Log.d(TAG, "onNavigationItemSelected: home..");
                item.setChecked(true);
                break;
            case R.id.bottom_nav_favourites:
                Log.d(TAG, "onNavigationItemSelected: favourites...");
                item.setChecked(true);
                break;
        }
    return false;
    }
}