package com.example.adventurer.declarationtest.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.adventurer.declarationtest.IMainActivity;
import com.example.adventurer.declarationtest.R;
import com.example.adventurer.declarationtest.adapters.MainRecyclerViewAdapter;
import com.example.adventurer.declarationtest.model.APIResponse;
import com.example.adventurer.declarationtest.model.Item;
import com.example.adventurer.declarationtest.operations.NetworkOperations;
import com.example.adventurer.declarationtest.utils.PreferenceKeys;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FavouritesFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    //TAGS

    private static final int NUM_COLUMNS = 1;
    private static final String TAG = "Favourites fragment";

    //Widgets

    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipe;
    private StaggeredGridLayoutManager mStaggeredGridLayoutManager;
    private MainRecyclerViewAdapter mRecyclerViewAdapter;

    //vars

    private IMainActivity mInterface;
    private NetworkOperations operations = new NetworkOperations();
    private APIResponse mRespons = new APIResponse();
    private List<Item> mDeclarations = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: started.");

        View view = inflater.inflate(R.layout.fragment_favourites, container, false);

        mRecyclerView = view.findViewById(R.id.recycler_view);
        mSwipe = view.findViewById(R.id.swipe_refresh_layout);

        mSwipe.setOnRefreshListener(this);

        getFavouritesList();
        return view;
    }

    private void getFavouritesList() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        Set<String> savedID = preferences.getStringSet(PreferenceKeys.ID, new HashSet<String>());

        if (mDeclarations != null) {
            mDeclarations.clear();
        }
        List<Item> items = new ArrayList<>();
        mRespons = operations.getResponses();
        if (mRespons != null) {
            items = mRespons.getItems();
            for (Item item : items) {
                if (savedID.contains(item.getId())) {
                    mDeclarations.add(item);
                }
            }
            System.out.println(mDeclarations);


            if (mRecyclerViewAdapter == null) {
                initRecyclerView();
            }

        }
    }

    private void initRecyclerView() {
        Log.d(TAG, "initRecyclerView: init recyclerview.");
        mStaggeredGridLayoutManager = new StaggeredGridLayoutManager(NUM_COLUMNS, LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mStaggeredGridLayoutManager);
        mRecyclerViewAdapter = new MainRecyclerViewAdapter(getActivity(), mDeclarations);
        mRecyclerView.setAdapter(mRecyclerViewAdapter);
    }

    @Override
    public void onRefresh() {
        if (mDeclarations != null) {
            getFavouritesList();
            onItemsLoadComplete();
        }
    }

    void onItemsLoadComplete() {
        mRecyclerViewAdapter.notifyDataSetChanged();
        mSwipe.setRefreshing(false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mInterface = (IMainActivity) getActivity();
    }

}
