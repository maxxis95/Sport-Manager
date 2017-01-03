package com.foi.air1603.sport_manager.presenter;

import com.foi.air1603.sport_manager.entities.Reservation;

/**
 * Created by Korisnik on 26-Dec-16.
 */

public interface AppointmentPresenter {
    void loadAllAppointments();

    void showAppointmentsForDate(Integer date);

    void reservateAppointment(Reservation userReseravation);
}
