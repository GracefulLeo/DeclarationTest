package com.example.adventurer.declarationtest.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.adventurer.declarationtest.R;
import com.example.adventurer.declarationtest.model.Item;

public class PreviewSinglePersonInfoFragment extends Fragment implements View.OnClickListener {

    // Constants
    private static final String TAG = "Preview Fragment";

    //widgets

    private TextView mFirstName;
    private TextView mLastName;
    private TextView mPlaceOfWork;
    private TextView mPosition;
    private ImageButton mCheckPDF;
    private ImageButton mFavouritesButton;
    private Item mDeclaration;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: ");

        Bundle bundle = this.getArguments();
//        mDeclaration = ()
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: ");

        View view = inflater.inflate(R.layout.fragment_single_person_info_preview,container,false);

        mFirstName = view.findViewById(R.id.first_name_preview);
        mLastName = view.findViewById(R.id.last_name_preview);
        mPlaceOfWork = view.findViewById(R.id.place_of_work_preview);
        mPosition = view.findViewById(R.id.position_preview);

        mCheckPDF = view.findViewById(R.id.button_check_pdf);
        mFavouritesButton = view.findViewById(R.id.button_favourite);


        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_check_pdf:
//                checkPDF();
                break;
        }
    }

    void checkPDF(String pdfLink) {
        startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(pdfLink)));
    }

}
