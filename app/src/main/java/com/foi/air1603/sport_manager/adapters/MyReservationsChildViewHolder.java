package com.foi.air1603.sport_manager.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bignerdranch.expandablerecyclerview.ChildViewHolder;
import com.foi.air1603.sport_manager.R;
import com.foi.air1603.sport_manager.entities.Reservation;
import com.foi.air1603.sport_manager.view.fragments.MyReservationsFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Generalko on 28-Dec-16.
 */

public class MyReservationsChildViewHolder extends ChildViewHolder {

    @BindView(R.id.myAppointmentType)
    TextView mReservationType;
    @BindView(R.id.myAppointmentAddress)
    TextView mReservationAddress;
    @BindView(R.id.myAppointmentPlayers)
    TextView mReservationPlayers;
    @BindView(R.id.myAppointmentID)
    TextView mReservationValue;
    @BindView(R.id.myAppointmentConfirm)
    Button myAppointmentConfirm;
    @BindView(R.id.myAppointmentDelete)
    Button myAppointmentDelete;


    MyReservationsRecycleAdapter mAdapter;
    View mItemView;
    Activity mActivity;


    public MyReservationsChildViewHolder(@NonNull View itemView, MyReservationsRecycleAdapter adapter) {
        super(itemView);
        mAdapter = adapter;
        mItemView = itemView;
        ButterKnife.bind(this, itemView);
    }

    public void bind(Reservation reservation) {
        mReservationType.setText(reservation.sport.name);
        mReservationAddress.setText(reservation.appointment.place.address);
        mReservationPlayers.setText(reservation.team.name);
        mReservationValue.setText("ID " + reservation.id + "");
        myAppointmentConfirm.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Toast.makeText(context.getApplicationContext(),
                        "Ova mogućnost trenutno nije implementirana", Toast.LENGTH_LONG).show();
            }
        });
        myAppointmentDelete.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Toast.makeText(context.getApplicationContext(),
                        "Ova mogućnost trenutno nije implementirana", Toast.LENGTH_LONG).show();
            }
        });


    }

}
