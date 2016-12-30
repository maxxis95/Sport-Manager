package com.foi.air1603.sport_manager.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Generalko on 28-Dec-16.
 */

public class Reservation {

    int id;
    public String created;
    public String submitted;
    public String confirmed;


    public Reservation() {
    }

    public Reservation(Reservation reservations) {
        id = reservations.id;
        created = reservations.created;
        submitted = reservations.submitted;
        confirmed = reservations.confirmed;
    }

    public int getId() {
        return id;
    }

    /* public String getCreated() {
         return created;
     }

     public String getSubmitted() {
         return submitted;
     }


     public String getConfirmed() {
         return confirmed;
     }
 */
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

    public void setCreated(String created) {
        this.created = created;
    }

    public void setSubmitted(String submitted) {
        this.submitted = submitted;
    }

    public void setReservationsChildList(List<ReservationsChild> reservationsChildList) {
        this.reservationsChildList = reservationsChildList;
    }

    public void setConfirmed(String confirmed) {
        this.confirmed = confirmed;
    }
}
