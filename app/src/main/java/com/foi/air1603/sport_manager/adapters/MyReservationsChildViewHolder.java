package com.foi.air1603.sport_manager.adapters;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ChildViewHolder;
import com.foi.air1603.sport_manager.R;
import com.foi.air1603.sport_manager.entities.Reservation;

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
        mReservationValue.setText(reservation.id + "");
    }

}
