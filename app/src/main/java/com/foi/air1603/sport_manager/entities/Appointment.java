package com.foi.air1603.sport_manager.entities;

/**
 * Created by Korisnik on 26-Dec-16.
 */

public class Appointment {
    public Integer id;
    public Integer placeId;
    public String date;
    public String start;
    public String end;
    public Integer maxplayers;


    public int getId() {
        return id;
    }

    public int getPlaceId() {
        return placeId;
    }

    public String getDate() {
        return date;
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }

    public Integer getMaxplayers() {
        return maxplayers;
    }
}
