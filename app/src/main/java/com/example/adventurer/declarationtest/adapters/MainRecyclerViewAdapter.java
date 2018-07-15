package com.example.adventurer.declarationtest.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.adventurer.declarationtest.IMainActivity;
import com.example.adventurer.declarationtest.R;
import com.example.adventurer.declarationtest.model.Item;

import java.util.List;

public class MainRecyclerViewAdapter extends RecyclerView.Adapter<MainRecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "MainRecyclerViewAd";

    //vars
    private List<Item> mDeclarations;
    private IMainActivity mInterface;
    private Context mContext;

    public MainRecyclerViewAdapter(Context context, List<Item> declarations) {
        mDeclarations = declarations;
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_main_feed, parent,
                false);
        Log.d(TAG, "onCreateViewHolder: created...");
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called...");

        holder.mFirstName.setText(mDeclarations.get(position).getFirstname());
        holder.mLastName.setText(mDeclarations.get(position).getLastname());
        holder.mPlaceOfWork.setText(mDeclarations.get(position).getPlaceOfWork());
        holder.mPosition.setText(mDeclarations.get(position).getPosition());
        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: clicked on: " + mDeclarations.get(position).getFirstname());
                mInterface.inflatePreviewSinglePersonInfoFragment(mDeclarations.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mDeclarations != null) {
            return mDeclarations.size();
        }
        return 0;
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        mInterface = (IMainActivity) mContext;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        CardView mCardView;
        TextView mFirstName;
        TextView mLastName;
        TextView mPlaceOfWork;
        TextView mPosition;

        public ViewHolder(View itemView) {
            super(itemView);
            mFirstName = itemView.findViewById(R.id.first_name);
            mLastName = itemView.findViewById(R.id.last_name);
            mPlaceOfWork = itemView.findViewById(R.id.place_of_work);
            mPosition = itemView.findViewById(R.id.position);
            mCardView = itemView.findViewById(R.id.card_view);
        }
    }
}
