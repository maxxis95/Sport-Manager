package com.foi.air1603.sport_manager.adapters;

import android.content.Context;
import android.net.Uri;
import android.provider.Settings;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.foi.air1603.sport_manager.R;
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
    AllPlacesFragment context;
    Context cont;



    public PlaceRecycleAdapter(List<Integer> place_id, List<String> place_name, List<String> place_address, List<String> place_contact, List<String> place_img , List<String> place_workingHoursFrom, List<String> place_workingHoursTo, List<String> place_lat, List<String> place_lon, AllPlacesFragment context){
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

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.place_list_item, parent, false);
        PlaceViewHolder item = new PlaceViewHolder(view);
        cont= parent.getContext();
        return item;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((PlaceViewHolder) holder).place_name_view.setText(place_name.get(position));
        ((PlaceViewHolder) holder).place_address_view.setText(place_address.get(position));

        if(!place_img.get(position).isEmpty() ){

            Uri uri = Uri.parse(place_img.get(position));

            Picasso.with(cont).load(uri).resize(577, 120).into(((PlaceViewHolder) holder).place_img_view);
            System.out.println("kurac 1");

        } else {
            ((PlaceViewHolder) holder).place_img_view.setImageResource(R.drawable.place_stock);
        }




    }

    @Override
    public int getItemCount() {
        return place_name.size();
    }



    public class PlaceViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

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
            int position  =   getAdapterPosition();
            int id = place_id.get(position);
            String workingHoursFrom = place_workingHoursFrom.get(position).toString();
            String workingHoursTo = place_workingHoursTo.get(position).toString();
            String address = place_address.get(position).toString();
            String name = place_name.get(position).toString();
            String img = place_img.get(position) != null ? place_img.get(position).toString() : "";
            String contact = place_contact.get(position).toString();
            String lat = place_lat.get(position).toString();
            String lon = place_lon.get(position).toString();

           context.changeFragment(id,name,address,contact,img,workingHoursFrom,workingHoursTo,lat,lon);

        }
    }
}
