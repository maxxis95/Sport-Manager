package com.foi.air1603.sport_manager.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.foi.air1603.sport_manager.R;
import com.foi.air1603.sport_manager.entities.Appointment;
import com.foi.air1603.sport_manager.entities.Reservation;
import com.foi.air1603.sport_manager.view.fragments.MyPlacesReservationFragment;

import java.util.List;

/**
 * Created by Generalko on 31-Dec-16.
 */

public class MyPlaceReservationsRecycleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    MyPlacesReservationFragment myPlacesReservationFragment;
    Context context;
    List<Appointment> appointmentList;
    //List<Reservation> reservationList;

    public MyPlaceReservationsRecycleAdapter(MyPlacesReservationFragment myPlacesReservationFragment, List<Appointment> appointmentList) {
        this.myPlacesReservationFragment = myPlacesReservationFragment;
        this.appointmentList = appointmentList;
        //this.reservationList = reservationList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_my_places_reservations, parent, false);
        MyPlaceReservationsRecycleAdapter.MyPlaceReservationsViewHolder item = new MyPlaceReservationsRecycleAdapter.MyPlaceReservationsViewHolder(view);
        context = parent.getContext();
        return item;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final Appointment appointment = appointmentList.get(position);
        //final Reservation reservation = reservationList.get(position);

        ((MyPlaceReservationsViewHolder) holder).place_appointment_date.setText(appointment.date);
        ((MyPlaceReservationsViewHolder) holder).place_appointment_start.setText(appointment.start);
        ((MyPlaceReservationsViewHolder) holder).place_appointment_end.setText(appointment.end);
        //((MyPlaceReservationsViewHolder) holder).place_appointment_sport_image.setImageResource(reservation.);
    }

    @Override
    public int getItemCount() {
        return appointmentList.size();
    }

    public class MyPlaceReservationsViewHolder extends RecyclerView.ViewHolder {
        TextView place_appointment_date;
        TextView place_appointment_start;
        TextView place_appointment_end;

        /*
        ImageView place_appointment_sport_image;
        TextView place_appointment_sport_name;
        TextView place_appointment_main_user;
        TextView place_appointment_other_users;
        TextView place_appointment_id;
        */

        public MyPlaceReservationsViewHolder(View view) {
            super(view);
            place_appointment_date = (TextView) view.findViewById(R.id.place_appointment_date);
            place_appointment_start = (TextView) view.findViewById(R.id.place_appointment_start);
            place_appointment_end = (TextView) view.findViewById(R.id.place_appointment_end);
            /*
            place_appointment_sport_image = (ImageView) view.findViewById(R.id.place_appointment_image);
            place_appointment_sport_name = (TextView) view.findViewById(R.id.place_appointment_sport_name);
            place_appointment_main_user = (TextView) view.findViewById(R.id.place_appointment_user_main);
            place_appointment_other_users = (TextView) view.findViewById(R.id.place_appointment_other_users);
            place_appointment_id = (TextView) view.findViewById(R.id.place_appointment_id);
            */
        }
    }
}
