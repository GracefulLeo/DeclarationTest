package com.example.adventurer.declarationtest.fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adventurer.declarationtest.IMainActivity;
import com.example.adventurer.declarationtest.R;
import com.example.adventurer.declarationtest.model.Item;
import com.example.adventurer.declarationtest.utils.PreferenceKeys;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PreviewSinglePersonInfoFragment extends Fragment implements View.OnClickListener {

    // Constants
    private static final String TAG = "Preview Fragment";

    //widgets

    private TextView mFirstNameTV;
    private TextView mLastNameTV;
    private TextView mPlaceOfWorkTV;
    private TextView mPositionTV;
    private Button mCheckPDFBtn;
    private RadioButton mFavouritesBtn;
    private Boolean isChecked;
    private Item mDeclaration = new Item();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");

        Bundle bundle = this.getArguments();
        System.out.println("Bundle comes: " + bundle);

        if (bundle != null) {
            mDeclaration = bundle.getParcelable("Declaration");
            System.out.println(mDeclaration.getFirstname());
            System.out.println("Got incoming bundle link:" + mDeclaration.getLinkPDF());
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: ");

        View view = inflater.inflate(R.layout.fragment_single_person_info_preview, container, false);

        mFirstNameTV = view.findViewById(R.id.first_name_preview);
        mLastNameTV = view.findViewById(R.id.last_name_preview);
        mPlaceOfWorkTV = view.findViewById(R.id.place_of_work_preview);
        mPositionTV = view.findViewById(R.id.position_preview);

        mCheckPDFBtn = view.findViewById(R.id.button_check_pdf);
        mFavouritesBtn = view.findViewById(R.id.button_favourite);

        mCheckPDFBtn.setOnClickListener(this);
        mFavouritesBtn.setOnClickListener(this);


        init();
        checkIfAdded();
        return view;
    }

    private void init() {
        mFirstNameTV.setText(mDeclaration.getFirstname());
        mLastNameTV.setText(mDeclaration.getLastname());
        mPlaceOfWorkTV.setText(mDeclaration.getPlaceOfWork());
        mPositionTV.setText(mDeclaration.getPosition());
    }

    private void checkIfAdded() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        Set<String> savedID = preferences.getStringSet(PreferenceKeys.ID, new HashSet<String>());
        if (savedID.contains(mDeclaration.getId())) {
            Log.d(TAG, "checkIfAdded: added.");
            mFavouritesBtn.setChecked(true);
            isChecked = true;
        } else {
            Log.d(TAG, "checkIfAdded: removed.");
            mFavouritesBtn.setChecked(false);
            isChecked = false;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_check_pdf:
                if (mDeclaration.getLinkPDF() != null) {
                    checkPDF(mDeclaration.getLinkPDF());
                } else {
                    Toast.makeText(getContext(), "No link was found.", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.button_favourite:
                Log.d(TAG, "onClick: favourites clicked");
                if (!isChecked) {
                    mFavouritesBtn.setChecked(true);
                    isChecked = true;
                    addToFavourites();
                    Toast.makeText(getContext(), "Added to favourites", Toast.LENGTH_SHORT).show();
                } else {
                    mFavouritesBtn.setChecked(false);
                    isChecked = false;
                    removeFromFavourites();
                    Toast.makeText(getContext(), "Removed from favourites", Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }

    private void checkPDF(String pdfLink) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(pdfLink)));
    }

    public void addToFavourites() {
        Log.d(TAG, "addToFavourites: added to favourites");
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        SharedPreferences.Editor editor = preferences.edit();

        Set<String> savedId = preferences.getStringSet(PreferenceKeys.ID, new HashSet<String>());
        savedId.add(mDeclaration.getId());
        editor.putStringSet(PreferenceKeys.ID, savedId);
        editor.commit();
    }

    public void removeFromFavourites() {
        Log.d(TAG, "removeFromFavourites: removed from favourites");
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        SharedPreferences.Editor editor = preferences.edit();

        Set<String> savedId = preferences.getStringSet(PreferenceKeys.ID, new HashSet<String>());
        savedId.remove(mDeclaration.getId());
        editor.remove(PreferenceKeys.ID);
        editor.commit();
        editor.putStringSet(PreferenceKeys.ID, savedId);
        editor.commit();
    }
}
