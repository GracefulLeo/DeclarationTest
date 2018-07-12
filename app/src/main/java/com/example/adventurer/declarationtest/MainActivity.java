package com.example.adventurer.declarationtest;

import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import com.example.adventurer.declarationtest.fragments.HomeFragment;
import com.example.adventurer.declarationtest.fragments.PreviewSinglePersonInfoFragment;
import com.example.adventurer.declarationtest.model.APIResponse;
import com.example.adventurer.declarationtest.model.Item;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

public class MainActivity extends AppCompatActivity implements
        BottomNavigationView.OnNavigationItemSelectedListener, IMainActivity {

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
        inflateHomeFragment();
    }

    private void initBottomNavigationView () {
        Log.d(TAG, "initBottomNavigationView: initizialization of bottom navigation view.");
        bottomNavigationViewEx.enableAnimation(true);
        bottomNavigationViewEx.setOnNavigationItemSelectedListener(this);
    }

    private void inflateHomeFragment (){
        Log.d(TAG, "inflateHomeFragment: inflating.");
        HomeFragment homeFragment = new HomeFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_content_frame, homeFragment, getString(R.string.tag_fragment_home));
        transaction.addToBackStack(getString(R.string.tag_fragment_home));
        transaction.commit();
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

    @Override
    public void inflatePreviewSinglePersonInfoFragment(Item declaration) {
        PreviewSinglePersonInfoFragment previewSinglePersonInfoFragment = new PreviewSinglePersonInfoFragment();

        Bundle args = new Bundle();
        args.putString("id",declaration.getLinkPDF() );

        previewSinglePersonInfoFragment.setArguments(args);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_content_frame, previewSinglePersonInfoFragment
                , getString(R.string.tag_fragment_preview_single_person_info));
        transaction.addToBackStack(getString(R.string.tag_fragment_preview_single_person_info));
        transaction.commit();
    }
}