package com.foi.air1603.sport_manager.model;

import com.foi.air1603.sport_manager.entities.Reservation;

/**
 * Created by Generalko on 28-Dec-16.
 */

public interface MyReservationsInteractor {
    void getMyReservationsObject(int userId);

    void setReservationsObject(Reservation reservation);
}
