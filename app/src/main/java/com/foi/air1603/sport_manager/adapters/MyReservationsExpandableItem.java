package com.foi.air1603.sport_manager.adapters;

import com.bignerdranch.expandablerecyclerview.model.Parent;
import com.foi.air1603.sport_manager.entities.Reservations;
import com.foi.air1603.sport_manager.entities.ReservationsChild;

import java.util.List;

/**
 * Created by Generalko on 29-Dec-16.
 */

public class MyReservationsExpandableItem extends Reservations implements Parent<ReservationsChild> {

    private List<ReservationsChild> mReservationsList;

    public MyReservationsExpandableItem(Reservations reservations) {
        super(reservations);
        mReservationsList = reservations.getReservationsChildList();
    }

    @Override
    public List<ReservationsChild> getChildList() {
        return mReservationsList;
    }

    @Override
    public boolean isInitiallyExpanded() {
        return false;
    }
}
