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
    List<String> place_contact;
    List<String> place_imgUrl;
    List<String> place_workingHoursFrom;
    List<String> place_workingHoursTo;
    List<String> place_lat;
    List<String> place_lon;
    AllPlacesFragment context;

    public PlaceRecycleAdapter(List<String> place_name, List<String> place_address, List<String> place_contact, List<String> place_imgUrl , List<String> place_workingHoursFrom, List<String> place_workingHoursTo, List<String> place_lat, List<String> place_lon, AllPlacesFragment context){
        this.place_name = place_name;
        this.place_address = place_address;
        this.place_contact = place_contact;
        this.place_imgUrl = place_imgUrl;
        this.place_workingHoursFrom = place_workingHoursFrom;
        this.place_workingHoursTo = place_workingHoursTo;
        this.place_lat = place_lat;
        this.place_lon = place_lon;

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
       ((PlaceViewHolder) holder).place_name_view.setText(place_name.get(position));
        ((PlaceViewHolder) holder).place_address_view.setText(place_address.get(position));

    }

    @Override
    public int getItemCount() {
        return place_name.size();
    }

    public class PlaceViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView place_name_view;
        TextView place_address_view;


        public PlaceViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);
            place_name_view = (TextView) view.findViewById(R.id.place_name);
            place_address_view = (TextView) view.findViewById(R.id.place_address);

        }

        @Override
        public void onClick(View view) {
            int position  =   getAdapterPosition();

            String workingHoursFrom = place_workingHoursFrom.get(position).toString();
            String workingHoursTo = place_workingHoursTo.get(position).toString();
            String address = place_address.get(position).toString();
            String name = place_name.get(position).toString();
            String imgUrl = place_imgUrl.get(position).toString();
            String contact = place_contact.get(position).toString();
            String lat = place_lat.get(position).toString();
            String lon = place_lon.get(position).toString();

           context.changeFragment(name,address,contact,imgUrl,workingHoursFrom,workingHoursTo,lat,lon);

        }
    }
}