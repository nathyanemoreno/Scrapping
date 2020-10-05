package com.example.scrapping.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.scrapping.R;

import java.util.LinkedList;


public class EventListAdapter extends RecyclerView.Adapter <EventListAdapter.EventViewHolder>{

    private final LinkedList<String> mDataset;
    private LayoutInflater mInflater;

//    Create the class for the ViewHolder that will load the information (text, images , ...) for one item to be displayed
    static class EventViewHolder extends RecyclerView.ViewHolder{
        TextView eventItemView;
        final EventListAdapter mAdapter;
        EventViewHolder (View itemView, EventListAdapter adapter) {
            super(itemView);
            eventItemView = itemView.findViewById(R.id.event_title_text);
            this.mAdapter = adapter;
    }
}

//    Create the class for the ViewHolder that will load the information (text, images , ...) for one item to be displayed
    public EventListAdapter (Context context, LinkedList<String> myDataset){
        mInflater = LayoutInflater.from(context);
        this.mDataset = myDataset;
    }

    @NonNull
    @Override
    public EventListAdapter.EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.event_list_item, parent, false);
        return new EventViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull EventListAdapter.EventViewHolder holder, int position) {
        String mCurrent = mDataset.get(position);
        holder.eventItemView.setText(mCurrent);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}