package com.example.adventurer.declarationtest.fragments;

import android.os.Bundle;
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

import com.example.adventurer.declarationtest.MainRecyclerViewAdapter;
import com.example.adventurer.declarationtest.R;
import com.example.adventurer.declarationtest.model.APIResponse;
import com.example.adventurer.declarationtest.model.Item;
import com.example.adventurer.declarationtest.operations.NetworkOperations;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    //TAGS

    private static final int NUM_COLUMNS = 1;
    private static final String TAG = "Home Fragment";

    //Widgets

    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipe;
    private StaggeredGridLayoutManager mStaggeredGridLayoutManager;
    private MainRecyclerViewAdapter mRecyclerViewAdapter;

    //vars

    private APIResponse mRespons = new APIResponse();
    private List<Item> mDeclarations = new ArrayList<>();
    private NetworkOperations operations = new NetworkOperations();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: started.");

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        mRecyclerView = view.findViewById(R.id.recycler_view);
        mSwipe = view.findViewById(R.id.swipe_refresh_layout);

        mSwipe.setOnRefreshListener(this);



        mRespons = operations.getResponses();
        System.out.println(mRespons == null);
        mDeclarations = mRespons.getItems();
        initRecyclerView();
        return view;
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
        onItemsLoadComplete();
    }

    void onItemsLoadComplete() {
        mRecyclerViewAdapter.notifyDataSetChanged();
        mSwipe.setRefreshing(false);
    }
}
