package com.foi.air1603.sport_manager.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Generalko on 28-Dec-16.
 */

public class Reservation {

    public int id;
    public String created;
    public String submitted;
    public String confirmed;
    public String placeName;
    public Place place;
    public Appointment appointment;
    public Sport sport;


    public Reservation() {
    }

    public Reservation(Reservation reservation) {
        id = reservation.id;
        created = reservation.created;
       // placeName = reservation.appointment.place.name;
        place = reservation.appointment.place;
        submitted = reservation.submitted;
        confirmed = reservation.confirmed;
        sport = reservation.sport;
    }

    private List<ReservationsChild> reservationChildList;

    public List<ReservationsChild> getReservationsChildList() {
        // test data
        reservationChildList = new ArrayList<>();
        reservationChildList.add(new ReservationsChild("TERETANA", "Vukovarska 33", "NOGOMETAŠI SA SELA"));
        return reservationChildList;
    }


}
