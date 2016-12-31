package com.foi.air1603.sport_manager.adapters;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ParentViewHolder;
import com.foi.air1603.sport_manager.R;
import com.foi.air1603.sport_manager.entities.Reservation;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Generalko on 28-Dec-16.
 */

public class MyReservationsParentViewHolder extends ParentViewHolder {
    @BindView(R.id.reservation_place_name)
    TextView mReservationPlaceName;
    @BindView(R.id.reservation_time)
    TextView mReservationTime;
    @BindView(R.id.sport_image)
    ImageView mReservationSportImage;
    
    View mItemView;

    public MyReservationsParentViewHolder(@NonNull View itemView) {
        super(itemView);
        mItemView = itemView;
        ButterKnife.bind(this, itemView);
    }

    // when the adapter is implemented this method is used to bind list elements with the recycler-view, here, we populate the Views
    public void bind(Reservation reservation) {
        mReservationPlaceName.setText(reservation.appointment.place.name);
        mReservationTime.setText(reservation.created);

        switch (reservation.sport.name){
            case "Košarka":
                mReservationSportImage.setImageResource(R.drawable.basketball);
                break;
            case "Nogomet":
                mReservationSportImage.setImageResource(R.drawable.football);
                break;
            case "Badminton":
                mReservationSportImage.setImageResource(R.drawable.badminton);
                break;
            case "Odbojka":
                mReservationSportImage.setImageResource(R.drawable.volleyball);
                break;
            case "Trčanje":
                mReservationSportImage.setImageResource(R.drawable.running);
                break;
        }
    }
}
