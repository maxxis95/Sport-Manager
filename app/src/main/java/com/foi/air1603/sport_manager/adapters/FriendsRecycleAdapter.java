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
import com.foi.air1603.sport_manager.entities.User;
import com.foi.air1603.sport_manager.view.fragments.AllPlacesFragment;
import com.foi.air1603.sport_manager.view.fragments.InviteFriendsFragment;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Korisnik on 04-Dec-16.
 */

public class FriendsRecycleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<User> users;
    InviteFriendsFragment context;
    Context cont;


    public FriendsRecycleAdapter(List<User> users, InviteFriendsFragment context) {
        this.users = users;
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
        User user = users.get(position);

        ((PlaceViewHolder) holder).place_name_view.setText(user.firstName);
        /*((PlaceViewHolder) holder).place_address_view.setText(place.address);

        if (!place.img.isEmpty()) {
            Uri uri = Uri.parse(place.img);
            Picasso.with(cont).load(uri).into(((PlaceViewHolder) holder).place_img_view);
        } else {
            ((PlaceViewHolder) holder).place_img_view.setImageResource(R.drawable.place_stock);
        }*/
    }

    @Override
    public int getItemCount() {
        return users.size();
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
            User user = users.get(position);
           // context.changeFragment(user);
        }
    }
}
