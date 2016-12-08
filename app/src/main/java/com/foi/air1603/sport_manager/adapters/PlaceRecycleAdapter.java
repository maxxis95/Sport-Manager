package com.foi.air1603.sport_manager.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.foi.air1603.sport_manager.R;
import com.foi.air1603.sport_manager.view.fragments.AllPlacesFragment;

import java.util.List;

/**
 * Created by Korisnik on 04-Dec-16.
 */

public class PlaceRecycleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<String> place_name;
    List<String> place_address;
    AllPlacesFragment context;

    public PlaceRecycleAdapter(List<String> place_name, List<String> place_address, AllPlacesFragment context){
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
       ((PlaceViewHolder) holder).place_name.setText(place_name.get(position));
        ((PlaceViewHolder) holder).place_address.setText(place_address.get(position));
    }

    @Override
    public int getItemCount() {
        return place_name.size();
    }

    public class PlaceViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView place_name;
        TextView place_address;

        public PlaceViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);
            place_name = (TextView) view.findViewById(R.id.place_name);
            place_address = (TextView) view.findViewById(R.id.place_address);
        }

        @Override
        public void onClick(View view) {
            context.changeFragment();

        }


    }
}
