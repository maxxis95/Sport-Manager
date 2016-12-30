package com.foi.air1603.sport_manager.adapters;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.foi.air1603.sport_manager.R;
import com.foi.air1603.sport_manager.entities.Place;
import com.foi.air1603.sport_manager.view.fragments.AllPlacesFragment;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Korisnik on 04-Dec-16.
 */

public class PlaceRecycleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<Integer> place_id;
    List<String> place_name;
    List<String> place_address;
    List<String> place_contact;
    List<String> place_img;
    List<String> place_workingHoursFrom;
    List<String> place_workingHoursTo;
    List<String> place_lat;
    List<String> place_lon;

    List<Place> places;
    AllPlacesFragment context;
    Context cont;


    public PlaceRecycleAdapter(List<Integer> place_id, List<String> place_name, List<String> place_address, List<String> place_contact, List<String> place_img, List<String> place_workingHoursFrom, List<String> place_workingHoursTo, List<String> place_lat, List<String> place_lon, AllPlacesFragment context) {
        this.place_id = place_id;
        this.place_name = place_name;
        this.place_address = place_address;
        this.place_contact = place_contact;
        this.place_img = place_img;
        this.place_workingHoursFrom = place_workingHoursFrom;
        this.place_workingHoursTo = place_workingHoursTo;
        this.place_lat = place_lat;
        this.place_lon = place_lon;
        this.context = context;
    }

    public PlaceRecycleAdapter(List<Place> places, AllPlacesFragment context) {
        this.places = places;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.place_list_item, parent, false);
        PlaceViewHolder item = new PlaceViewHolder(view);
        cont = parent.getContext();
        return item;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Place place = places.get(position);

        ((PlaceViewHolder) holder).place_name_view.setText(place.name);
        ((PlaceViewHolder) holder).place_address_view.setText(place.address);

        if (!place.img.isEmpty()) {
            Uri uri = Uri.parse(place.img);
            Picasso.with(cont).load(uri).into(((PlaceViewHolder) holder).place_img_view);
        } else {
            ((PlaceViewHolder) holder).place_img_view.setImageResource(R.drawable.place_stock);
        }
    }

    @Override
    public int getItemCount() {
        return places.size();
    }


    public class PlaceViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView place_name_view;
        TextView place_address_view;
        ImageView place_img_view;


        public PlaceViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);
            place_name_view = (TextView) view.findViewById(R.id.place_name);
            place_address_view = (TextView) view.findViewById(R.id.place_address);
            place_img_view = (ImageView) view.findViewById(R.id.place_image);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            Place place = places.get(position);
            context.changeFragment(place);
        }
    }
}
