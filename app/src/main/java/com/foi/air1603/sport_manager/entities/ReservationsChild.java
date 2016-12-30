package com.foi.air1603.sport_manager.entities;

/**
 * Created by Generalko on 28-Dec-16.
 */

public class ReservationsChild {
    String sportName;
    String placeAddress;
    String teamName;

    public ReservationsChild(){

    }

    public ReservationsChild(String sportName, String placeAddress, String teamName) {
        this.sportName = sportName;
        this.placeAddress = placeAddress;
        this.teamName = teamName;
    }

    public String getSportName() {
        return sportName;
    }

    public String getPlaceAddress() {
        return placeAddress;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setSportName(String sportName) {
        this.sportName = sportName;
    }

    public void setPlaceAddress(String placeAddress) {
        this.placeAddress = placeAddress;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
}
