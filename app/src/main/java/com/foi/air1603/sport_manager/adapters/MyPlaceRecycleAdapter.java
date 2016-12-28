package com.foi.air1603.sport_manager.adapters;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.foi.air1603.sport_manager.R;
import com.foi.air1603.sport_manager.view.fragments.AllPlacesFragment;
import com.foi.air1603.sport_manager.view.fragments.MyPlacesFragment;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Korisnik on 28-Dec-16.
 */

public class MyPlaceRecycleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<Integer> place_id;
    List<String> place_name;
    List<String> place_address;
    List<String> place_contact;
    List<String> place_img;
    List<String> place_workingHoursFrom;
    List<String> place_workingHoursTo;
    List<String> place_lat;
    List<String> place_lon;
    MyPlacesFragment context;
    Context cont;

    public MyPlaceRecycleAdapter(List<Integer> id, List<String> name, List<String> address, List<String> contact, List<String> img, List<String> workingHoursFrom, List<String> workingHoursTo, List<String> lat, List<String> lon, MyPlacesFragment myPlacesFragment) {
        this.place_id = id;
        this.place_name = name;
        this.place_address = address;
        this.place_contact = contact;
        this.place_img = img;
        this.place_workingHoursFrom = workingHoursFrom;
        this.place_workingHoursTo = workingHoursTo;
        this.place_lat = lat;
        this.place_lon = lon;
        this.context = myPlacesFragment;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_place_list_item, parent, false);
        MyPlaceRecycleAdapter.MyPlaceViewHolder item = new MyPlaceRecycleAdapter.MyPlaceViewHolder(view);
        cont= parent.getContext();
        return item;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        ((MyPlaceRecycleAdapter.MyPlaceViewHolder) holder).my_place_name_view.setText(place_name.get(position));
        ((MyPlaceRecycleAdapter.MyPlaceViewHolder) holder).my_place_address_view.setText(place_address.get(position));
        ((MyPlaceRecycleAdapter.MyPlaceViewHolder) holder).my_place_add_appointment_btn.setText("Dodaj termin");
        ((MyPlaceRecycleAdapter.MyPlaceViewHolder) holder).my_place_reserved_appointments_btn.setText("Rezervirani termini");

        if(!place_img.get(position).isEmpty() ){

            Uri uri = Uri.parse(place_img.get(position));

            Picasso.with(cont).load(uri).into(((MyPlaceViewHolder) holder).my_place_img_view);

        } else {
            ((MyPlaceViewHolder) holder).my_place_img_view.setImageResource(R.drawable.place_stock);
        }


    }



    @Override
    public int getItemCount() {
        return place_name.size();
    }

    public class MyPlaceViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView my_place_name_view;
        TextView my_place_address_view;
        ImageView my_place_img_view;
        Button my_place_add_appointment_btn;
        Button my_place_reserved_appointments_btn;


        public MyPlaceViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);
            my_place_name_view = (TextView) view.findViewById(R.id.my_place_name);
            my_place_add_appointment_btn = (Button) view.findViewById(R.id.btn_my_place_add_appointment);
            my_place_reserved_appointments_btn = (Button) view.findViewById(R.id.btn_my_place_reserved);
            my_place_address_view = (TextView) view.findViewById(R.id.my_place_address);
            my_place_img_view = (ImageView) view.findViewById(R.id.my_place_image);
        }

        @Override
        public void onClick(View view) {
            int position  =   getAdapterPosition();
            int id = place_id.get(position);
                context.changeFragment(id);


        }
    }
}
