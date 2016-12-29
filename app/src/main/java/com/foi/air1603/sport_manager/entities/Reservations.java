package com.foi.air1603.sport_manager.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Generalko on 28-Dec-16.
 */

public class Reservations{

    int id;
    String sportName;
    String reservationDate;
    String sportPicture;



    public Reservations() {
    }

    public Reservations(Reservations reservations) {
        id = reservations.id;
        sportName = reservations.sportName;
        reservationDate = reservations.reservationDate;
        sportPicture = reservations.sportPicture;
    }

    public int getId() {
        return id;
    }

    public String getSportName() {
        return sportName;
    }

    public String getReservationDate() {
        return reservationDate;
    }

    public String getSportPicture() {
        return sportPicture;
    }

    private List<ReservationsChild> reservationsChildList;

    public List<ReservationsChild> getReservationsChildList() {
        // test data
        reservationsChildList = new ArrayList<>();
        reservationsChildList.add(new ReservationsChild("TERETANA", "Vukovarska 33", "NOGOMETAŠI SA SELA"));
        return reservationsChildList;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSportName(String sportName) {
        this.sportName = sportName;
    }

    public void setReservationDate(String reservationDate) {
        this.reservationDate = reservationDate;
    }

    public void setSportPicture(String sportPicture) {
        this.sportPicture = sportPicture;
    }
}
