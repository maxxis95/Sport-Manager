package com.foi.air1603.sport_manager.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.foi.air1603.sport_manager.R;
import com.foi.air1603.sport_manager.fragments.AllPlacesFragment;
import com.foi.air1603.sport_manager.view.PlaceView;

/**
 * Created by Korisnik on 04-Dec-16.
 */

public class PlaceRecycleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    String[] place_name;
    String[] place_address;
    AllPlacesFragment context;

    public PlaceRecycleAdapter(String[] place_name, String[] place_address, AllPlacesFragment context){
        this.place_name = place_name;
        this.place_address = place_address;
        this.context = context;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.place_list_item, parent, false);
        PlaceViewHolder item = new PlaceViewHolder(view);
        return item;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((PlaceViewHolder) holder).place_name.setText(place_name[position]);
        ((PlaceViewHolder) holder).place_address.setText(place_name[position]);
    }

    @Override
    public int getItemCount() {
        return place_name.length;
    }

    public class PlaceViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView place_name;
        TextView place_address;

        public PlaceViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);
            place_name = (TextView) view.findViewById(R.id.place_name);
            place_address = (TextView) view.findViewById(R.id.place_name);
        }

        @Override
        public void onClick(View view) {
            return;
        }

    }
}
