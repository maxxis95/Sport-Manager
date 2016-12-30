package com.foi.air1603.sport_manager.adapters;

import android.support.annotation.NonNull;
import android.view.View;
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
//    @BindView(R.id.sport_image)
//    TextView mSportImage;

    View mItemView;

    public MyReservationsParentViewHolder(@NonNull View itemView) {
        super(itemView);
        mItemView = itemView;
        ButterKnife.bind(this, itemView);
    }

    // when the adapter is implemented this method is used to bind list elements with the recycler-view, here, we populate the Views
    public void bind(Reservation reservations){
        mReservationPlaceName.setText(reservations.created);
        mReservationTime.setText(reservations.submitted);
        //todo: set sport image if you want
    }
}
